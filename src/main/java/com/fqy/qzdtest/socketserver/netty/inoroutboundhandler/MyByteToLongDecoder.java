package com.fqy.qzdtest.socketserver.netty.inoroutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("decoder .....");

        //ReplayDecoder   不需要判断(readableBytes())  但存在缺点
        //1.不支持所有bytebuf方法
        //2.数据复杂或这网络缓慢时，速度变慢

        //netty提供其他一系列的解码器


        //log4j整合
        if (byteBuf.readableBytes()>=8){
            list.add(byteBuf.readLong());
        }
    }
}
