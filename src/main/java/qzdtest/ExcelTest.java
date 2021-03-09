package qzdtest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
import com.esotericsoftware.minlog.Log;
import com.github.tangyi.common.core.model.JWT;
import com.github.tangyi.common.core.persistence.BaseEntity;
import com.github.tangyi.common.core.utils.HttpClientUtil;
import com.github.tangyi.common.core.utils.HttpUtil;
import com.github.tangyi.common.core.utils.TestObject;
import com.google.gson.JsonObject;
import org.apache.http.util.TextUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
*/

public class ExcelTest {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        /*ArrayList<JWT> jwts = new ArrayList<>();
        for (int i = 0;i<10;i++){
            jwts.add(new JWT("qwe","asd","zxc",345,"dfg","cvb"));
        }
        ExcelUtil.writeExcel("test",jwts,JWT.class);*/

        /*String a ="12";
        String b ="12";
        String c ="12";
        String d ="12";
        String e = "a5002f50f6383033323533463033313646313131313131313131313131dccd01aaaaa10011e1015500020002cddc";
        String f = "A5 00 2F 50 F6  38 30 33 32 35 33 46 30 33 31 36 46  31 31 31 31 31 31 31 31 31 31 31 31 DC CD 01 AA AA A1 00 11 E1 01 55 00 02 00 02 CD DC";
        byte[] bytes = hexStrToBinaryStr(f);
        int sum = 0;
        for (byte aByte : bytes) {
            System.out.println(aByte);
            sum+=aByte;
        }
        System.out.println(sum);
        //byte SumSub = (byte) sum;
        System.out.println(Integer.toHexString(sum&0xff));
        System.out.println("=================");*/
        //bytes[bytes.length-1]=(byte)Integer.parseInt(String.valueOf(SumSub), 16);
        //System.out.println(bytes[bytes.length-1]);


        /*byte[] bytes = new byte[]{(byte) 0xa2, (byte) 0xd3};
        byte a2 = (byte) 0xa2;
        System.out.println(a2 == bytes[0]);*/

        /*byte eightIntMax = (byte)0xdc;*/

        /*String f = "A5 00 2F 50 F6  38 30 33 32 35 33 46 30 33 31 36 46  31 31 31 31 31 31 31 31 31 31 31 31 DC CD 01 AA AA A1 00 11 E1 01 55 00 02 00 02 CD DC";
        byte[] bytes = hexStrToBinaryStr(f);
        String s = bytes.toString();
        System.out.println("toString:"+s);
        System.out.println("length:"+s.length());
        String s1 = Arrays.toString(bytes);
        System.out.println("array:"+s1);
        System.out.println("length:"+s1.length());
        String s2 = bytesToHexString(bytes);
        System.out.println("array:"+s2);
        System.out.println("length:"+s2.length());*/

        /*String message = new String(new byte[]{},"utf-8");
        String message2 = null;
        System.out.println("S"+message+"e");
        System.out.println("s"+message2+"e");*/

        /*String switchId = "";
        int[] switchs = new int[]{1};
        StringBuilder s = new StringBuilder("a5");
        int outsideCommendLength = 40+7*switchs.length; //39+21=60 3c
        int insideCommandLength = 10+7*switchs.length;  //31 1f
        String outsideCommendLengthStr = Integer.toHexString(outsideCommendLength);
        String insideCommendLengthStr = Integer.toHexString(insideCommandLength);
        //System.out.println(outsideCommendLengthStr+"***"+insideCommendLengthStr);
        s.append("00");
        s.append(outsideCommendLengthStr); //指令长度
        s.append("50f6");
        s.append(switchId);
        s.append("31 31 31 31 31 31 31 31 31 31 31 31");
        s.append("dcdc"); //帧头
        s.append("01aaaaa1");
        s.append("00");
        if (insideCommendLengthStr.length()<2){
            s.append("0"+insideCommendLengthStr);//指令长度
        }else {
            s.append(insideCommendLengthStr);//指令长度
        }

        for (int aSwitch : switchs) {
            String s1 = Integer.toHexString(aSwitch);
            System.out.println("address:"+s1);
            s.append("e10"+s1);
            s.append("55");//开关控制
            s.append("00 02 00 02");
        }
        s.append("cddc");
        System.out.println(s);
        //校验码
        byte[] bytes = hexStrToBinaryStr(s.toString());
        int sum = 0;
        for (byte aByte : bytes) {
            sum+=aByte;
        }
        System.out.println("检验码"+sum);
        String last = Integer.toHexString(sum&0xff);
        System.out.println(last);
        if (last.length()<2){
            s.append("0"+last);
        }else {
            s.append(last);
        }
        System.out.println("s**"+s);
        bytes = hexStrToBinaryStr(s.toString());
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytesToHexString(bytes));*/


