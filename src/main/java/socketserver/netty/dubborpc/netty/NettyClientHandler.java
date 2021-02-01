package socketserver.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context;
    private String message;
    private String result;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context=ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result=msg.toString();
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常:"+cause.getMessage());
    }

    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(message);
        wait();
        return result;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
