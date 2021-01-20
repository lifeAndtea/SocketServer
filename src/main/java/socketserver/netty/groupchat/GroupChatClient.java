package socketserver.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.Socket;
import java.util.Scanner;

public class GroupChatClient {

    private final Integer port;

    private final String host;

    public GroupChatClient(String host,Integer port){
        this.port = port;
        this.host = host;
    }

    public void run() throws Exception {

        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("StringDecode", new StringDecoder());
                            pipeline.addLast("StringEncode", new StringEncoder());
                            pipeline.addLast("maHandler", new ClientChatHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            System.out.println("---------" + channel.localAddress() + "----------");

            //输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }
        }finally {
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        GroupChatClient groupChatClient= new GroupChatClient("127.0.0.1",9999);
        groupChatClient.run();
    }




}
