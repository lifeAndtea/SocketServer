package socketserver.netty.dubborpc.provider;

import socketserver.netty.dubborpc.netty.NettyServer;

public class NettyServerBootstrap {

    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1",9999);
    }
}
