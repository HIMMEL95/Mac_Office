package com.example.openapi_kotlin.m_DataObject

class Item {

    var name: String? = null
    var address: String? = null
    var height: String? = null
    var info: String? = null
    var infoHun: String? = null
    var imgUrl: String? = null
    var traffic: String? = null
    var surround: String? = null
    var surroundImg: String? = null

    fun Item(){}

    fun Item(name: String?, address: String?, height: String?, info: String?, infoHun:String? ,imgUrl: String?, traffic: String?, surround:String?, surroundImg:String?) {
        this.name = name
        this.address = address
        this.height = height
        this.info = info
        this.infoHun = infoHun
        this.imgUrl = imgUrl
        this.traffic = traffic
        this.surround = surround
        this.surroundImg = surroundImg
    }

    @JvmName("setName1")
    fun setName(name: String?) {
        this.name = name
    }
    @JvmName("getName1")
    fun getName(): String? {
        return name
    }

    @JvmName("getAddress1")
    fun getAddress(): String? {
        return address
    }

    @JvmName("setAddress1")
    fun setAddress(address: String?) {
        this.address = address
    }

    @JvmName("getHeight1")
    fun getHeight(): String? {
        return height
    }

    @JvmName("setHeight1")
    fun setHeight(height: String?) {
        this.height = height
    }

    @JvmName("getInfo1")
    fun getInfo(): String? {
        return info
    }

    @JvmName("setInfo1")
    fun setInfo(info: String?) {
        this.info = info
    }

    @JvmName("getImgUrl1")
    fun getImgUrl(): String? {
        return imgUrl
    }

    @JvmName("setImgUrl1")
    fun setImgUrl(imgUrl: String?) {
        this.imgUrl = imgUrl
    }

    @JvmName("getTraffic1")
    fun getTraffic(): String? {
        return traffic
    }

    @JvmName("setTraffic1")
    fun setTraffic(admin: String) {
        this.traffic = traffic
    }

    @JvmName("getSurround1")
    fun getSurround(): String? {
        return surround
    }

    @JvmName("setSurround1")
    fun setSurround(admin: String) {
        this.surround = surround
    }

    @JvmName("getInfoHun1")
    fun getInfoHun(): String? {
        return infoHun
    }

    @JvmName("setInfoHun1")
    fun setInfoHun(admin: String) {
        this.infoHun = infoHun
    }

    @JvmName("getSurroundImg1")
    fun getSurroundImg(): String? {
        return surroundImg
    }

    @JvmName("setSurroundImg1")
    fun setSurroundImg(admin: String) {
        this.surroundImg = surroundImg
    }

}