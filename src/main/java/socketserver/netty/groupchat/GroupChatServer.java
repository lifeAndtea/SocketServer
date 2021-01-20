package socketserver.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


public class GroupChatServer {

    private int port;

    public GroupChatServer(int port){
        this.port = port;
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
                            pipeline.addLast("StringDecode",new StringDecoder());
                            pipeline.addLast("StringEncode",new StringEncoder());
                            pipeline.addLast("myHandler",new ServerChatHandler());
                            //添加心跳监测机制
                            //long readerIdleTime 未发生读操作时间
                            // long writerIdleTime, 未发生写操作事件
                            // long allIdleTime, 未发生任何事件事件
                            // TimeUnit unit 单位
                            //Triggers an {@link IdleStateEvent} when a {@link Channel} has not performed
                            // * read, write, or both operation for a while.
                            //将事件传递给下一个handler 事件接收方法：userEventTiggered()
                            pipeline.addLast("heart",new IdleStateHandler(2,5,7, TimeUnit.SECONDS));
                            pipeline.addLast("myIdlehandler",new MyIdleHandler());
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


    public static void main(String[] args) throws Exception {

        GroupChatServer groupChatServer = new GroupChatServer(9999);

        groupChatServer.run();

    }
}