        /*String s = new String("0000");
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i=0;i<s.length();i++){
            ints.add(Integer.parseInt(String.valueOf(s.charAt(i)),16));
        }
        System.out.println(ints);

        //ArrayList<Boolean> result = new ArrayList<>(16);
        boolean[] result = new boolean[16];
        for (int i = 0; i < ints.size(); i++) {
            for (int j =1;j<5;j++){
                System.out.println("i:"+i+" j"+j);
                if (ints.get(i) %2==0){
                    result[4 * i +(4-j)]=false;
                }else {
                    result[4 * i + (4-j)]=true;
                }
                ints.set(i,ints.get(i)>>>1);
            }
        }
        for (Boolean aBoolean : result) {
            System.out.print(aBoolean+" ");
        }*/

        /*String s = "a5003650f6343833464441353245304331343833464441353245304331dccd01aae0b10018e2010000000000e3010000000000cddc00";
        String s1 = "a5003650f6343833464441353245304331343833464441353245304331dccd01aa";
        System.out.println("s1.length:"+s1.length());
        String substring = s.substring(66,68);
        System.out.println(substring);
        System.out.println("e0".equals(substring));*/

       /*String s = "a5003650f6343833464441353245304331343833464441353245304331dccd01aae0b10018e2010000000000e3010000000000cddc00";
        System.out.println("a5003650f6343833464441353245304331343833464441353245304331dccd01aae0b10018e201000000".length());
        String substring = s.substring(84, 88);
        System.out.println(substring);
        boolean[] status = getStatus(substring);
        System.out.println(status);*/

        /*ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("sTime Readerkjhdflaksf","========");
        map.put("skjhdflakTime Readersf","skjdofasdf");
        map.put("skjhdflaksf","skjdofasdf");
        map.put("sTime Readerkjhdflcxvaksf","+++++");
        map.put("sdfgdfgkjhdflaksf","*******");
        map.put("skjhdflTime Readeraksf","skjdofasdf");
        map.put("ssfdfkjhdflaksf","&&&&&&");
        map.put("skjhdfTime Readerlaksf","######");
        ConcurrentMap<String, String> map1 = removeLightSocketByIterator(map);
        System.out.println(map1.size());
        Set<String> strings = map1.keySet();
        for (String string : strings) {
            System.out.println(string+" "+map1.get(string));
        }*/

       /* Calendar instance = Calendar.getInstance();
        System.out.println("calender"+instance);
        instance.setTime(new Date());
        instance.add(Calendar.DATE,1);
        Date time = instance.getTime();
        System.out.println("time"+time);
        String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS").format(time);
        System.out.println("format:"+format);*/


        /*StringBuilder timeCommand = new StringBuilder();
        String format = new SimpleDateFormat("yyMMddhhmmss,E").format(new Date());
        String[] timeArray = format.split(",");
        timeCommand.append("0aff09e0");
        timeCommand.append(timeArray[0]);
        switch (timeArray[1]){
            case "星期日":
                timeCommand.append("00");
                break;
            case "星期一":
                timeCommand.append("01");
                break;
            case "星期二":
                timeCommand.append("02");
                break;
            case "星期三":
                timeCommand.append("03");
                break;
            case "星期四":
                timeCommand.append("04");
                break;
            case "星期五":
                timeCommand.append("05");
                break;
            case "星期六":
                timeCommand.append("06");
                break;
            default:
                timeCommand.append("00");
                break;
        }
        System.out.println(timeCommand);
        byte[] bytes = hexStrToBinaryStr(timeCommand.toString());
        byte sum = 0;
        for (byte aByte : bytes) {
            System.out.print(aByte+" ");
            int a = Integer.parseInt(String.valueOf(aByte),16);
            System.out.println(a);
            sum+=aByte;
        }
        //System.out.println("sum"+sum);
        String checkSum = Integer.toHexString((~sum + 1) & 0xff);
        //System.out.println("检验码:"+checkSum);
        if (checkSum.length()<2){
            timeCommand.append("0").append(checkSum);
        } else {
            timeCommand.append(checkSum);
        }
        System.out.println("timeCommand:"+timeCommand);*/

