package socketserver.cpuutils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CpuByHttp {
    public static void main(String[] args) {
        String s = "{\"bandwidthData\":[{\"time\":\"2021-02-01 17:35:02\",\"use\":\"0.002\"},{\"time\":\"2021-02-01 17:35:08\",\"use\":\"0.011\"},{\"time\":\"2021-02-01 17:35:14\",\"use\":\"0.050\"},{\"time\":\"2021-02-01 17:35:19\",\"use\":\"0.005\"},{\"time\":\"2021-02-01 17:35:25\",\"use\":\"0.009\"},{\"time\":\"2021-02-01 17:35:31\",\"use\":\"0.013\"},{\"time\":\"2021-02-01 17:35:37\",\"use\":\"0.006\"},{\"time\":\"2021-02-02 11:19:40\",\"use\":\"648.579\"},{\"time\":\"2021-02-02 11:19:47\",\"use\":\"0.007\"},{\"time\":\"2021-02-02 11:19:52\",\"use\":\"0.004\"}],\"cpuData\":[{\"time\":\"2021-02-02 11:19:23\",\"use\":0.1484375},{\"time\":\"2021-02-02 11:19:24\",\"use\":0.2109375},{\"time\":\"2021-02-02 11:19:25\",\"use\":0.140625},{\"time\":\"2021-02-02 11:19:26\",\"use\":0.140625},{\"time\":\"2021-02-02 11:19:27\",\"use\":0.10546875},{\"time\":\"2021-02-02 11:19:28\",\"use\":0.1171875},{\"time\":\"2021-02-02 11:19:29\",\"use\":0.23828125},{\"time\":\"2021-02-02 11:19:30\",\"use\":0.11538461538461538},{\"time\":\"2021-02-02 11:19:31\",\"use\":0.18359375},{\"time\":\"2021-02-02 11:19:32\",\"use\":0.140625},{\"time\":\"2021-02-02 11:19:33\",\"use\":0.140625},{\"time\":\"2021-02-02 11:19:34\",\"use\":0.1328125},{\"time\":\"2021-02-02 11:19:35\",\"use\":0.2153846153846154},{\"time\":\"2021-02-02 11:19:36\",\"use\":0.1953125},{\"time\":\"2021-02-02 11:19:37\",\"use\":0.15625},{\"time\":\"2021-02-02 11:19:38\",\"use\":0.18359375},{\"time\":\"2021-02-02 11:19:39\",\"use\":0.1875},{\"time\":\"2021-02-02 11:19:40\",\"use\":0.12890625},{\"time\":\"2021-02-02 11:19:41\",\"use\":0.79296875},{\"time\":\"2021-02-02 11:19:42\",\"use\":0.9961538461538462},{\"time\":\"2021-02-02 11:19:43\",\"use\":0.99609375},{\"time\":\"2021-02-02 11:19:44\",\"use\":1},{\"time\":\"2021-02-02 11:19:45\",\"use\":1},{\"time\":\"2021-02-02 11:19:46\",\"use\":0.8715953307392996},{\"time\":\"2021-02-02 11:19:47\",\"use\":0.4921875},{\"time\":\"2021-02-02 11:19:48\",\"use\":0.9296875},{\"time\":\"2021-02-02 11:19:49\",\"use\":0.52734375},{\"time\":\"2021-02-02 11:19:50\",\"use\":0.4140625},{\"time\":\"2021-02-02 11:19:51\",\"use\":0.5807692307692308},{\"time\":\"2021-02-02 11:19:52\",\"use\":0.37109375}],\"memData\":[{\"time\":\"2021-02-02 11:19:23\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:24\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:25\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:26\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:27\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:28\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:29\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:30\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:31\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:32\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:33\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:34\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:35\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:36\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:37\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:38\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:39\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:40\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:41\",\"use\":0.85},{\"time\":\"2021-02-02 11:19:42\",\"use\":0.86},{\"time\":\"2021-02-02 11:19:43\",\"use\":0.86},{\"time\":\"2021-02-02 11:19:44\",\"use\":0.86},{\"time\":\"2021-02-02 11:19:45\",\"use\":0.88},{\"time\":\"2021-02-02 11:19:46\",\"use\":0.87},{\"time\":\"2021-02-02 11:19:47\",\"use\":0.87},{\"time\":\"2021-02-02 11:19:48\",\"use\":0.88},{\"time\":\"2021-02-02 11:19:49\",\"use\":0.88},{\"time\":\"2021-02-02 11:19:50\",\"use\":0.88},{\"time\":\"2021-02-02 11:19:51\",\"use\":0.88},{\"time\":\"2021-02-02 11:19:52\",\"use\":0.87}]}";
        String post = HttpUtil.post("https:///conf/state/top",new HashMap<>());
        parseResponse(post);
    }

    public static int parseResponse(String s){
        JSONObject jsonObject = JSONUtil.parseObj(s);
        List<Map<String,Object>> list= (List<Map<String,Object>>) jsonObject.get("cpuData");
        int percent = 0;
        for (int i = 0; i < 2; i++) {
            percent+=(double) list.get(i).get("use")*100;

        }
        System.out.println(percent/2);
        return percent/2;
    }
}
