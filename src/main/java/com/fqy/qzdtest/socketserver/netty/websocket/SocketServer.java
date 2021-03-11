package com.fqy.qzdtest.socketserver.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SocketServer {

    @Autowired
    private ChannelMannger channelMannger;

    private int port = 9999;
    private int HEART_TIME_OUT = 30;
    private NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private NioEventLoopGroup workGroup = new NioEventLoopGroup();
    private Channel channel;

    public SocketServer(){}

    public SocketServer(int port, int HEART_TIME_OUT) {
        this.port = port;
        this.HEART_TIME_OUT = HEART_TIME_OUT;
    }

    @PostConstruct
    public void run() throws Exception {


        ServerBootstrap serverBootstrap = new ServerBootstrap();


        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(channelMannger);

        System.out.println("netty 服务器创建成功...");

        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        //channelFuture.channel().closeFuture().sync();
        if (channelFuture.isSuccess()) {
            channel = channelFuture.channel();
        }

    }

    @PreDestroy
    public void destory() {
        if (channel != null) {
            channel.close();
        }
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }


    public static void main(String[] args) throws Exception {

        SocketServer socketServer = new SocketServer(9999, 30);

        socketServer.run();

    }

}
