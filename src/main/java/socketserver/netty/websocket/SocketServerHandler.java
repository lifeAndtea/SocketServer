package socketserver.netty.websocket;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public class SocketServerHandler extends ChannelInboundHandlerAdapter {

    private  ConcurrentMap<String, Channel> socketConnections;
    private  ConcurrentMap<String, String> lightReturnMessage;

    public SocketServerHandler(ConcurrentMap<String, Channel> socketConnections, ConcurrentMap<String, String> lightReturnMessage) {
        this.socketConnections = socketConnections;
        this.lightReturnMessage = lightReturnMessage;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf result = (ByteBuf) msg;
        byte[] bytemessage = new byte[result.readableBytes()];
        result.readBytes(bytemessage);
        String HexString = bytesToHexString(bytemessage);
        String basketballMessage = new String(bytemessage, StandardCharsets.UTF_8);
        System.out.println("client msg:(16进制)" + HexString);
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
            System.out.println("感应器设备" + strSN + "的心跳报");
            //将设备加入到map中

            if (!socketConnections.containsKey(strSN)) {
                socketConnections.put(strSN, ctx.channel());
            }

            //心跳返回
            ServerSendDto dto = new ServerSendDto();
            dto.setFunctionCode(FunctionCodeEnum.HEART.getValue());
            String jsonStr = JSONUtil.toJsonStr(dto);
            byte[] bytes = jsonStr.getBytes(StandardCharsets.UTF_8);
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(bytes));

        } else if ("02".equals(strHead)) {

            String strSN = messageList.get(1);
            String strTime = messageList.get(2);
            //log.info("设备"+ strSN +"的数据报，进球时间：" + strTime);
            System.out.println("感应器设备" + strSN + "的数据报，进球时间：" + strTime);

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
            HttpClientUtil.post("http://:9090/zhibo/saveGoalTime", JSONUtil.toJsonStr(videosensor));

        } else if (bytemessage[0] == (byte) 0xa5) {

            //log.info("灯控信息String："+SocketConstant.bytesToHexString(byteMessageAfter));
            int LIGHT_HRARTBEAT_PACKAGE_LENGTH = 18;
            if (bytemessage.length == LIGHT_HRARTBEAT_PACKAGE_LENGTH) {
                String Macid = HexString.substring(10, 34);
                System.out.println("灯控设备" + Macid + "的心跳报");
                if (!socketConnections.containsKey(Macid)) {
                    socketConnections.put(Macid, ctx.channel());
                }
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer(bytemessage));
            } else {
                //"============================灯控返回信息================================="
                String Macid = HexString.substring(10, 34);
                if (!socketConnections.containsKey(Macid)) {
                    socketConnections.put(Macid, ctx.channel());
                }
                System.out.println("灯控设备:" + Macid + " 返回信息" + HexString);
                lightReturnMessage.put(Macid, HexString);
            }
            //log.info("receDto:"+receiveDto.toString());
        } else if (basketballMessage.length() == 15) {
            //只有设备id的情况
            //如果为空，则只有设备id，并设置当前时间
            System.out.println("蓝框感应器设备(只有设备id)" + basketballMessage + "的进球报。");

            if (!socketConnections.containsKey(basketballMessage)) {
                socketConnections.put(basketballMessage, ctx.channel());
            }

            DateTime goalTime = new DateTime();
            String starttime = DateUtil.offsetSecond(goalTime, -10).toString();
            String endtime = DateUtil.offsetSecond(goalTime, 5).toString();

            VideoSensor videosensor = new VideoSensor();
            videosensor.setDevice(basketballMessage);
            videosensor.setStarttime(starttime);
            videosensor.setEndtime(endtime);
            HttpClientUtil.post("http:///zhibo/saveGoalTime", JSONUtil.toJsonStr(videosensor));

        } else {
            //log.info("不明报文");
            System.out.println("不明报文！");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        String Macid = getKeyByLoop(socketConnections, ctx.channel());
        if (Macid != null) {
            socketConnections.remove(Macid);
            System.out.println("发生异常，异常设备id为：" + Macid + "  异常信息为：" + cause.getMessage());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("设备连接！");
        for (String s : socketConnections.keySet()) {
            System.out.println("在线列表："+s);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println( "Inactive!");
        String Macid = getKeyByLoop(socketConnections, ctx.channel());
        if (Macid != null) {
            socketConnections.remove(Macid);
            System.out.println("设备连接断开，设备id：" + Macid);
        }
        for (String s : socketConnections.keySet()) {
            System.out.println("在线列表："+s);
        }
    }


    /**
     * byte数组转String
     *
     * @param bytes
     * @return
     */
    public String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
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
}
