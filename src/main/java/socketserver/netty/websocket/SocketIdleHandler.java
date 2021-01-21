package socketserver.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public class SocketIdleHandler extends ChannelInboundHandlerAdapter {

    private ConcurrentMap<String, Channel> socketConnections;

    public SocketIdleHandler(ConcurrentMap<String, Channel> socketConnections) {
        this.socketConnections = socketConnections;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()) {
                case READER_IDLE:
                    System.out.println("未发生读操作。");
                    break;
                case WRITER_IDLE:
                    System.out.println("未发生写操作。");
                    break;
                case ALL_IDLE:
                    System.out.println("超时未发生任何操作,断开连接。");
                    ctx.close();
                    break;
            }

        }

    }

    //根据value获取key
    private <K, V> K getKeyByLoop(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
