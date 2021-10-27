package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UtilClass {
    @RequestMapping("/getR")
    public Map getR(){
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
        String result = key + sums;
        Map mapResult  = new HashMap<>();
        mapResult.put("result",result);
        return mapResult;
    }
}
