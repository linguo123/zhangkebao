package com.example.a10146.demo2;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


/**
 * Created by Lane on 2018/4/2.
 */

public class HttpConnectionUtil {
    public static HttpConnectionUtil http = new HttpConnectionUtil();

    public static HttpConnectionUtil getHttp() {
        return http;
    }

    public String getRequset(final String url, final String token) {
        final StringBuilder sb = new StringBuilder();
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL requestUrl = new URL(url);
                    connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json");
                    if (token != null)
                        connection.setRequestProperty("token", token);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    if (connection.getResponseCode() == 200) {
                        InputStream in = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        System.out.println(sb);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                    if (connection != null) {
                        connection.disconnect();//鏂紑杩炴帴锛岄噴鏀捐祫婧�
                    }
                }
                return sb.toString();
            }
        });
        new Thread(task).start();
        String s = null;
        try {
            s = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public String postRequset(final String url, final Map<String, String> map, final String token) {
        final StringBuilder sb = new StringBuilder();
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL requestUrl = new URL(url);
                    connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);//閾炬帴瓒呮椂
                    connection.setReadTimeout(8000);//璇诲彇瓒呮椂
                    //鍙戦�乸ost璇锋眰蹇呴』璁剧疆
                    connection.setDoOutput(true);  //鎵撴潵杈撳嚭娴侊紝浠ヤ究鍚戞湇鍔″櫒鑾峰彇鏁版嵁
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setInstanceFollowRedirects(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    if (token != null)
                        connection.setRequestProperty("token", token);
                    DataOutputStream out = new DataOutputStream(connection
                            .getOutputStream());
                    StringBuilder request = new StringBuilder();
                    for (String key : map.keySet()) {
                        request.append(key + "=" + URLEncoder.encode(map.get(key), "UTF-8") + "&");
                    }
                    out.writeBytes(request.toString());//鍐欏叆璇锋眰鍙傛暟
                    out.flush();
                    out.close();
                    if (connection.getResponseCode() == 200) {
                        InputStream in = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        System.out.println(sb);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        reader.close();//鍏抽棴娴�
                    }
                    if (connection != null) {
                        connection.disconnect();//鏂紑杩炴帴锛岄噴鏀捐祫婧�
                    }
                }
                return sb.toString();
            }
        });
        new Thread(task).start();
        String s = null;
        try {
            s = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;

    public static final OkHttpClient client = new OkHttpClient();

    // .Builder()
//            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//璁剧疆璇诲彇瓒呮椂鏃堕棿
//            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//璁剧疆鍐欑殑瓒呮椂鏃堕棿
//            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//璁剧疆杩炴帴瓒呮椂鏃堕棿
//            .build();
    public String post(final String url, final String json) throws IOException {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                RequestBody body = RequestBody.create(JSON, json);
                Request request = new Request.Builder()
                        .url(url)
                        .header("token", "8ee02f525de74425a424cea885fa6926")
                        .addHeader("content-type", "application/json")
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
        new Thread(task).start();
        String s = null;
        try {
            s = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
