package com.fqy.qzdtest.socketserver.netty.websocket;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServerSendDto implements Serializable {

	private static final long serialVersionUID = -7453297551797390215L;

	/**
	 * 状态码 200 成功，否则有errorMessage
	 */
	private Integer statusCode;

	private String message;

	/**
	 * 功能码
	 */
	private Integer functionCode;

	/**
	 * 错误消息
	 */
	private String errorMessage;
}
