package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

public class RequestOkhttp {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static String requestPost(String url, JSONObject json) throws  IOException{

        System.out.println("url:"+url+"\t"+"json"+json);
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response  response= client.newCall(request).execute();
            String responseBody = response.body().string();
            response.close();
            return responseBody;

    }
}