        /*byte[] timeCommand = getTimeCommand();
        for (byte b : timeCommand) {
            System.out.print(b);
        }*/

        /*Date time = Calendar.getInstance().getTime();
        System.out.println(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        Date format = null;
        try {
            format = simpleDateFormat.parse("20-12-30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(format);*/

        /*String parm = "123=456&asd=dsa&sdf=fds&dfg=gfd";
        String stringParam = getStringParam(parm);
        System.out.println(stringParam);*/

        /*String str = "time reader";
        byte[] bytes = str.getBytes();
        for (byte aByte : bytes) {
            System.out.print(aByte+" ");
        }*/

        /*VideoMatch videoMatch = new VideoMatch();
        videoMatch.setZbId("aRGiJ14Gg");
        videoMatch.setArenaId("arena08");
        videoMatch.setName("兄弟篮球");
        videoMatch.setStarttime("2020-10-23 20:49:22");
        videoMatch.setEndtime("2020-10-23 20:50:59");
        videoMatch.setTeamA("健身小分队");
        videoMatch.setTeamB("肌肉小分队");
        System.out.println(videoMatch.toString());
        String s = JSON.toJSONString(videoMatch);
        System.out.println(s);

        String s2 = "{'zbId':'aRGiJ14Gg','arenaId':'arena08','name':'兄弟篮球', 'starttime':'2020-10-23 20:49:22', 'endtime':'2020-10-23 20:50:59', 'teamA':'健身小分队', 'teamB':'肌肉小分队'}";
        JSONObject jsonObject = JSON.parseObject(s2);
        String zbId = jsonObject.getString("zbId");
        System.out.println("zbId"+zbId);*/

        /*String replace = "2020-08-29 21:10:04".replace("-", "").replace(" ", "").replace(":", "");
        System.out.println(replace);*/
        /*String replace = "{'zbId':'aRGiJ14Gg','arenaId':'arena08','name':'兄弟篮球', 'starttime':'2020-10-23 20:49:22', 'endtime':'2020-10-23 20:50:59', 'teamA':'健身小分队', 'teamB':'肌肉小分队'}".replace("'", "\"");
        System.out.println(replace);*/

        /*HashMap<String, String> httpValue = new HashMap<>();
        httpValue.put("folder","postceshi");
        httpValue.put("name","开始交电费");
        httpValue.put("sort","1");

        String s = HttpClientUtil.doPost( "http://127.0.0.1:10080/vod/subcatalogadd", httpValue);
        System.out.println(s);*/

        //uploadFile("D:\\bili\\Download\\phone.mp4");

        //book4("D:\\bili\\Download\\cbaBass.mp4");

        /*String result = "    {\"id\":\"rTOssPAGR\",\"createAt\":\"2020-12-03 17:38:21\",\"updateAt\":\"2020-12-03 17:38:22\"," +
                "\"name\":\"phone\",\"size\":53337827,\"type\":\"video/mp4\"," +
                "\"path\":\"D:\\\\EasyDSS\\\\EasyDSS-windows-3.2.2-20102917\\\\data\\\\vodsrc\\\\test\\\\rTOssPAGR.mp4\"," +
                "\"folder\":\"test/rTOssPAGR\",\"status\":\"waiting\",\"duration\":20," +
                "\"videoCodec\":\"H.264\",\"audioCodec\":\"AAC\",\"aspect\":\"1920x1080\",\"error\":\"\"," +
                "\"shared\":false,\"shareBeginTime\":null,\"shareEndTime\":null,\"rotate\":90," +
                "\"resolution\":\"yh,fhd,hd,sd\",\"isresolution\":true,\"resolutiondefault\":\"hd\"," +
                "\"transvideo\":false,\"userId\":\"\",\"localIp\":\"192.168.124.72\"," +
                "\"snapUrl\":\"\",\"videoUrl\":\"/fvod/test/rTOssPAGR/video.m3u8\"," +
                "\"sharedLink\":\"/share.html?id=rTOssPAGR\\u0026type=vod\",\"flowNum\":0,\"progress\":0,\"playNum\":0}";



        JSONObject jsonObject = JSONObject.parseObject(result);
        String id = jsonObject.getString("id");
        String createAt = jsonObject.getString("createAt");
        String updateAt = jsonObject.getString("updateAt");
        String name = jsonObject.getString("name");
        String size = jsonObject.getString("size");
        String type = jsonObject.getString("type");
        String string = jsonObject.getString("path");
        String folder = jsonObject.getString("folder");
        String status = jsonObject.getString("status");
        String duration = jsonObject.getString("duration");
        String snapUrl = jsonObject.getString("snapUrl");

        System.out.println(snapUrl);
        System.out.println(snapUrl.equals(""));
        System.out.println(id+"  "+createAt);*/

