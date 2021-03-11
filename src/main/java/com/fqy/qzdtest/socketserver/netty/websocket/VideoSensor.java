package com.fqy.qzdtest.socketserver.netty.websocket;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VideoSensor extends BaseEntity<VideoSensor> {

    private static final long serialVersionUID = 1L;

    private String zbId;           // 直播ID
    private String device;         // 设备编号
    private String arenaId;        // 场馆编号
    private String starttime;      // 推流开始, YYYY-MM-DD HH:mm:ss
    private String endtime;        // 推流结束, YYYY-MM-DD HH:mm:ss
    private String url;            // 播放地址
    private String durl;           // 下载地址
    private String snap;           // 快照
    private String music;          // 音乐
    private Integer length;        // 时长
    private String remark;         // 文案

    private String groupName;
    private String zbName;

    private Integer clickNum;//点击量
    private Integer playNum;//播放量 --播放end的数量
    private Integer downloadNum;//下载量
    private Integer shareNum;//分享次数

    private String isTopTenVideo;//是否是十佳视频，0-非十佳；1-十佳视频
    private String alreadyBuy;//是否已经购买,y-已购，n-未购
    private String murl;  //特效视频播放地址
    private String mdurl; //特效视频下载地址

    public static final String TOP_TEN_VIDEO = "1";

}