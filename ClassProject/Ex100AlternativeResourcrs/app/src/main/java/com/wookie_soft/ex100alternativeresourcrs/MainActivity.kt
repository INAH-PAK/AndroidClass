package com.wookie_soft.ex100alternativeresourcrs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 멀티플 리소스, 페러럴 리소스, 얼터네이티브 리소스 라고 불림.
        // 사용자의 폰이 언어, 화면전환 등이 있는데 일일히 다 적용하기 힘들잖아?
        // 그럴 때 쓰는 기능 !!

        // xml파일을 하위폴더 식으로 적용해서 이름 같게 해서 R장부가 자동으로 알아듣도록 ~~


    }
}