       //book4("E:\\downloadVideos\\arena54hyassyngr20200829211004addwaterremarkconcat.mp4");
       /* List<String> strings = new ArrayList<>();
        System.out.println(strings.size());
        strings.add("df");
        strings.add("sdf");
        System.out.println(strings.size());*/

       /* String s = HttpClientUtil.doGet("http://127.0.0.1:10080/vod/progress?id=fl4LdZ1Gg");
        System.out.println("[]".equals(s));*/

        /*String ids = "trgLdW1Mg feMYdZ1Gg fl4LdZ1Gg 8XQmKnJMg hpDYdZ1Mg mUJsOWJGg O9bydZJMg 7IEsdZJMg 5FYyOWJMg KiPsOWJMR";
        String[] s = ids.split(" ");
        List<String> id = new ArrayList<>();
        for (String s1 : s) {
            id.add(s1);
        }
        boolean flag = false;
        while (!flag) {
            try {
                System.out.println("sleeping...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (String s1 : id) {
                String s2 = HttpClientUtil.doGet("http://127.0.0.1:10080" + "/vod/progress?id=" + s1);
                if ("[]".equals(s2)){
                    flag=true;
                    System.out.println(flag);
                }else {
                    flag=false;
                    System.out.println(flag);
                    break;
                }
            }
        }
        System.out.println("result flag = "+flag);*/

        /*String folderpath = "aaa/ccc/bbb";
        String foldername = "ddd";
        HashMap<String, String> httpValue = new HashMap<>();
        httpValue.put("folder",folderpath);
        httpValue.put("name",foldername);
        httpValue.put("sort","1");
        String result = HttpClientUtil.doPost( "http://127.0.0.1:10080/vod/subcatalogadd", httpValue);
        System.out.println(result);*/

        /*Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            str = sc.next();
            str = str.replace("吗", "");
            str = str.replace("你", "");
            str = str.replace("?", "!");
            System.out.println(str);
        }*/

        /*SoftReference<String> stringSoftReference = new SoftReference<>("hello world!");
        System.out.println(stringSoftReference.toString());
        System.out.println(stringSoftReference.get());

        WeakReference<String> stringWeakReference = new WeakReference<String>("hello today!");
        System.out.println(stringWeakReference.toString());
        System.out.println(stringWeakReference.get());*/

        /*Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        System.out.println(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(time);
        System.out.println(format);*/


       /* Map<String, String> httpValue = new HashMap<>();
        httpValue.put("id","f7goHaAGg");
        httpValue.put("actived","true");
        String result;

        do {
            result = HttpClientUtil.doPost("http://127.0.0.1:10080" + "/live/turn/actived", httpValue);
        }while (!result.contains("200"));
        System.out.println(result);*/
        //System.out.println("242021010600002".length());


       /* *//*"livePlan":"{\"Monday\":\"03:00:00-08:00:00\",\"Tuesday\":\"\",\"Wednesday\":\"\"," +
                "\"Thursday\":\"\",\"Friday\":\"\",\"Saturday\":\"\",\"Sunday\":\"\"}"*//*
        Map<String, Map<String, String>> stringListMap = new HashMap<>();
        Map<String,String> plans = new HashMap<>();
        //List<Map<String,String>> weeks = new ArrayList<>();
        plans.put("Monday","05:00:00-22:00:00");
        plans.put("Tuesday","05:00:00-09:00:00");
        plans.put("Wednesday","05:00:00-09:00:00");
        plans.put("Thursday","05:00:00-22:00:00");
        plans.put("Friday","05:00:00-09:00:00");
        plans.put("Saturday","05:00:00-09:00:00");
        plans.put("Sunday","05:00:00-09:00:00");
        //weeks.add(plans);
        stringListMap.put("livePlan",plans);
        String jsonString = JSONObject.toJSONString(stringListMap);
        System.out.println(jsonString);
        //plans.keySet().forEach(System.out::print);

        Map<String,String> httpvalue = new HashMap<>();
        httpvalue.put("id","f7goHaAGg");//id 编辑必传
        httpvalue.put("name","直播");
        httpvalue.put("recordReserve","10");
        httpvalue.put("livePlanData", JSONObject.toJSONString(plans));
        String result = HttpClientUtil.doPost("http://127.0.0.1:10080/live/save", httpvalue);
        System.out.println(result);*/

