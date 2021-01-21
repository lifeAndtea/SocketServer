package socketserver.netty.websocket;

import io.netty.channel.Channel;
import lombok.Data;

@Data
public class SocketAttr {
    private Channel channel;
    private String returnMessage = null;
}
