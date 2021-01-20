package socketserver.netty.httpserverf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest){
            System.out.println("msg 类型："+msg.getClass());
            System.out.println("客户端地址："+ctx.channel().remoteAddress());
            System.out.println("channel hascode:"+ctx.channel().hashCode()+"  pipeline hashcode:"+ctx.pipeline().hashCode());

            HttpRequest httpRequest = (HttpRequest) msg;
            String uri = httpRequest.uri();
            if ("/favicon.ico".equals(uri)){
                System.out.println("请求了/favivon.ico...");
                return;
            }

            ByteBuf content = Unpooled.copiedBuffer("hello,客户端。。。",CharsetUtil.UTF_16);

            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            ctx.writeAndFlush(httpResponse);
        }

    }
}
