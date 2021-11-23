package com.example.listview_openapi_test

import android.os.Bundle
import com.example.listview_openapi_test.OpenAPi.Mountain
import kotlinx.android.synthetic.main.activity_mountain_detail.*
import kotlinx.android.synthetic.main.mountain_list_item.nameTxt

class MountainDetailActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mountain_detail)

        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        // mountainInfo를 serializable로 받는다
        // 그냥 받은 채로 변수에 넣으면 오류가 나는데 이 때 Casting을 해줘야 한다.
        val mountain = intent.getSerializableExtra("mountainInfo") as Mountain

        // activity_mountain_detail.xml에 설정했던 view에 따라 매핑
        nameTxt.text = mountain.mountainName
        addressTxt.text = mountain.mountainAddress
        heightTxt.text = mountain.mountainHeight
        infoTxt.text = mountain.mountainInfo
        adminTxt.text = mountain.mountainAdmin
    }
}