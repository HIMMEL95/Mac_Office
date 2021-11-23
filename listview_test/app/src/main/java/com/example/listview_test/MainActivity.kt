package com.example.listview_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.listview_test.adapters.MountainAdapter
import com.example.listview_test.data.Mountain
import com.example.listview_test.data.MountainData
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import org.json.JSONObject


class MainActivity : BaseActivity() {

    val mMountainList = ArrayList<Mountain>()

    lateinit var mMountainAdapter: MountainAdapter

    var mtnName: TextView? = null
    var mtnAddress: TextView? = null
    var mtnHeight: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mtnName = findViewById(R.id.mountainTxt)
        mtnAddress = findViewById(R.id.addressTxt)
        mtnHeight = findViewById(R.id.heightTxt)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        // 메인화면의 이벤트관련 코드를 모아두는 장소
        // 리스트 클릭 이벤트 - 리스트뷰의 각 줄이 눌리는 시점의 이벤트
        mountainListView.setOnItemClickListener { adapterView, view, i, l ->

            // 눌린 위치에 해당하는 목록이 어떤 목록인지 가져오기
            val clickedMountain = mMountainList[i]

            // 선택된 목록정보를 가져왔으면 이제 화면 이동
            val myIntent = Intent(mContext, MountainDetailActivity::class.java)

            // 정보를 담아주기
            myIntent.putExtra("mountainInfo", clickedMountain)

            // 화면 전환
            startActivity(myIntent)
        }
    }

    override fun setValues() {

        val mountainData = MountainData()

        // 실제로 뿌려줄 데이터들 ArrayList 변수에 추가
        // 산이름, 주소, 높이 , 정보
        mMountainList.add(Mountain("신성하고 숭고한 산이라는 뜻에서 예로부터 설산(雪山)·설봉산(雪峰山)·설화산(雪華山) 등 여러 이름으로 불렸고, 금강산(1,638m)을 서리뫼[霜嶽]라고 한 것과 관련해 우리말로 설뫼[雪嶽]라고도 하였다. 남한에서는 한라산(1,950m)·지리산(1,915m)에 이어 세 번째로 높은 산이다.", "설악산", "강원 인제군 북면 한계리" ,"1708", "강원도"))
        mMountainList.add(Mountain("관악산(冠岳山)은 그 꼭대기가 마치 큰 바위기둥을 세워 놓은 모습으로 보여서 ‘갓 모습의 산’이란 뜻의 ‘갓뫼(간뫼)’ 또는 ‘관악(冠岳)’이라고 했다. 관악산은 빼어난 수십 개의 봉우리와 바위들이 많고, 오래 된 나무와 온갖 풀이 바위와 어우려서 철따라 변하는 산 모습이 마치 금강산과 같다 하여 ‘소금강(小金剛)’ 또는 서쪽에 있는 금강산이라 하여 ‘서금강(西金剛)’이라고도 하였다.", "관악산","경기 과천시 중앙동", "632", "경기"))
        mMountainList.add(Mountain("제주도는 제3기 말∼제4기 초 우리나라의 지각의 역사에서 비교적 최근에 형성된 화산섬으로 수차례의 화산활동에 의해 용암이 분출되며 형성되었다. 한라산은 제주도의 대부분을 차지하고 있으며 제주도 전체가 거대한 한라산의 연속체로 보이기도 한다. 전체적으로 경사가 완만한 순상화산의 형태를 띠고 있으나 정상부에는 경사가 급한 종상화산이 위치하는 이중화산의 형태가 나타난다. 대부분 현무암으로 이루어져 있으며, 제주도 중앙에서 산줄기가 동서로 뻗어 있다. 남쪽은 경사가 심한 반면 북쪽은 완만하고 동서방향으로는 비교적 지대가 높고 평탄하다. 정상에는 둘레 약 3㎞, 지름 500m의 화구호인 백록담(白鹿潭)이 있으며, 주위 사방에 흙붉은오름[土赤岳]·사라오름[砂羅岳]·성널오름[城板岳]·어승생오름[御乘生岳] 등 360여 개의 기생화산을 거느리고 있다. 또 해안지대에는 폭포와 주상절리 등 아름다운 화산지형이 펼쳐지고, 해발고도에 따라 아열대·온대·냉대 등 1,800여 종에 달하는 식물이 자생하여 고도에 따른 식생의 변화가 뚜렷하다. 봄의 철쭉·진달래·유채, 가을의 단풍, 겨울의 설경과 운해가 절경이며, 곳곳에서 한라산의 상징인 노루를 볼 수 있다.", "한라산","제주 서귀포시 토평동 산15-1", "1947", "제주"))
        mMountainList.add(Mountain(mountainData.mountainInfo, mountainData.mountainName, mountainData.mountainAddress, mountainData.mountainHeight, mountainData.mountainAdmin))

        // Adapter 클래스를 객체화
        // BaseActivity의 mContext, 어떤 리스트를 보여줄건지, 목록변수의 이름
        // lateinit var로 초기화를 미뤘던 변수의 실제 초기화 코드
        // (mContext=어떤화면에서?, romm_list_item=어떤모양으로그릴지?, mRoomList=어떤목록?)
        mMountainAdapter = MountainAdapter(mContext, R.layout.mountain_list_item, mMountainList)

        // 객체화된 adapter변수를 리스트뷰의 어댑터로 지정
        // 실제 목록을 리스트뷰에 뿌려준다.
        mountainListView.adapter = mMountainAdapter
    }

    fun mOnClick(view: View?) {
        val assetManager = assets
        try {
            val `is` = assetManager.open("jsons/mountain.json")
            val isr = InputStreamReader(`is`)
            val reader = BufferedReader(isr)
            val buffer = StringBuffer()
            var line = reader.readLine()
            while (line != null) {
                buffer.append(line + "\n")
                line = reader.readLine()
            }
            val jsonData = buffer.toString()

            val jsonObject = JSONObject(jsonData)

            val name = jsonObject.getString("산 이름")
            val address = jsonObject.getString("산정보소재지")
            val height = jsonObject.getString("산 높이")

            mtnName?.setText(name)
            mtnAddress?.setText(address)
            mtnHeight?.setText(height)

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
