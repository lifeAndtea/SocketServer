package socketserver.netty.inoroutboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("server channel initializer...");
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder",new MyByteToLongDecoder());
        pipeline.addLast("encoder",new MyClientMessageToByteEncoder());
        pipeline.addLast("messageHandler",new MyServerMessageHandler());
    }
}
