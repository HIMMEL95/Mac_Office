package com.example.listview_openapi_test.OpenAPi

import java.io.Serializable

class Mountain(
    val mountainName: String,
    val mountainAddress: String,
    val mountainHeight: String,
    val mountainAdmin: String,
    val mountainInfo: String,
) : Serializable {
}