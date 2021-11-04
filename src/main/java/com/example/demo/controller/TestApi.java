package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {
    @PostMapping("/hello")
    public JSONObject hello(@RequestBody String data){
        JSONObject json = JSONObject.parseObject(data);
        return json;
    }

    @GetMapping("test")
    public String Test(){
        return "test123456";
    }
}
