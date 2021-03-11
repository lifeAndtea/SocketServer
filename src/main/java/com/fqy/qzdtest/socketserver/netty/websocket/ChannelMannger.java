package com.fqy.qzdtest.socketserver.netty.websocket;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Component
public class ChannelMannger extends ChannelInitializer {

    private int HEART_TIME_OUT = 30;

    private static ConcurrentMap<String, Channel> socketConnections = new ConcurrentHashMap<>();
    private static ConcurrentMap<String, String> lightReturnMessage = new ConcurrentHashMap<>();

    public ChannelMannger(){}

    public ChannelMannger(int timeOut){
        this.HEART_TIME_OUT = timeOut;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //pipeline.addLast("StringDecode",new StringDecoder());
        //pipeline.addLast("StringEncode",new StringEncoder());
        //pipeline.addLast("hexDecoder",new SocketHexDecoder());
        //===========

        //===========/*pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        //                            pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));*/

        //添加心跳监测机制
        //long readerIdleTime 未发生读操作时间
        // long writerIdleTime, 未发生写操作事件
        // long allIdleTime, 未发生任何事件事件
        // TimeUnit unit 单位
        //Triggers an {@link IdleStateEvent} when a {@link Channel} has not performed
        // * read, write, or both operation for a while.
        //将事件传递给下一个handler 事件接收方法：userEventTiggered()
        pipeline.addLast("heart",new IdleStateHandler(HEART_TIME_OUT,HEART_TIME_OUT,HEART_TIME_OUT, TimeUnit.SECONDS));
        //pipeline.addLast("myIdlehandler",new SocketIdleHandler(socketConnections));
        pipeline.addLast("myHandler",new SocketServerHandler(socketConnections,lightReturnMessage));
    }

    public boolean sendMsgToChannel(String Macid, byte[] lightCommand) {
        Channel channel = socketConnections.get(Macid);
        if (channel != null) {
            channel.writeAndFlush(Unpooled.copiedBuffer(lightCommand));
            System.out.println("向设备："+Macid+"  发送控制信息："+ Arrays.toString(lightCommand)+" 成功！");
            return true;
        }else {
            System.out.println("灯控设备："+ Macid+"未连接！");
            return false;
        }
    }

}
