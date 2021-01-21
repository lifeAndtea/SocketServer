package socketserver.netty.websocket;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocketServerHandler extends ChannelInboundHandlerAdapter {

    private static ConcurrentMap<String, Channel> socketConnections = new ConcurrentHashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf result = (ByteBuf) msg;
        byte[] bytemessage = new byte[result.readableBytes()];
        result.readBytes(bytemessage);
        String HexString = bytesToHexString(bytemessage);
        String basketballMessage = new String(bytemessage, StandardCharsets.UTF_8);
        System.out.println("client msg:" + HexString);
        System.out.println("client msg:" + basketballMessage);


        /**
         * 在这里根据与客户端约定的协议格式进行处理
         */
        List<String> messageList = StrUtil.splitTrim(basketballMessage, ",");
        String strHead = messageList.get(0);
        //log.info("报文信息:"+SocketConstant.bytesToHexString(byteMessageAfter));
        //log.info("报文信息:"+message);
        if ("01".equals(strHead)) {
            String strSN = messageList.get(3);
            //log.info("设备"+ strSN +"的心跳报");

            //将设备加入到map中

            socketConnections.put(strSN, ctx.channel());

            //心跳返回
            ServerSendDto dto = new ServerSendDto();
            dto.setFunctionCode(FunctionCodeEnum.HEART.getValue());
            ctx.channel().writeAndFlush(JSONUtil.toJsonStr(dto));

        } else if ("02".equals(strHead)) {

            String strSN = messageList.get(1);
            String strTime = messageList.get(2);
            //log.info("设备"+ strSN +"的数据报，进球时间：" + strTime);


            if (!socketConnections.containsKey(strSN)) {
                socketConnections.put(strSN, ctx.channel());
            }

            DateTime goalTime = DateUtil.parse(strTime, "yyMMddHHmmss");
            goalTime = goalTime.getTime() > DateTime.now().getTime() ? DateTime.now() : goalTime;

            String starttime = DateUtil.offsetSecond(goalTime, -10).toString();
            String endtime = DateUtil.offsetSecond(goalTime, 5).toString();

            VideoSensor videosensor = new VideoSensor();
            videosensor.setDevice(strSN);
            videosensor.setStarttime(starttime);
            videosensor.setEndtime(endtime);
            HttpClientUtil.post("http://47.99.83.184:9090/zhibo/saveGoalTime", JSONUtil.toJsonStr(videosensor));

        } else if (bytemessage[0] == (byte) 0xa5) {

            //log.info("灯控信息String："+SocketConstant.bytesToHexString(byteMessageAfter));
            int LIGHT_HRARTBEAT_PACKAGE_LENGTH = 18;
            if (bytemessage.length == LIGHT_HRARTBEAT_PACKAGE_LENGTH) {

                socketConnections.put(bytesToHexString(bytemessage).substring(10, 34), ctx.channel());
                ctx.channel().writeAndFlush(bytemessage);
            } else {
                "============================================================="
                SocketAttr socketAttr1 = socketConnections.get(bytesToHexString(bytemessage).substring(10, 34));
                socketAttr1.setReturnMessage(bytesToHexString(bytemessage));
            }
            //log.info("receDto:"+receiveDto.toString());
        }else if (basketballMessage.length() == 15) {
            //只有设备id的情况
            //如果为空，则只有设备id，并设置当前时间
            DateTime goalTime = new DateTime();
            String starttime = DateUtil.offsetSecond(goalTime, -10).toString();
            String endtime = DateUtil.offsetSecond(goalTime, 5).toString();

            VideoSensor videosensor = new VideoSensor();
            videosensor.setDevice(basketballMessage);
            videosensor.setStarttime(starttime);
            videosensor.setEndtime(endtime);
            HttpClientUtil.post("http://47.99.83.184:9090/zhibo/saveGoalTime", JSONUtil.toJsonStr(videosensor));

        } else {
            //log.info("不明报文");
        }


    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        SocketAttr socketAttr = new SocketAttr();
        socketAttr.setChannel(ctx.channel());
        socketConnections.
        System.out.println("连接断开：");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
    }

    /**
     * byte数组转String
     *
     * @param bytes
     * @return
     */
    public String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


}
