package com.fqy.qzdtest.socketserver.netty.test;


import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
/*import com.balledu.video.mapper.VideoMatchDao;
import com.balledu.video.mapper.VideoSensorDao;
import com.balledu.video.service.video.VideoDictionaryService;
import com.balledu.video.service.video.VideoMatchService;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.utils.HttpClientUtil;
import com.github.tangyi.video.api.module.video.VideoDictionary;
import com.github.tangyi.video.api.module.video.VideoMatch;
import com.github.tangyi.video.api.module.video.VideoSensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;*/

import java.util.*;

//@Component
public class ScheduleTask {

   /* protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private VideoMatchDao videoMatchDao;

    @Autowired
    private VideoMatchService videoMatchService;

    @Autowired
    private VideoSensorDao videoSensorDao;

    @Autowired
    private VideoDictionaryService videoDictionaryService;

    @Autowired
    private SysConfig sysConfig;

    *//**
     * 每半个小时创建比赛
     *//*
    //@Scheduled(cron = "0 15,45 * * * ?")
    @Scheduled(cron = "0 0/2 * * * ?")
    public void createMatchHalfAnHour(){
        //确定时间
        //Date now = new Date();
        Date now = new Date(121, 0, 21, 16, 15);
        DateTime startTime = DateUtil.offsetMinute(now, -45);
        String startTimeStr = startTime.toString();
        DateTime endTime = DateUtil.offsetMinute(now, -15);
        String endTimeStr = endTime.toString();
        //查询安装了设备的场馆
        List<VideoMatch> videoMatches = videoMatchDao.queryEveryZBid();
        //将url存入集合中，方便调用
        List<String> urlList = new ArrayList<>();

        for (VideoMatch videoMatch : videoMatches) {
            List<String> videoSensors = videoSensorDao.queryVideosByTimeSlot(videoMatch.getZbId(), startTimeStr, endTimeStr);
            if (videoSensors.size()==0){
                logger.info("场馆:{},{}到{}时间段视频数为0，不创建比赛！",videoMatch.getArenaName(),startTimeStr,endTimeStr);
                continue;
            }
            //查询场馆自己设置的每场赛不少于多少进球才创建比赛  若未设置，默认30
            VideoDictionary arenaSetting = videoDictionaryService.getVideoDictionary(videoMatch.getArenaId(),videoMatch.getZbId());
            int minShoots = null!=arenaSetting?arenaSetting.getHitNum():30;
            int goalInterval = null!=arenaSetting?arenaSetting.getIntervalTime():7;
            if (videoSensors.size()<minShoots){
                //数量少于设定值
                logger.info("场馆:{},{}到{}时间段视频数过少，不创建比赛！",videoMatch.getArenaName(),startTimeStr,endTimeStr);
            }else {
                VideoSensor videoSensor1 = new VideoSensor();
                VideoSensor videoSensor2 = new VideoSensor();
                String firstSensorId = videoSensors.get(0);
                String lastSensorId = videoSensors.get(videoSensors.size()-1);
                videoSensor1.setId(firstSensorId);
                VideoSensor videoSensorFirst = videoSensorDao.get(videoSensor1);
                videoSensor2.setId(lastSensorId);
                VideoSensor videoSensorlast = videoSensorDao.get(videoSensor2);
                //videomatch表中，global中数量太多会出错，分为多个写入
                //List<List<String>> videoSensorlists = splitList(videoSensors, 40);
                videoMatch.setStarttime(startTimeStr);
                videoMatch.setEndtime(endTimeStr);
                videoMatch.setTimeSolt(videoSensorFirst.getStarttime(),videoSensorlast.getEndtime());
                videoMatch.setMatchType(VideoMatch.TYPE_0);
                videoMatch.setTeamA("散客");
                videoMatch.setTeamB("散客");
                videoMatch.setName(videoMatch.getName()+startTime.toString(" HH:mm")+"-"+endTime.toString("HH:mm"));
                List<String> videoSensorList =videoSensors.size()>190?videoSensors.subList(0, 190):videoSensors;
                videoMatch.setGoal(rejectInvalidVideo(videoSensorList,goalInterval));
                videoMatch.setCommonValue("QZD", CommonConstant.SYS_CODE_VIDEO);
                logger.info("videoMatch:{}",videoMatch.toString());
                videoMatchService.save(videoMatch);
                if ("arena08".equals(videoMatch.getArenaId())){
                    logger.info("本时间段存在金融港视频:{}",videoMatch.getUrl());
                    urlList.add(videoMatch.getUrl());
                }
            }
        }
        if (urlList.size()>0){
            new Thread(() -> {
                String post = HttpUtil.post(sysConfig.getZbUrl()+"/conf/state/top",new HashMap<>());
                int CpuPercent = parseResponse(post);
                logger.info("当前cpu占用为：{}",CpuPercent);
                if (CpuPercent >90){
                    logger.info("当前cpu占用为{},占用过高，不调easydss接口生成缓存！", CpuPercent);
                    Thread.currentThread().stop();
                }
                //===========================================
                int currentHour = Integer.parseInt(startTime.toString("HH"));
                if (currentHour>=19&&currentHour<=22){
                    for (String url : urlList) {
                        try {
                            Thread.sleep(5*1000L);
                            HttpClientUtil.doGetNoResponse(url);
                            logger.info("调用easydss地址{}缓存成功。",url);
                        } catch (InterruptedException e) {
                            logger.info("easydss地址:{}调用异常:{}"+url,e.getMessage());
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }).start();
        }
    }*/

