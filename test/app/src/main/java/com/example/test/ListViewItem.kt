package com.example.test

class ListViewItem {

    private var mountainName: String? = null
    private var mountainAddress: String? = null
    private var mountainHeight: String? = null

    constructor(mountainName: String, mountainAddress: String, mountainHeight: String) {
        this.mountainName = mountainName
        this.mountainAddress = mountainAddress
        this.mountainHeight = mountainHeight
    }

    fun setMountainName(mountainName: String) {
        this.mountainName = mountainName
    }

    fun setMountainAddress(mountainAddress: String) {
        this.mountainAddress = mountainAddress
    }

    fun setMountainHeight(mountainHeight: String) {
        this.mountainHeight = mountainHeight
    }

    fun getMountainName(): String? {
        return this.mountainName
    }

    fun getMountainAddress(): String? {
        return this.mountainAddress
    }

    fun getMountainHeight(): String? {
        return this.mountainHeight
    }
}