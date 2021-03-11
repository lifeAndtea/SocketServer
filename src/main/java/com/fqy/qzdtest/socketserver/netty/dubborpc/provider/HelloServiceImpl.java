package com.fqy.qzdtest.socketserver.netty.dubborpc.provider;

import com.fqy.qzdtest.socketserver.netty.dubborpc.publicapi.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String str) {
        if (str!=null){
            return "server get message ["+str +"]";
        }else {
            return "server get none message";
        }

    }
}
