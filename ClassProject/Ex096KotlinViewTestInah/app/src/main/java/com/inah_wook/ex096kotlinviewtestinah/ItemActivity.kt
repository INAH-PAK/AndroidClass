package com.inah_wook.ex096kotlinviewtestinah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ItemActivity : AppCompatActivity() {

    val iv:ImageView by lazy { findViewById(R.id.iv) }
    val tv by lazy { findViewById<TextView>(R.id.tv) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title = intent.getStringExtra("title")
        val msg = intent.getStringExtra("msg")
        val img = intent.getIntExtra("image", R.drawable.ms_03)

        supportActionBar!!.title = title

        tv.text = msg
        iv.setImageResource(img)

        // iv가 전환 효과의 대상이 되도록 별칭 설정
        // 아답터에서 설정했던 별칭과 같다면 연결된거임.
        iv.transitionName = "inah"



        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}