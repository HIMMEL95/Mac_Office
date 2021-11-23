package com.example.xml_test.m_DataObject;

public class Item {

    String name;
    String address;
    String height;
    String info;
    String imgUrl;
    String admin;

    public Item(){}

    public Item(String name, String address, String height, String info, String imgUrl, String admin) {
        this.name = name;
        this.address = address;
        this.height = height;
        this.info = info;
        this.imgUrl = imgUrl;
        this.admin = admin;
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
}
