package com.example.listview_test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.listview_test.R
import com.example.listview_test.data.Mountain

// 2. 상속받은 뒤, Adapter 주 생성자에서 필요한 재료 받고
class MountainAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<Mountain>

    // 1. ArrayAdapter<Mountain(뿌려줄 데이터 클래스)>()를 상속받고
    // 3. ArrayAdapter<Mountain>(mContext, resId, mList) 생성자에서 필요한 재료 순으로 부모에게 넘기기
) : ArrayAdapter<Mountain>(mContext, resId, mList) {

    // 4. 객체로 변환해주는 변수를 멤버변수로 생성
    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // 6. convertView 변수를 tempRow에 옮겨담아서 null인 경우 새로운 inflate해서 담기
        // 이렇게 사용하는 이유는 listView를 재사용성하기 위해
        var tempRow = convertView
        if(tempRow == null) {
            tempRow = inf.inflate(R.layout.mountain_list_item, null)
        }

        // tempRow는 맞지만 null은 절대 아니다 (= !!)
        val row = tempRow!!

        return row
    }
}