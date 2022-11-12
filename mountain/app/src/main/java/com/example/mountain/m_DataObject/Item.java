package com.example.mountain.m_DataObject;

public class Item {

    String name;
    String address;
    String height;
    String info;
    String imgUrl;
    String admin;
    String listNo;
    String adminNum;
    String traffic;
    String surround;
    String surroundImg;

    public Item(){}

    public Item(String name, String address, String height, String info, String imgUrl, String admin, String listNo, String adminNum, String traffic, String surround, String surroundImg) {
        this.name = name;
        this.address = address;
        this.height = height;
        this.info = info;
        this.imgUrl = imgUrl;
        this.admin = admin;
        this.listNo = listNo;
        this.adminNum = adminNum;
        this.traffic = traffic;
        this.surround = surround;
        this.surroundImg = surroundImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getListNo() {
        return listNo;
    }

    public void setListNo(String listNo) {
        this.listNo = listNo;
    }

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getSurround() {
        return surround;
    }

    public void setSurround(String surround) {
        this.surround = surround;
    }

    public String getSurroundImg() {
        return surroundImg;
    }

    public void setSurroundImg(String surroundImg) {
        this.surroundImg = surroundImg;
    }

}
