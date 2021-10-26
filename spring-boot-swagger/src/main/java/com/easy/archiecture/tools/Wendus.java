package com.easy.archiecture.tools;

import com.easy.archiecture.entity.Wendu;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Wendus {
    public static Wendu getWendu(String city_code) {
        Wendu wd;
        //BufferedWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL("http://t.weather.itboy.net/api/weather/city/" + city_code);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5 * 1000);
            connection.connect();
            //connection.setRequestProperty("Connection", "Keep-Alive");
            //connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            //            writer.write(body);
            //            writer.close();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("发送 GET 请求出现异常！");
            e.printStackTrace();
        }
//使用finally块来关闭输出流、输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println(result);
        wd = GsonUtil.fromJson(result.toString(), Wendu.class);
        return wd;
    }
}
