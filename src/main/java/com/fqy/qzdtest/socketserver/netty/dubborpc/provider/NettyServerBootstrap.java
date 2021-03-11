package com.fqy.qzdtest.socketserver.netty.dubborpc.provider;

import com.fqy.qzdtest.socketserver.netty.dubborpc.netty.NettyServer;

public class NettyServerBootstrap {

    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1",9999);
    }
}
