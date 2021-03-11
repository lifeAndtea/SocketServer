/*
package com.fqy.qzdtest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;*/
/*
import com.github.tangyi.common.core.utils.HttpClientUtil;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;*//*


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class GetLoginClass {

    public static void main(String[] args) throws Exception {
        //getToken();

        //getTokenByUser();

        //httpRequestTest();

        //String s = downloadVideosByGet("http://localhost:10080/record/video/play/f7goHaAGg/20201209050003/20201209050500?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE3MTMxODUsInB3IjoiMjEyMzJmMjk3YTU3YTVhNzQzODk0YTBlNGE4MDFmYzMiLCJ0bSI6MTYxMTYyNjc4NSwidW4iOiJhZG1pbiJ9.CnFAF86mdwi8AvtKyElKt8dToz-kY5o6ioOjQhok-Fk", "D:\\test.mp4");

        //upLoadVideo("D:\\ttest.mp4","/test","http://localhost:10080");

        //uploadFile("D:\\ttest.mp4","test","http://localhost:10080");

        //md5Password("admin");

        //insertNewFolder();

        //testQueryStatus();

        //sqlLengthTest();

        //rejectInvalidVideo(null);

        //setTime();

        //compareTime(null);

        //testActive();

        //智多星
        //cpu();

        //testGetUrl();


        getRequestTest();


    }

    public static void getSid() throws Exception {
        URL url = new URL("http://localhost:10080/login");
        //发起POST请求，并传递username，password参数（需要md5加密）
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        String content = "username=admin&password=21232f297a57a5a743894a0e4a801fc3";
        out.writeBytes(content);
        out.flush();
        out.close();
        Map<String, List<String>> headerFields = conn.getHeaderFields();
        Set<String> headerFieldsSet = headerFields.keySet();
        Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();

        while (hearerFieldsIter.hasNext()) {
            String headerFieldKey = hearerFieldsIter.next();

            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
                List<String> headerFieldValue = headerFields.get(headerFieldKey);
                for (String headerValue : headerFieldValue) {
                    String[] fields = headerValue.split(";\\s*");
                    for (int j = 0; j < fields.length; j++) {
                        if (fields[j].indexOf('=') > 0) {
                            String[] f = fields[j].split("=");
                            if ("Expires".equalsIgnoreCase(f[0])) {
                                System.out.println("Expires:" + f[1]);
                            }
                            else if ("Max-Age".equalsIgnoreCase(f[0])) {
                                System.out.println("Max-Age:" + f[1]);
                            }else if ("sid".equalsIgnoreCase(f[0])) {  //获取sid
                                System.out.println("sid:" + f[1]);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void getTokenByUser(){
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("username","admin");
        parms.put("password","21232f297a57a5a743894a0e4a801fc3");
        String result = HttpUtil.post("localhost:10080/login", parms);
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String token = jsonObject.getString("token");
        System.out.println("token:"+token);
    }

    public static void getToken() throws Exception {
        URL url = new URL("http://localhost:10080/login");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        String content = "username=admin&password=21232f297a57a5a743894a0e4a801fc3";
        out.writeBytes(content);
        out.flush();
        out.close();
        conn.connect();
        StringBuffer sbf = new StringBuffer();
        InputStream is = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String strRead = null;
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
            sbf.append("\r\n");
        }
        reader.close();
        System.out.println(sbf.toString());
        JSONObject jsonObject = JSONObject.parseObject(String.valueOf(sbf));
        String token = jsonObject.getString("token");
        System.out.println(token);
    }

    public static void httpRequestTest(){
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("id","RZomDyfMR");
        parms.put("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE3MTMxODUsInB3IjoiMjEyMzJmMjk3YTU3YTVhNzQzODk0YTBlNGE4MDFmYzMiLCJ0bSI6MTYxMTYyNjc4NSwidW4iOiJhZG1pbiJ9.CnFAF86mdwi8AvtKyElKt8dToz-kY5o6ioOjQhok-Fk");

        //仅cookie
        //String result = HttpRequest.post("http://localhost:10080/vod/get").cookie("2VVECRLGg").form(parms).execute().body();

        //cookie+token(header)
        //String result = HttpRequest.post("http://localhost:10080/vod/get").cookie("2VVECRLGg").header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE3MTMxODUsInB3IjoiMjEyMzJmMjk3YTU3YTVhNzQzODk0YTBlNGE4MDFmYzMiLCJ0bSI6MTYxMTYyNjc4NSwidW4iOiJhZG1pbiJ9.CnFAF86mdwi8AvtKyElKt8dToz-kY5o6ioOjQhok-Fk")

        //token(header)
        //String result = HttpRequest.post("http://localhost:10080/vod/get").header("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE3MTMxODUsInB3IjoiMjEyMzJmMjk3YTU3YTVhNzQzODk0YTBlNGE4MDFmYzMiLCJ0bSI6MTYxMTYyNjc4NSwidW4iOiJhZG1pbiJ9.CnFAF86mdwi8AvtKyElKt8dToz-kY5o6ioOjQhok-Fk")

        //token(parm)  success
        String result = HttpRequest.post("http://localhost:10080/vod/get").form(parms).execute().body();
        System.out.println(result);
    }

    public static String downloadVideosByGet(String httpUrl, String saveFile) throws IOException {
        // 1.下载网络文件
        int byteRead;
        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }

        //2.获取链接
        URLConnection conn = url.openConnection();
        //3.输入流
        InputStream inStream = conn.getInputStream();
        //3.写入文件
        FileOutputStream fs = new FileOutputStream(saveFile);

        byte[] buffer = new byte[1024];
        while ((byteRead = inStream.read(buffer)) != -1) {
            fs.write(buffer, 0, byteRead);
        }
        inStream.close();
        fs.close();
        if (!new File(saveFile).exists()) {
            throw new IOException("文件下载失败:" + saveFile);
        }
        return saveFile;
    }

    public static String upLoadVideo(String videoAbsolutePath, String videoUploadPath, String serverAddress) throws Exception {

        String result = null;
        FileSystemResource fileSystemResource;
        try {

            fileSystemResource = new FileSystemResource(videoAbsolutePath);
            if (!fileSystemResource.exists()) {//文件不存在处理
                System.out.println("文件不存在");
                throw new FileNotFoundException(fileSystemResource.getDescription());
            }
            MediaType type = MediaType.parseMediaType("multipart/form-data");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(type);
            //headers.set("Cookie", cookies);//session用于验证身份
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
            form.add("file", fileSystemResource);
            //form.add("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE3MTMxODUsInB3IjoiMjEyMzJmMjk3YTU3YTVhNzQzODk0YTBlNGE4MDFmYzMiLCJ0bSI6MTYxMTYyNjc4NSwidW4iOiJhZG1pbiJ9.CnFAF86mdwi8AvtKyElKt8dToz-kY5o6ioOjQhok-Fk");//用于验证身份
            form.add("path", videoUploadPath);
            HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(serverAddress + "/vod/upload", files, String.class);//发送
            //此处要添加错误处理
            result = responseEntity.getBody().toString();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static void uploadFile(String videoAbsolutePath, String videoUploadPath, String serverAddress){
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE3MTMxODUsInB3IjoiMjEyMzJmMjk3YTU3YTVhNzQzODk0YTBlNGE4MDFmYzMiLCJ0bSI6MTYxMTYyNjc4NSwidW4iOiJhZG1pbiJ9.CnFAF86mdwi8AvtKyElKt8dToz-kY5o6ioOjQhok-Fk");
        parms.put("file", FileUtil.file(videoAbsolutePath));
        parms.put("path",videoUploadPath);
        String post = HttpUtil.post(serverAddress + "/vod/upload", parms);
        System.out.println(post);
    }

    public static void md5Password(String password){
        String s = SecureUtil.md5(password);
        System.out.println(s);
    }

    public static void insertNewFolder(){
        HashMap<String, String> httpValue = new HashMap<>();
        httpValue.put("folder", "test/bbb");
        httpValue.put("name", "test");
        httpValue.put("sort", "1");
        httpValue.put("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE3MTMxODUsInB3IjoiMjEyMzJmMjk3YTU3YTVhNzQzODk0YTBlNGE4MDFmYzMiLCJ0bSI6MTYxMTYyNjc4NSwidW4iOiJhZG1pbiJ9.CnFAF86mdwi8AvtKyElKt8dToz-kY5o6ioOjQhok-Fk");

        String result = HttpClientUtil.doPost( "http://localhost:10080/vod/subcatalogadd", httpValue);
        System.out.println(result);
    }

    public static void testQueryStatus(){
        String s2 = HttpClientUtil.doGet("http://localhost:10080/vod/progress?id=pA1qmkYGR&token="+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE3MTMxODUsInB3IjoiMjEyMzJmMjk3YTU3YTVhNzQzODk0YTBlNGE4MDFmYzMiLCJ0bSI6MTYxMTYyNjc4NSwidW4iOiJhZG1pbiJ9.CnFAF86mdwi8AvtKyElKt8dToz-kY5o6ioOjQhok-Fk");
        System.out.println(s2);
    }

    public static void sqlLengthTest(){
        String s1 = "JQ20210121153405293,JQ20210121153450200,JQ20210121153500441,JQ20210121153511828,JQ20210121153519037,JQ20210121153540702,JQ20210121153555955,JQ20210121153606547,JQ20210121153632576,JQ20210121153732305,JQ20210121153837592,JQ20210121153903370,JQ20210121153914687,JQ20210121153935374,JQ20210121153950036,JQ20210121154018788,JQ20210121154054310,JQ20210121154113537,JQ20210121154205097,JQ20210121154224387,JQ20210121154250389,JQ20210121154258856,JQ20210121154329808,JQ20210121154350995,JQ20210121154359450,JQ20210121154446200,JQ20210121154524443,JQ20210121154536135,JQ20210121154628967,JQ20210121154656743,JQ20210121154739967,JQ20210121154750119,JQ20210121154759732,JQ20210121154847568,JQ20210121154859597,JQ20210121154934774,JQ20210121155051060,JQ20210121155141842,JQ20210121155220236,JQ20210121155324613,JQ20210121155411442,JQ20210121155442464,JQ20210121155514235,JQ20210121155535674,JQ20210121155735401,JQ20210121155752482,JQ20210121155908581,JQ20210121155918750,JQ20210121155938111,JQ20210121155951590";
        System.out.println("s1 length:"+s1.length()+"s1 size:"+s1.split(",").length);

        String s2 = "JQ20210121153512047,JQ20210121153924185,JQ20210121154115085,JQ20210121154147655,JQ20210121154204718,JQ20210121154225473,JQ20210121154255351,JQ20210121154309558,JQ20210121154323211,JQ20210121154331414,JQ20210121154341110,JQ20210121154349302,JQ20210121154412099,JQ20210121154421202,JQ20210121154430534,JQ20210121154451496,JQ20210121154459643,JQ20210121154531840,JQ20210121154552462,JQ20210121154731943,JQ20210121154830648,JQ20210121154849226,JQ20210121154857776,JQ20210121154905806,JQ20210121154938958,JQ20210121154945980,JQ20210121154959596,JQ20210121155019419,JQ20210121155033049,JQ20210121155047845,JQ20210121155058082,JQ20210121155115119,JQ20210121155130260,JQ20210121155148693,JQ20210121155234010,JQ20210121155300969,JQ20210121155330050,JQ20210121155503485,JQ20210121155530455,JQ20210121155538188,JQ20210121155623755,JQ20210121155705570,JQ20210121155755773,JQ20210121155818193,JQ20210121155838189,JQ20210121155849295,JQ20210121155857792";
        System.out.println("s2 length:"+s2.length()+"s2 size:"+s2.split(",").length);
    }

    public static String rejectInvalidVideo(List<String> videoIds){
        String s="JQ20210126211137269,JQ20210126211137840,JQ20210126211138836,JQ20210126211139862,JQ20210126211140870,JQ20210126211144321,JQ20210126211144926,JQ20210126211146461,JQ20210126211147191,JQ20210126211147337,JQ20210126211147879,JQ20210126211148897,JQ20210126211206513,JQ20210126211222063,JQ20210126211231613,JQ20210126211242789,JQ20210126211300795,JQ20210126211315636,JQ20210126211330579,JQ20210126211337802,JQ20210126211347866,JQ20210126211402954,JQ20210126211418117,JQ20210126211429719,JQ20210126211439738,JQ20210126211512777,JQ20210126211524573,JQ20210126211536072,JQ20210126211545559,JQ20210126211553845,JQ20210126211602868,JQ20210126211635172,JQ20210126211647072,JQ20210126211709479,JQ20210126211717214,JQ20210126211735377,JQ20210126211750731,JQ20210126211758149,JQ20210126211806887,JQ20210126211844177,JQ20210126211900395,JQ20210126211912255,JQ20210126211922876,JQ20210126211931503,JQ20210126211945478,JQ20210126212029911,JQ20210126212037424,JQ20210126212047262,JQ20210126212058755,JQ20210126212123500,JQ20210126212153824,JQ20210126212219861,JQ20210126212232967,JQ20210126212250592";
        videoIds = Arrays.asList(s.split(","));
        if (videoIds.size()==0){
            return null;
        }
        System.out.println("size"+videoIds.size());
        StringBuilder goal = new StringBuilder();
        int nowTime =Integer.parseInt(videoIds.get(0).substring(12,16)); //JQ2021 01 26 21 04 54 231
        int currentTime;
        for (String videoId : videoIds) {
            currentTime=Integer.parseInt(videoId.substring(12,16));
            System.out.println("nowtime : "+nowTime+"  currenttime: "+currentTime);
            if (Math.abs(currentTime-nowTime)>1000){
                goal.append(videoId).append(",");
                nowTime=currentTime;
            }
        }
        String substring = goal.substring(0, goal.length() - 1);
        System.out.println("length:"+substring.split(",").length);
        System.out.println(substring);
        return substring;
    }


    public static void setTime(){
        DateTime now = new DateTime();
        String nowStr = now.toString();
        System.out.println("now:"+now+"   nowStr:"+nowStr);
    }


    public static String  compareTime(List<String> videoIds){
        String s="JQ20210126211137269,JQ20210126211137840,JQ20210126211138836,JQ20210126211139862,JQ20210126211140870,JQ20210126211144321,JQ20210126211144926,JQ20210126211146461,JQ20210126211147191,JQ20210126211147337,JQ20210126211147879,JQ20210126211148897,JQ20210126211206513,JQ20210126211222063,JQ20210126211231613,JQ20210126211242789,JQ20210126211300795,JQ20210126211315636,JQ20210126211330579,JQ20210126211337802,JQ20210126211347866,JQ20210126211402954,JQ20210126211418117,JQ20210126211429719,JQ20210126211439738,JQ20210126211512777,JQ20210126211524573,JQ20210126211536072,JQ20210126211545559,JQ20210126211553845,JQ20210126211602868,JQ20210126211635172,JQ20210126211647072,JQ20210126211709479,JQ20210126211717214,JQ20210126211735377,JQ20210126211750731,JQ20210126211758149,JQ20210126211806887,JQ20210126211844177,JQ20210126211900395,JQ20210126211912255,JQ20210126211922876,JQ20210126211931503,JQ20210126211945478,JQ20210126212029911,JQ20210126212037424,JQ20210126212047262,JQ20210126212058755,JQ20210126212123500,JQ20210126212153824,JQ20210126212219861,JQ20210126212232967,JQ20210126212250592";
        videoIds = Arrays.asList(s.split(","));
        if (videoIds.size()==0){
            return null;
        }
        System.out.println("size"+videoIds.size());
        StringBuilder goal = new StringBuilder();
        //String nowTimeStr =videoIds.get(0).substring(2); //JQ2021 01 26 21 04 54 231
        long nowTime = new DateTime(videoIds.get(0).substring(2,16), "yyyyMMddHHmmss").getTime();
        System.out.println(nowTime);
        long currentTime;
        for (String videoId : videoIds) {
            currentTime=new DateTime(videoId.substring(2,16), "yyyyMMddHHmmss").getTime();
            System.out.println("nowtime : "+nowTime+"  currenttime: "+currentTime);
            if (Math.abs(currentTime-nowTime)>7*1000){
                goal.append(videoId).append(",");
                nowTime=currentTime;
           }
        }
        String substring = goal.substring(0, goal.length() - 1);
       System.out.println("length:"+substring.split(",").length);
        System.out.println(substring);
        return substring;
    }

    public static void testActive(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "I6cc1j7MR");

        // 向流媒体服务器发送请求，判断是否正在直播
        String response = HttpClientUtil.doPost("http://localhost:10080/live/get", params);
        Map<String, Object> retMap = JSON.parseObject(response);
        System.out.println(response);
        for (String s : retMap.keySet()) {
            System.out.println("key: "+s+"  value: "+retMap.get(s));
        }
    }

    public static void cpu(){
        try{
            while(true){
                File file = new File("/proc/stat");
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file)));
                StringTokenizer token = new StringTokenizer(br.readLine());
                token.nextToken();
                int user1 = Integer.parseInt(token.nextToken());
                int nice1 = Integer.parseInt(token.nextToken());
                int sys1 = Integer.parseInt(token.nextToken());
                int idle1 = Integer.parseInt(token.nextToken());
                long startTime = new Date().getTime();
                Thread.sleep(1000);
                long entTime = new Date().getTime();
                System.out.println("time:"+(entTime - startTime));
                br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file)));
                token = new StringTokenizer(br.readLine());
                token.nextToken();
                int user2 = Integer.parseInt(token.nextToken());
                int nice2 = Integer.parseInt(token.nextToken());
                int sys2 = Integer.parseInt(token.nextToken());
                int idle2 = Integer.parseInt(token.nextToken());

                float cpu = ((user2 + sys2 + nice2) - (user1 + sys1 + nice1)) / (float)((user2 + nice2 + sys2 + idle2) - (user1 + nice1 + sys1 + idle1));
                System.out.println("cpu:"+cpu*100+"%");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void testGetUrl(){
        Date now = new Date(121, 0, 21, 22, 15);
        DateTime startTime = new DateTime(now);
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("1");
        urlList.add("2");
        urlList.add("3");
        urlList.add("4");
        new Thread(() -> {
            int currentHour = Integer.parseInt(startTime.toString("HH"));
            if (currentHour>=19&&currentHour<=23){
                for (String url : urlList) {
                    try {
                        Thread.sleep(10*1000L);
                        //HttpClientUtil.doGet(url);
                        System.out.println(url);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }


    public static void getRequestTest(){
        HttpClientUtil.doGetNoResponse("https:///record/video/play/Lsc4uGbGg/20210202133023/20210202135823");

    }


}

*/
