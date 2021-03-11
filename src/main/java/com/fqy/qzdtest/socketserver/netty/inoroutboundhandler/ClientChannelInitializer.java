package com.fqy.qzdtest.socketserver.netty.inoroutboundhandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ClientChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        System.out.println("channel initializer...");
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("messageEncoder",new MyClientMessageToByteEncoder());
        pipeline.addLast("decoder",new MyByteToLongDecoder());
        pipeline.addLast("clientMessagehandler",new MyClientMessagehandler());
    }
}
