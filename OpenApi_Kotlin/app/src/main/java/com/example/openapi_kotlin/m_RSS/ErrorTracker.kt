package com.example.openapi_kotlin.m_RSS

class ErrorTracker {

    companion object {
        const val WRONG_URL_FORMAT: String = "Error : Wrong URL Format"
        const val CONNECTION_ERROR: String = "Error : Unable To Establish Connection"
        const val IO_ERROR: String = "Error : Unable to Read"
        const val RESPONSE_ERROR: String = "Error : Bad Response - "
    }
}