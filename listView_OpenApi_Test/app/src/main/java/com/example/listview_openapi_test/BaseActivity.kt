package com.example.listview_openapi_test

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity()  {

    // 어느 화면인지 알려줄 떄 쓰는 용도의 변수 mContext 멤버변수로 지정
    val mContext = this

    // 이벤트 처리 코드를 모아두게 될 함수
    abstract fun setupEvents()

    // 화면에 데이터를 뿌려는데에 관련된 코드를 모아두게 될 힘수
    abstract fun setValues()
}