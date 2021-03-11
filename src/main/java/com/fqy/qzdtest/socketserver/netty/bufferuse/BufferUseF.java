package com.fqy.qzdtest.socketserver.netty.bufferuse;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class BufferUseF {
    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.buffer(10);

        for (int i = 0 ;i < byteBuf.capacity();i++){
            byteBuf.writeByte(i);
        }

        //无需flip
        //有两个 readindex 和 writeindex

        for (int i = 0;i<byteBuf.capacity();i++){
            System.out.println(byteBuf.getByte(i));
            System.out.println(byteBuf.readByte());
        }

    }
}
