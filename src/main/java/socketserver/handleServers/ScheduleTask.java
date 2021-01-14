package socketserver.handleServers;///*
//package com.github.tonydeng.socketserver.handleServers;
//
//import com.balledu.video.mapper.VideoZbDao;
//import com.github.tangyi.common.core.utils.HttpClientUtil;
//import com.github.tangyi.video.api.module.video.VideoZb;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//

/*
* List<VideoZb> getAllZbId();
*
    <select id="getAllZbId" resultType="com.github.tangyi.video.api.module.video.VideoZb">
        SELECT
        <include refid="videozbColumns"/>
        FROM video_zb a
        WHERE a.del_flag = 0
        GROUP BY zb_id
    </select>
* */
//@Component
//public class ScheduleTask {
//
//    protected Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private VideoZbDao videoZbDao;
//
//    @Autowired
//    private SysConfig sysConfig;
//
//
//    */
///**
//     * 每天23:30关闭直播推流通道
//     *//*
//
//    @Scheduled(cron = "0 30 23 1/1 * ?")
//    //@Scheduled(cron = "0 0/4 * * * ?")
//    public void closeZbPassway(){
//        List<VideoZb> allZbId = videoZbDao.getAllZbId();
//        Map<String, String> httpValue = new HashMap<>();
//        for (VideoZb videoZb : allZbId) {
//            httpValue.put("id",videoZb.getZbId());
//            httpValue.put("actived","false");
//            String result;
//            int i = 0;
//            do {
//                if (i>3){
//                    logger.error("关闭直播推流失败，失败直播id："+videoZb.getZbId());
//                    break;
//                }
//                result = HttpClientUtil.doPost(sysConfig.getZbUrl() + "/live/turn/actived", httpValue);
//                //System.out.println(result);
//                i++;
//            }while (!result.contains("200"));
//        }
//    }
//
//    */
///**
//     * 每天09:00打开直播推流通道
//     *//*
//
//    @Scheduled(cron = "0 0 9 1/1 * ?")
//    //@Scheduled(cron = "0 0/3 * * * ?")
//    public void openZbPassway(){
//        List<VideoZb> allZbId = videoZbDao.getAllZbId();
//        Map<String, String> httpValue = new HashMap<>();
//        for (VideoZb videoZb : allZbId) {
//            httpValue.put("id",videoZb.getZbId());
//            httpValue.put("actived","true");
//            String result;
//            int i = 0;
//            do {
//                if (i>3){
//                    logger.error("开启直播推流失败，失败直播id："+videoZb.getZbId());
//                    break;
//                }
//                result = HttpClientUtil.doPost(sysConfig.getZbUrl() + "/live/turn/actived", httpValue);
//                //System.out.println(result);
//                i++;
//            }while (!result.contains("200"));
//        }
//    }
//}
//*/
