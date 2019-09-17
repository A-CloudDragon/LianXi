package com.jiyun.pinglun;

public class itemBean {
    private String name;
    private String nameimg;
    private String title;

    public itemBean(String name, String nameimg, String title) {
        this.name = name;
        this.nameimg = nameimg;
        this.title = title;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameimg() {
        return nameimg;
    }

    public void setNameimg(String nameimg) {
        this.nameimg = nameimg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
