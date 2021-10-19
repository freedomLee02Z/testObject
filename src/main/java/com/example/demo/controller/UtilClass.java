package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UtilClass {
    @RequestMapping("/getR")
    public String getR(@RequestBody JSONObject version){
        Double num = (double)(new Date()).getTime() * Math.random() * 10000.0D;
        Long parseNum = (new Double(num)).longValue();
        Long val = Math.abs(parseNum);
        String key = val + "";
        int sum = 0;

        for(int i = 0; i < key.length(); ++i) {
            sum += Integer.parseInt(String.valueOf(key.charAt(i)));
        }

        sum += key.length();
        String sums = String.format("%03d", sum);
        String result = version + key + sums;
        System.out.println(result);
        return result;
    }
}