        //printNum2(6);

        /*String s = "你好";
        String encode = Base64.encode(s);
        System.out.println(Arrays.toString(Base64.decode(encode)));*/


       /* Date now = new Date();
        DateTime startTime = DateUtil.offsetMinute(now, -45);
        DateTime endTime = DateUtil.offsetMinute(now, -15);

        System.out.println(startTime.toString("MMdd hh:mm"));

        System.out.println("now: "+now+"  start: " +startTime.toString()+"  end: "+endTime.toString());
*/
       /* Date date = new Date(121, 1, 21, 16, 15 );
        System.out.println(date);*/

       /*String  s = "JQ20210121192143007,JQ20210121192441539,JQ20210121192451990,JQ20210121211302027,JQ20210121211323340,JQ20210121211407963,JQ20210121211516100,JQ20210121211559905,JQ20210121211640906,JQ20210121211816979,JQ20210121212025033,JQ20210121212149355,JQ20210121212223565,JQ20210121212459224,JQ20210121192536277,JQ20210121192629995,";
        System.out.println(s.split(",").length);
        String d = s+s+s;
        System.out.println(d);
*/

       /*String s = "0.12";
        System.out.println(s);
        float v = Float.parseFloat(s);
        BigDecimal bigDecimal = new BigDecimal(s);
        System.out.println(v);
        System.out.println(v*12);
        System.out.println(bigDecimal.multiply(BigDecimal.valueOf(12)));*/

       /*String starttime="2021-03-24 10:00:00";
       String endtime="2021-03-25 10:00:00";

        long start = new DateTime(starttime, "yyyy-MM-dd HH:mm:ss").getTime();
        long end = new DateTime(endtime, "yyyy-MM-dd HH:mm:ss").getTime();
        long now = new DateTime().getTime();
        System.out.println("start"+start);
        System.out.println("end"+end);
        System.out.println(now>start);*/

