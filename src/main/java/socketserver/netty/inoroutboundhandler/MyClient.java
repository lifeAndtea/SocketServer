package socketserver.netty.inoroutboundhandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());


            ChannelFuture localhost = bootstrap.connect("localhost", 9999).sync();
            localhost.channel().closeFuture().sync();


        }finally {
            workGroup.shutdownGracefully();
        }
    }
}
