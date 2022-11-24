package main.entity;

import java.io.Serializable;

public class Building implements Serializable {
    //景点名称
    private String buildName;
    //景点描述
    private String message;
    //景点编号
    private int no;

    public Building(String buildName, String message, int no) {
        this.buildName = buildName;
        this.message = message;
        this.no = no;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNo() {
        return no;
    }
}