    public  String rejectInvalidVideo(List<String> videoIds,int interval){
        StringBuilder goal = new StringBuilder();
        long nowTime = new DateTime(videoIds.get(0).substring(2,16), "yyyyMMddHHmmss").getTime();
        long currentTime;
        for (String videoId : videoIds) {
            currentTime=new DateTime(videoId.substring(2,16), "yyyyMMddHHmmss").getTime();
            if (Math.abs(currentTime-nowTime)>interval*1000){
                goal.append(videoId).append(",");
                nowTime=currentTime;
            }
        }
        return goal.substring(0, goal.length() - 1);
    }

    public int parseResponse(String s){
        JSONObject jsonObject = JSONUtil.parseObj(s);
        List<Map<String,Object>> list= (List<Map<String,Object>>) jsonObject.get("cpuData");
        int percent = 0;
        for (int i = 0; i < 2; i++) {
            percent+=(double) list.get(i).get("use")*100;

        }
        //System.out.println(percent/2);
        return percent/2;
    }

    /*每半个小时创建比赛
    @Scheduled(cron = "0 15,45 * * * ?")
    //@Scheduled(cron = "0 0/2 * * * ?")
    public void createMatch(){
        //确定时间
        Date now = new Date();
        //Date now = new Date(121, 0, 21, 16, 15);
        DateTime startTime = DateUtil.offsetMinute(now, -45);
        String startTimeStr = startTime.toString();
        DateTime endTime = DateUtil.offsetMinute(now, -15);
        String endTimeStr = endTime.toString();
        //查询安装了设备的场馆
        List<VideoMatch> videoMatches = videoMatchDao.queryEveryZBid();
        for (VideoMatch videoMatch : videoMatches) {
            List<String> videoSensors = videoSensorDao.queryVideosByTimeSlot(videoMatch.getZbId(), startTimeStr, endTimeStr);
            if (videoSensors.size()==0){
                logger.info("场馆:{},{}到{}时间段视频数为0，不创建比赛！",videoMatch.getArenaName(),startTimeStr,endTimeStr);
                continue;
            }
            //查询场馆自己设置的每场赛不少于多少进球才创建比赛  若未设置，默认20
            VideoDictionary arenaSetting = videoDictionaryDao.getByArenaId(videoMatch.getArenaId());
            int minShoots = arenaSetting!=null?arenaSetting.getHitNum():20;
            if (videoSensors.size()<minShoots){
                //数量少于设定值
                logger.info("场馆:{},{}到{}时间段视频数过少，不创建比赛！",videoMatch.getArenaName(),startTimeStr,endTimeStr);
            }else {
                //videomatch表中，global中数量太多会出错，分为多个写入
                List<List<String>> videoSensorlists = splitList(videoSensors, 40);
                videoMatch.setStarttime(startTimeStr);
                videoMatch.setEndtime(endTimeStr);
                videoMatch.init();
                videoMatch.setMatchType(VideoMatch.TYPE_0);
                videoMatch.setTeamA("散客");
                videoMatch.setTeamB("散客");
                videoMatch.setName(videoMatch.getName()+startTime.toString(" HH:mm")+"-"+endTime.toString(" HH:mm"));
                for (List<String> videoSensorlist : videoSensorlists) {
                    videoMatch.setGoal(String.join(",",videoSensorlist));
                    videoMatch.setCommonValue("QZD", CommonConstant.SYS_CODE_VIDEO);
                    logger.info("videoMatch:{}",videoMatch.toString());
                    videoMatchService.save(videoMatch);
                }

            }
        }
    }*/

    /**
     * 按照固定长度分割list
     * @param list
     * @param groupSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int groupSize){
        int length = list.size();
        // 计算可以分成多少组
        int num = ( length + groupSize - 1 )/groupSize ;
        List<List<T>> newList = new ArrayList<>(num);
        if (num==1){
            //只有一组时
            newList.add(list);
            return newList;
        }
        for (int i = 0; i < num; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = Math.min((i + 1) * groupSize, length);
            newList.add(list.subList(fromIndex,toIndex)) ;
        }
        return  newList ;
    }


    /*public static void doGetNoResponse(String url) {
        HttpClient client = createSSLClientDefault();
        HttpGet httpRequst = new HttpGet(url);
        try {
            client.execute(httpRequst);           // 其中HttpGet是HttpUriRequst的子类
            httpRequst.abort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



}

