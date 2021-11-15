package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DataTools {
    public static JSONObject modifyJson(String content, String modifyKey,
                                        String modifyValue) {
        JSONObject items = JSON.parseObject(content);
        for (String key : items.keySet()) {
            if (items.get(key) instanceof JSONArray) {
                for (int i = 0; i < ((JSONArray) items.get(key)).size(); i++) {
                    JSONObject temp = modifyJson(
                            (((JSONArray) items.get(key)).get(i).toString()),
                            modifyKey, modifyValue);
                    ((JSONArray)items.get(key)).set(i, temp);


                }
            } else if (items.get(key) instanceof JSONObject) {
                items.put(
                        key,
                        modifyJson((items.get(key)).toString(), modifyKey,
                                modifyValue));
            } else if (modifyKey.equals(key)) {
                items.put(modifyKey, modifyValue);
            }
        }
        return items;
    }

    public static void main(String[] args) {
        String testJson = "{\"a\":\"1\",\"h\":\"8\",\"b\":{\"g\":\"1\",\"h\":\"2\",\"i\":{\"j\":\"5\",\"k\":\"6\"}},\"c\":[{\"d\":\"3\",\"h\":\"4\"},{\"f\":\"5\"}]}";
        System.out.println(DataTools.modifyJson(testJson, "h", "9").toJSONString());
    }
}