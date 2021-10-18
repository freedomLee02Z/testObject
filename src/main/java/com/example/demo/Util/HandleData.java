package com.example.demo.Util;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;

public class HandleData {
    @Test
    public void HandleData111(){
        JSONObject json = new ReadYaml().getYamlData("src/test.yaml");
        JSONObject originalData = json.getJSONObject("originalData");
        System.out.println("模板参数"+originalData);
        json.remove("originalData");
        System.out.println("移除模板参数后yaml文件数据："+json);

        for(Object key:json.keySet()){
            JSONObject tempOriginalData=originalData;
            System.out.println("模板参数-"+originalData);
            String modifyKey=key.toString();
            JSONObject keyValue=json.getJSONObject(modifyKey);
            System.out.println("modifyKey:"+modifyKey);
            System.out.println("keyValue:"+keyValue);
            System.out.println("tempOriginalData"+tempOriginalData);
            for(Object key1:keyValue.keySet()){
                tempOriginalData.put(modifyKey,key1);
                System.out.println("修改原始参数"+tempOriginalData);

                try{
                    System.out.println( "请求返回值："+RequestOkhttp.requestPost("http://172.20.109.17:9090/hello",originalData));
                }catch (IOException e){
                        e.printStackTrace();
                }
            }
        }
    }
}
