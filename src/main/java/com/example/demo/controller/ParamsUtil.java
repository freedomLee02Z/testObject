package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class ParamsUtil {
    /**
     * 生成指定范围内的数字
     *
     * @param startNub 生成最小值
     * @param endNub   生成最大值
     * @return 生成指定范围的数值
     */
    public static int generateNub(int startNub, int endNub) {
        Random random = new Random();
        int randomValue = random.nextInt(endNub - startNub) + startNub;
        return randomValue;
    }

    /**
     * 生成指定长度的字母
     *
     * @param generateLen 生成字母的长度
     * @param type        生成字母的方式（大小写，默认为小写，传big变更为大写）
     * @return
     */
    public static String generateEng(int generateLen, String type) {

        char setTacit = 'a';
        String str = "";
        if (type.equals("big")) {
            setTacit = 'A';
        }
        for (int i = 0; i < generateLen; i++) {
            str = str + (char) (Math.random() * 26 + setTacit);
        }
        return str;
    }


    /**
     * 生成指定长度的汉字
     *
     * @param len 需要生成的长度
     * @return String
     */
    public static String getRandomName(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    /**
     * json参数合并
     * 存在bug
     *
     * @param oldJson    原json串
     * @param modifyJson 需要修改的参数
     */
    public static JSONObject mergeJSON(String oldJson, String modifyJson) {
        if (modifyJson == null || modifyJson == "") {
            JSONObject items = JSONObject.parseObject(oldJson);
            return items;
        }

        JSONObject modifyData = JSONObject.parseObject(modifyJson);
        JSONObject items = JSONObject.parseObject(oldJson);
        System.out.println("items-->" + items);
        for (String modifyKey : modifyData.keySet()) {
            for (String key : items.keySet()) {
                if (items.get(key) instanceof JSONObject) {
                    items.put(key, mergeJSON(items.get(key).toString(), modifyData.remove(modifyKey).toString()));
                } else if (items.get(key) instanceof JSONArray) {
                    for (int i = 0; i < ((JSONArray) items.get(key)).size(); i++) {
                        JSONObject temp = mergeJSON(((JSONArray) items.get(key)).get(i).toString(), modifyJson);
                        ((JSONArray) items.get(key)).set(i, temp);
                    }
                    if (key.equals(modifyKey)) {
                        items.put(modifyKey, modifyData.get(modifyKey));
                        break;
                    }
                }
            }
        }
        return items;
    }

    /**
     * 时间戳转date
     *
     * @param s 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        res = simpleDateFormat.format(new Date(new Long(s)));
        return res;
    }

    /**
     * 浅合并
     *
     * @param resource 原json
     * @param target   需要修改的信息
     * @return 合并后的json
     */
    public static JSONObject jsonMerge(JSONObject resource, JSONObject target) {
        if (target == null || target.equals("")) {
            return resource;
        }
        for (String key : target.keySet()) {
            resource.put(key, target.get(key));
        }
        return resource;
    }


    /***
     * 深层次删除，所有与list中数据匹配的key都删除
     * @param resource  原始json
     * @param target    需要移除的target
     * @return
     */
    public static JSONObject jsonRemove(JSONObject resource, ArrayList target) {
        JSONObject items = resource;
        if (target == null || target.equals("")) {
            return items;
        }
        for (Object value : target) {
            Iterator<String> it = items.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                if (key.equals(value)) {
                    it.remove();
                } else if (items.get(key) instanceof JSONObject) {
                    items.put(key.toString(), jsonRemove(JSONObject.parseObject(items.get(key).toString()), target));
                } else if (items.get(key) instanceof JSONArray) {
                    for (int i = 0; i < ((JSONArray) items.get(key)).size(); i++) {
                        JSONObject temp = jsonRemove(JSONObject.parseObject(((JSONArray) items.get(key)).get(i).toString()), target);
                        ((JSONArray) items.get(key)).set(i, temp);
                    }
                }
            }
        }
        return items;
    }
}
