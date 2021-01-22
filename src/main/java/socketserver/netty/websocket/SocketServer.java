package socketserver.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class SocketServer {

    private int port;
    private int HEART_TIME_OUT;

    private static ConcurrentMap<String, Channel> socketConnections = new ConcurrentHashMap<>();
    private static ConcurrentMap<String, String> lightReturnMessage = new ConcurrentHashMap<>();

    public SocketServer(int port,int HEART_TIME_OUT){
        this.port = port;
        this.HEART_TIME_OUT = HEART_TIME_OUT;
    }

    public void run() throws Exception {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //pipeline.addLast("StringDecode",new StringDecoder());
                            //pipeline.addLast("StringEncode",new StringEncoder());
                            //pipeline.addLast("hexDecoder",new SocketHexDecoder());
                            //===========

                            //===========/*pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            //                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));*/

                            //添加心跳监测机制
                            //long readerIdleTime 未发生读操作时间
                            // long writerIdleTime, 未发生写操作事件
                            // long allIdleTime, 未发生任何事件事件
                            // TimeUnit unit 单位
                            //Triggers an {@link IdleStateEvent} when a {@link Channel} has not performed
                            // * read, write, or both operation for a while.
                            //将事件传递给下一个handler 事件接收方法：userEventTiggered()
                            pipeline.addLast("heart",new IdleStateHandler(HEART_TIME_OUT,HEART_TIME_OUT,HEART_TIME_OUT, TimeUnit.SECONDS));
                            //pipeline.addLast("myIdlehandler",new SocketIdleHandler(socketConnections));
                            pipeline.addLast("myHandler",new SocketServerHandler(socketConnections,lightReturnMessage));
                        }
                    });

            System.out.println("netty 服务器创建成功...");

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public boolean sendMsgToChannel(String Macid, byte[] lightCommand) {
        Channel channel = socketConnections.get(Macid);
        if (channel != null) {
            channel.writeAndFlush(Unpooled.copiedBuffer(lightCommand));
            System.out.println("向设备："+Macid+"  发送控制信息："+ Arrays.toString(lightCommand)+" 成功！");
            return true;
        }else {
            System.out.println("灯控设备："+ Macid+"未连接！");
            return false;
        }
    }

    public static void main(String[] args) throws Exception {

        SocketServer socketServer = new SocketServer(9999,30);

        socketServer.run();

    }

}
