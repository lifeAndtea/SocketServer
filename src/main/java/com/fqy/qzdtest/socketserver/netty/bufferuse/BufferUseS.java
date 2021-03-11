package com.fqy.qzdtest.socketserver.netty.bufferuse;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class BufferUseS {
    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("helloworld!", Charset.forName("utf-8"));

        if (byteBuf.hasArray()){

            //获取内容
            byte[] array = byteBuf.array();
            String result = new String(array, Charset.forName("utf-8")).trim();
            System.out.println(result);

            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            byteBuf.readByte();
            System.out.println(byteBuf.readableBytes());
        }

    }
}
