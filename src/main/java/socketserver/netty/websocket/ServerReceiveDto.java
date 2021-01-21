package socketserver.netty.websocket;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServerReceiveDto implements Serializable {

	private static final long serialVersionUID = 6600253865619639317L;

	/**
	 * 功能码 0 心跳 1 登录 2 注销 3 发送消息
	 */
	private Integer functionCode;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 约定是string的消息体
	 */
	private String message;

}
