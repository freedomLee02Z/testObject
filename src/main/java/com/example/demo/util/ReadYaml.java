package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;

public class ReadYaml {
    public JSONObject getYamlData(String fileName){
        JSONObject jsonObject=null;
        try {
            Yaml yaml = new Yaml();
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            jsonObject = yaml.loadAs(fileInputStream, JSONObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
