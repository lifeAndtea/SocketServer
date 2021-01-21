package socketserver.netty.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url, String parameter) {
        String uriAPI = url + "?" + parameter;                                // "http://XXXXX?str=I+am+get+String";
        String result = "";
        HttpClient client = createSSLClientDefault();
        HttpGet httpRequst = new HttpGet(uriAPI);
        try {
            HttpResponse httpResponse = client.execute(httpRequst);           // 其中HttpGet是HttpUriRequst的子类
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);                    // 取出应答字符串
                // 一般来说都要删除多余的字符
                result.replaceAll("\r", "");                                  // 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            } else {
                httpRequst.abort();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }

    public static String doGet(String url) {
        String result = "";
        HttpClient client = createSSLClientDefault();
        HttpGet httpRequst = new HttpGet(url);
        try {
            HttpResponse httpResponse = client.execute(httpRequst);           // 其中HttpGet是HttpUriRequst的子类
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);                    // 取出应答字符串
                // 一般来说都要删除多余的字符
                result.replaceAll("\r", "");                                  // 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            } else {
                httpRequst.abort();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }

    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (KeyStoreException e) {
        }
        return HttpClients.createDefault();
    }


    public static JSONObject doPostReJSON(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        JSONObject json = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                JSONObject jsonParam = new JSONObject();
                for (String key : param.keySet()) {
                    jsonParam.put(key, String.valueOf(param.get(key)));
                }

                //解决中文问题。
                httpPost.addHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

                httpPost.setHeader("Accept", "application/json");

                httpPost.setEntity(new StringEntity(jsonParam.toString(), Charset.forName("UTF-8")));
            }
            System.out.println("doPostReJSON=================" + httpPost.getEntity());
            // 执行http请求
            response = httpClient.execute(httpPost);
            HttpEntity responseContent = response.getEntity();
            json = JSONObject.parseObject(EntityUtils.toString(responseContent));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    public static JSONObject postForForm(String url, TreeMap<String, String> parms) {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<BasicNameValuePair> list = new ArrayList<>();
        parms.forEach((key, value) -> list.add(new BasicNameValuePair(key, value)));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            if (Objects.nonNull(parms) && parms.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            }
            InputStream content = httpPost.getEntity().getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(content, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readLine = bufferedReader.readLine();
            String s = URLDecoder.decode(readLine, "UTF-8");
            System.out.println("readLine===================================" + readLine);
            System.out.println("s==========================================" + s);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity, "UTF-8"));
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(httpClient)) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }




    /* 发送 post请求 用HTTPclient 发送请求*/
    public static byte[] post(String URL, String json) {
        InputStream inputStream = null;
        byte[] data = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(URL);
        httppost.addHeader("Content-type", "application/json; charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        try {
            StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));
            s.setContentEncoding("UTF-8");
            httppost.setEntity(s);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                // 获取相应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    inputStream = entity.getContent();
                    data = readInputStream(inputStream);
                }
                return data;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**  将流 保存为数据数组
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }



}