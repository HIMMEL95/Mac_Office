package com.example.listview_openapi_test

import android.content.Intent
import android.os.Bundle
import com.example.listview_openapi_test.Adapter.MountainAdapter
import com.example.listview_openapi_test.OpenAPi.Mountain
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){

    // 액티비티에서 실제 목록을 담아줄 ArrayList를 만들고 실제 데이터들을 담기
    val mMountainList = ArrayList<Mountain>()

    // 만들어둔 Adapter 클래스를 액티비티에 있는 리스트뷰와 연결
    lateinit var mMountainAdapter: MountainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        // 메인 화면의 이벤트 관련 코드를 모아두는 장소
        // 리스트 클릭 이벤트 - 리스트뷰의 각 줄이 눌리는 시점의 이벤트
        mountainListView.setOnItemClickListener { adapterView, view, i, l ->

            // 눌린 위치에 해당하는 목록이 어떤 목록인지 가져오기
            val clickedMountain = mMountainList[i]
            // 선택된 목록 정보를 가져왔으면 이제 화면 이동
            val myIntent = Intent(mContext, MountainDetailActivity::class.java)
            // 정보를 담아주기
            // 2번에서는 해당 부분 오류남. 3번하고 난 다음 여기로 다시 와야함
            myIntent.putExtra("mountainInfo", clickedMountain)
            // 화면 전환
            startActivity(myIntent)
        }
    }

    override fun setValues() {
        mMountainList.add(Mountain("설악산", "설악산", "500", "전북", "ㄴㅇㄹㄴㅇ" ))

        // Adapter 클래스를 객체화
        // BaseActivity의 mContext, 어떤 리스트를 보여줄건지, 목록변수의 이름
        // lateinit var로 초기화를 미뤘던 변수의 실제 초기화 코드
        // (mContext=어떤 화면에서?, mountain_list_item=어떤 모양으로 그릴지?, mMountainList=어떤 목록?)
        mMountainAdapter = MountainAdapter(mContext, R.layout.mountain_list_item, mMountainList)

        // 객체화된 adapter변수를 리스트뷰의 어댑터로 지정
        // 실제 목록을 리스트뷰에 뿌려준다.
        mountainListView.adapter = mMountainAdapter
    }
}