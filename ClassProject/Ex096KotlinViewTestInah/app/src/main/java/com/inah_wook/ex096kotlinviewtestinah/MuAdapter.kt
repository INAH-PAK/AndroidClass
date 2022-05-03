package com.inah_wook.ex096kotlinviewtestinah

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView

class MuAdapter constructor(val context: Context,var items: MutableList<Item>): RecyclerView.Adapter<MuAdapter.VH>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflator: LayoutInflater = LayoutInflater.from(context)
        val itemView = inflator.inflate(R.layout.recycler_item, parent, false )
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var item = items.get(position)

        holder.tv.setText(item.title)
        holder.iv.setImageResource(item.img)

        holder.itemView.setOnClickListener{
            // ItemActivity를 실행하면서 클릭한 Item의 정보를 넘겨보자
            val intent = Intent(context,ItemActivity::class.java)
            intent.putExtra("title" , item.title)
            intent.putExtra("msg" , item.msg)
            intent.putExtra("image", item.img)


            // 화면 전환시 연관된 뷰들의 화면 전환 효과 적용하기
            val optionsCompat:ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context as MainActivity, Pair(holder.iv,"inah")) //애니메이션을 적용 할 뷰의 별칭을 적용

            context.startActivity(intent,optionsCompat.toBundle())
        }
    }

    // 함수인데 리턴결과가 이렇다 ~ 이런식으로도 쓸 수 있음
    override fun getItemCount(): Int =  items.size

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){

    var tv: TextView = itemView.findViewById(R.id.tv)
    var iv: ImageView = itemView.findViewById(R.id.iv)


    }

}