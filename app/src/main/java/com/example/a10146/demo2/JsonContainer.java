package com.example.a10146.demo2;

import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JsonContainer<T> {

    private String status = "None";
    private String errormsg = "None";
    private long timestamp;
    private String md5checksum;
    private T data = null;
    private String token = null;
    private String expire = null;

    @Override
    public String toString() {
        return toJson();
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getErrormsg() {
        return errormsg;
    }
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {this.timestamp = timestamp;}
    public String getMd5checksum() {
        return md5checksum;
    }
    public void setMd5checksum(String md5checksum) {
        this.md5checksum = md5checksum;
    }
    public T getData() {
        if(data != null)
            return data;
        return null;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}
    public String getExpire() {return expire;}
    public void setExpire(String expire) {this.expire = expire;}

    private static String parseStrToMd5L32(String str) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    public String toJson() {
        timestamp = System.currentTimeMillis();
        if (data == null)
            md5checksum = parseStrToMd5L32(status + errormsg + timestamp);
        else
            md5checksum = parseStrToMd5L32(status + errormsg + timestamp + data.toString());
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}