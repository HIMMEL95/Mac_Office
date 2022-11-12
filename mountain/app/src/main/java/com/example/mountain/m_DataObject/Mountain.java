package com.example.mountain.m_DataObject;

public class Mountain {

    String imgName, imgFileName;

    public Mountain() {}

    public Mountain(String imgName, String imgFileName) {
        this.imgName = imgName;
        this.imgFileName = imgFileName;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }


}
