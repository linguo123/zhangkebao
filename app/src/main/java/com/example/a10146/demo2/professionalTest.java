package com.example.a10146.demo2;

import java.io.Serializable;

/**
 * 这是用来测试的类不清楚实现效果怎么样如果失败可以删除——lgc
 */


public class professionalTest implements Serializable {
    //测试标题，内容，图片
    private String title;
    private String desc;
    private int photoId;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public professionalTest(String name, String age, int photoId) {
        this.title=name;
        this.desc=age;
        this.photoId=photoId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getDesc() {
        return desc;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getTitle() {
        return title;
    }
}