        /*DateTime now = new DateTime();
        DateTime tenDaysAgo = DateUtil.offsetDay(now, -10);
        DateTime nineDaysAgo = DateUtil.offsetDay(now, -9);
        String tenDaysAgoStr = tenDaysAgo.toString("yyyy-MM-dd HH:mm:ss");
        String nineDaysAgoStr = nineDaysAgo.toString("yyyy-MM-dd HH:mm:ss");
        System.out.println("ten:"+tenDaysAgoStr);
        System.out.println("nine:"+nineDaysAgoStr);


        tenDaysAgoStr.split("");*/
        System.out.println(DateUtil.now());
        System.out.println(new DateTime());

    }



    public static void printNum(int i){
        System.out.println(i);
        if (i<=0){
            return;
        }
        i--;
        printNum(i);
        System.out.println(++i);
    }

    public static void printNum2(int i){
        System.out.println(i);
        if (i>0){
            i--;
            printNum2(i);
            System.out.println(++i);
        }
    }

   /* public static String book4(String path){
        // 上传文件
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        if (!fileSystemResource.exists()) {//文件不存在处理
            System.out.println("文件不存在");
        }
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        //headers.set("Cookie", cookies);//session用于验证身份
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<String, Object>();
        form.add("file", fileSystemResource);
        //form.add("pass", "123");//用于验证身份
        form.add("path","test");
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://127.0.0.1:10080/vod/upload", files, String.class);//发送
        //此处要添加错误处理
        String result = responseEntity.getBody().toString();
        return result;
    }*/
    //{"id":"rTOssPAGR","createAt":"2020-12-03 17:38:21","updateAt":"2020-12-03 17:38:22","name":"phone","size":53337827,"type":"video/mp4","path":"D:\\EasyDSS\\EasyDSS-windows-3.2.2-20102917\\data\\vodsrc\\test\\rTOssPAGR.mp4","folder":"test/rTOssPAGR","status":"waiting","duration":20,"videoCodec":"H.264","audioCodec":"AAC","aspect":"1920x1080","error":"","shared":false,"shareBeginTime":null,"shareEndTime":null,"rotate":90,"resolution":"yh,fhd,hd,sd","isresolution":true,"resolutiondefault":"hd","transvideo":false,"userId":"","localIp":"192.168.124.72","snapUrl":"","videoUrl":"/fvod/test/rTOssPAGR/video.m3u8","sharedLink":"/share.html?id=rTOssPAGR\u0026type=vod","flowNum":0,"progress":0,"playNum":0}



    public static void uploadFile(String fileName) {
        try {

            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL url = new URL("http://127.0.0.1:10080/vod/upload");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置为POST情
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头参数
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // 上传文件
            File file = new File(fileName);
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data;name=\"photo\";filename=\"" + fileName
                    + "\"" + newLine);
            sb.append("Content-Type:application/octet-stream");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);

            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            // 数据输入流,用于读取文件数据
            DataInputStream in = new DataInputStream(new FileInputStream(
                    file));
            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            // 最后添加换行
            out.write(newLine.getBytes());
            in.close();

            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine)
                    .getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();

            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
    }


    /*public static String getStringParam(String param){
        if(TestObject.isEmpty(param)){
            return "";
        }
        String [] teamId = param.split("&");
        String teamIdParam = "'";
        for(int i = 0 ; i < teamId.length ; i ++){
            teamIdParam += teamId[i] + "','";
        }
        return teamIdParam.substring(0,teamIdParam.length()-2);
    }*/

   /* public static byte[] getTimeCommand(){
        StringBuilder timeCommand = new StringBuilder();
        String format = new SimpleDateFormat("yyMMddhhmmss,E").format(new Date());
        String[] timeArray = format.split(",");
        timeCommand.append("0aff09e0");
        timeCommand.append(timeArray[0]);
        switch (timeArray[1]){
            case "星期日":
                timeCommand.append("00");
                break;
            case "星期一":
                timeCommand.append("01");
                break;
            case "星期二":
                timeCommand.append("02");
                break;
            case "星期三":
                timeCommand.append("03");
                break;
            case "星期四":
                timeCommand.append("04");
                break;
            case "星期五":
                timeCommand.append("05");
                break;
            case "星期六":
                timeCommand.append("06");
                break;
            default:
                timeCommand.append("00");
                break;
        }
        System.out.println(timeCommand);
        byte[] bytes = hexStrToBinaryStr(timeCommand.toString());
        //校验码
        byte sum = 0;
        for (byte aByte : bytes) {
            //int a = Integer.parseInt(String.valueOf(aByte),16);
            sum+=aByte;
        }
        String checkSum = Integer.toHexString((~sum + 1) & 0xff);
        if (checkSum.length()<2){
            timeCommand.append("0").append(checkSum);
        } else {
            timeCommand.append(checkSum);
        }
        //Log.info("timeCommand:" + timeCommand);
        return hexStrToBinaryStr(timeCommand.toString());
    }*/


    public static ConcurrentMap<String, String> removeLightSocketByIterator(ConcurrentMap<String, String> map){
        ConcurrentMap<String,String>  returnMap = new ConcurrentHashMap<>();
        Set<String> strings = map.keySet();
        for (String string : strings) {
            if (string.contains("Time Reader")){
                returnMap.put(string,map.get(string));
            }
        }
        return returnMap;
    }


    public static boolean[] getStatus(String s){
        ArrayList<Integer> ints = new ArrayList<>();
        for (int i=0;i<s.length();i++){
            ints.add(Integer.parseInt(String.valueOf(s.charAt(i)),16));
        }
        System.out.println(ints);
        boolean[] result = new boolean[16];
        for (int i = 0; i < ints.size(); i++) {
            for (int j =1;j<5;j++){
                System.out.println("i:"+i+" j"+j);
                result[4 * i +(4-j)]= ints.get(i) % 2 != 0;
                ints.set(i,ints.get(i)>>>1);
            }
        }
        for (Boolean aBoolean : result) {
            System.out.print(aBoolean+" ");
        }
        return result;
    }



        /**
         * 将十六进制的字符串转换成字节数组
         *
         * @param hexString
         * @return
         */
        /*public static byte[] hexStrToBinaryStr(String hexString){


            if (TextUtils.isEmpty(hexString)) {
                return null;
            }

            hexString = hexString.replaceAll(" ", "");

            int len = hexString.length();
            int index = 0;


            byte[] bytes = new byte[(len / 2)];

            while (index < len) {

                String sub = hexString.substring(index, index + 2);

                bytes[index/2] = (byte)Integer.parseInt(sub,16);

                index += 2;
            }

            return bytes;
        }*/

    public static byte[] hexStrToBinaryStr2(String hexString) {

        hexString = hexString.replaceAll(" ", "");

        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length-1; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


}
