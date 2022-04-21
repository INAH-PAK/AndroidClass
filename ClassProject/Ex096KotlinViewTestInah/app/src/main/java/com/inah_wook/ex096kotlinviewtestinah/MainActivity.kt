package com.inah_wook.ex096kotlinviewtestinah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// rjdml q바꿀 일 없으면 val

class MainActivity : AppCompatActivity() {

    //  늦은 초기화 lateinit  는   var  만 가능함.
    // 변하지 않을 변수는 val 이 가장 베스트 ! val 의 늦은 초기화 !!  by lazy
    //  by lazy 는 처음 사용될 때 초기화가 진행됨,
    val recycler:RecyclerView by lazy { findViewById(R.id.recycler) }

    // 대량의 데이터들 리스트 참조변수
    var items = mutableListOf<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 이건 연습이니까 대량의 데이터 추가
        items.add(Item("sam" , "죽겠네",R.drawable.bazzi))
        items.add(Item("Inah" , "죽겠네",R.drawable.ms_05))
        items.add(Item("d" , "dddd",R.drawable.ms_03))
        items.add(Item("s" , "dddd",R.drawable.bazzi))
        items.add(Item("fdf" , "dddd",R.drawable.ms_03))
        items.add(Item("dddd" , "dddd",R.drawable.bazzi))
        items.add(Item("sss" , "dddd",R.drawable.ms_03))
        items.add(Item("fhggggf" , "dddd",R.drawable.bazzi))

        // 주석 따라쓰기...
        recycler.adapter = MuAdapter(this, items)

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_aa -> Toast.makeText(this , " aaa ", Toast.LENGTH_SHORT).show()
            R.id.menu_bb -> Toast.makeText(this , " bbb ", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    // 화면 갱신시 리사이클러뷰 초기화
    override fun onResume() {
        super.onResume()

      recycler.adapter?.notifyDataSetChanged()

    }




}