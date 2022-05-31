package com.wookie_soft.ex101naversearchapi.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.exifinterface.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wookie_soft.ex101naversearchapi.databinding.RecylerItemBinding
import com.wookie_soft.ex101naversearchapi.model.SearchItem

class MyAdapter(val context: Context, val items:MutableList<SearchItem>) : RecyclerView.Adapter<MyAdapter.VH>() {

    inner class VH(itemView: View) :RecyclerView.ViewHolder(itemView){
        val binding:RecylerItemBinding = RecylerItemBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View = LayoutInflater.from(context).inflate(com.wookie_soft.ex101naversearchapi.R.layout.recyler_item, parent, false)// falde :  뷰홀더가붙여줄거라
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        // 그리고 가끔 웹을 크롤링 하면 태그문이 포함되어 있음.
        // 태그문을 없에자.
        var title = HtmlCompat.fromHtml(items[position].title, HtmlCompat.FROM_HTML_MODE_COMPACT)



        // holder안에 모든 내용아ㅣ 있음
        //holder.binding.tv.text = items.get(position).title
        holder.binding.tv.text = title
        holder.binding.lowPrice.text = items[position].lprice
        holder.binding.tvBrand.text = items[position].brand
        Glide.with(context).load(items[position].image).into(holder.binding.iv)

        holder.binding.root.setOnClickListener {
            val intent:Intent = Intent(Intent.ACTION_VIEW , Uri.parse(items[position].link)) // ACTION_VIEW : 화면만 가지면 무조건 띄우는 녀석
            context.startActivity(intent)
        }





    }

//    override fun getItemCount(): Int {
//        return items.size // 이 갯수대로 만들어짐 !!
//    }

    override fun getItemCount(): Int = items.size
}