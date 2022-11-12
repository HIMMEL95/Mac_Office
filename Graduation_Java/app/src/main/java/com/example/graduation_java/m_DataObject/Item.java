package com.example.graduation_java.m_DataObject;

public class Item {

    String mtnName, mtnAddress, mtnHeight, mtnAdmin, mtnInfo, mtnAdminNum, mtnListNo, mtnTop;

    public Item(){}

    public Item(String mtnName, String mtnAddress, String mtnHeight, String mtnAdmin, String mtnInfo, String mtnAdminNum, String mtnListNo, String mtnTop){
        this.mtnName = mtnName;
        this.mtnAddress = mtnAddress;
        this.mtnHeight = mtnHeight;
        this.mtnAdmin = mtnAdmin;
        this.mtnInfo = mtnInfo;
        this.mtnAdminNum = mtnAdminNum;
        this.mtnListNo = mtnListNo;
        this.mtnTop = mtnTop;
    }

    public String getMtnName() {
        return mtnName;
    }

    public void setMtnName(String mtnName) {
        this.mtnName = mtnName;
    }

    public String getMtnAddress() {
        return mtnAddress;
    }

    public void setMtnAddress(String mtnAddress) {
        this.mtnAddress = mtnAddress;
    }

    public String getMtnHeight() {
        return mtnHeight;
    }

    public void setMtnHeight(String mtnHeight) {
        this.mtnHeight = mtnHeight;
    }

    public String getMtnAdmin() {
        return mtnAdmin;
    }

    public void setMtnAdmin(String mtnAdmin) {
        this.mtnAdmin = mtnAdmin;
    }

    public String getMtnInfo() {
        return mtnInfo;
    }

    public void setMtnInfo(String mtnInfo) {
        this.mtnInfo = mtnInfo;
    }

    public String getMtnAdminNum() {
        return mtnAdminNum;
    }

    public void setMtnAdminNum(String mtnAdminNum) {
        this.mtnAdminNum = mtnAdminNum;
    }

    public String getMtnListNo() {
        return mtnListNo;
    }

    public void setMtnListNo(String mtnListNo) {
        this.mtnListNo = mtnListNo;
    }

    public String getMtnTop() {
        return mtnTop;
    }

    public void setMtnTop(String mtnTop) {
        this.mtnTop = mtnTop;
    }
}
