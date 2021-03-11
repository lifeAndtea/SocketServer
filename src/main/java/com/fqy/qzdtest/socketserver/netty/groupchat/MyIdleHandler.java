package com.fqy.qzdtest.socketserver.netty.groupchat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyIdleHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){

            IdleStateEvent event = (IdleStateEvent) evt;

            switch (event.state()){
                case READER_IDLE:
                    System.out.println("未发生读操作。");
                    break;
                case WRITER_IDLE:
                    System.out.println("未发生写操作。");
                    break;
                case ALL_IDLE:
                    System.out.println("未发生任何操作。");
                    break;
            }

        }

    }
}
