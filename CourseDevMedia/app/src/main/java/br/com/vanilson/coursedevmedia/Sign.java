package br.com.vanilson.coursedevmedia;

import java.io.Serializable;

public class Sign implements Serializable{
    private int iniDate;
    private int endDate;
    private int iniMonth;
    private int endMonth;
    private String name;
    private String image;

    public Sign() { }

    public Sign(int iniDate, int iniMonth, int endDate, int endMonth, String name, String image) {
        this.iniDate = iniDate;
        this.endDate = endDate;
        this.iniMonth = iniMonth;
        this.endMonth = endMonth;
        this.name = name;
        this.image = image;
    }

    public int getIniDate() {
        return iniDate;
    }
    public int getEndDate() {
        return endDate;
    }
    public int getIniMonth() {
        return iniMonth;
    }
    public int getEndMonth() {
        return endMonth;
    }
    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }
}