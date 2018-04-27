package com.example.a10146.demo2;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class JsonWarp {

    private Map data = new HashMap<String,String>();

    @Override
    public String toString() {
        return toJson();
    }
    public Map getData() {
        return data;
    }
    public void setData(String key,String value) {
        this.data.put(key,value);
    }
    private String toJson() {
        Gson gson = new Gson();
        return gson.toJson(data);
    }
}