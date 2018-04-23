package com.example.a10146.demo2;

/**
 * Created by 10146 on 2018/4/7.
 */

public class Status {
    private String status;
    private String errormsg;
    private String timestamp;
    private String md5checksum;

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

    public String getMd5checksum() {
        return md5checksum;
    }

    public void setMd5checksum(String md5checksum) {

        this.md5checksum = md5checksum;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
//    public static String parseStrToMd5L32(String str){
//        String reStr = null;
//        try {
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            byte[] bytes = md5.digest(str.getBytes());
//            StringBuffer stringBuffer = new StringBuffer();
//            for (byte b : bytes){
//                int bt = b&0xff;
//                if (bt < 16){
//                    stringBuffer.append(0);
//                }
//                stringBuffer.append(Integer.toHexString(bt));
//            }
//            reStr = stringBuffer.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return reStr;
//    }
    public String toString() {




        return "User [status ="+status+",errormsg="+errormsg+",timestamp= "+timestamp+",md5checksum=" +md5checksum+"  ]" ;
    }
}
