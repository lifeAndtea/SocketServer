package socketserver.netty.websockettest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import socketserver.netty.websocket.SocketIdleHandler;
import socketserver.netty.websocket.SocketServer;
import socketserver.netty.websocket.SocketServerHandler;

import java.util.concurrent.TimeUnit;

public class WebSocketServer {

    private int port;

    public WebSocketServer(int port){
        this.port = port;
    }

    public void run() throws Exception {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            //加http编码和解吗器
                            pipeline.addLast("httpCodec",new HttpServerCodec());
                            //是以块方式写
                            pipeline.addLast(new ChunkedWriteHandler());
                            //http传输过程中是分段，在此可将多个段聚合在一起
                            pipeline.addLast(new HttpObjectAggregator(8192));

                            /**
                             * 网络中以帧 （frame）的形式传输
                             * WebSocketFrame 下面有六个子类
                             * 浏览器请求：ws://localhost:9999/hello 表示请求的uri
                             * WebSocketServerProtocolHandler 是将http协议升级为ws协议，保持长连接
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            //自己的handler
                            pipeline.addLast(new MyTestWebSocketFrame());
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

        WebSocketServer webSocketServer = new WebSocketServer(9999);

        webSocketServer.run();

    }
}
