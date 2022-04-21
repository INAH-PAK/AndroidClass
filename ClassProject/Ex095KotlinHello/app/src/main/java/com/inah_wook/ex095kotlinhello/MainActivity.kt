package com.inah_wook.ex095kotlinhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// 대략적인 코틀린 코딩 방식 살펴보기

// 1. 코틀린에서 클래스의 상속 키워드 [ : ] 이며,
//    상속하는 클래스 명 옆에 주생성자(primary 생성자자를 호출하는 ()가 필수적으로 있어야 함.

// .java , .kt 파일 컴파일시 동일하게 ->.class 됨.
// JRE 입장에선 똑같음.
// 그냥 라이센스 문제 때문에 개발자만 걍 다르게쓰는고~

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {   // 변수명 : 자료형(null 값을 가질 수 있으면 ? , 코틀린의 기본 접근제한자 = public)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //2. 변수는 var 키워드 사용
        var btn: Button = findViewById(R.id.btn) // var btn; 이렇게 자료형 생략하는게 일반적임.

        //3. 문장에서 세미콜론이 없음
        btn.setOnClickListener {
            clickBtn()
        }




    }

    // 코틀린에서의 함수는 fun 키워드 사용
    fun clickBtn() {
        var tv = findViewById<TextView>(R.id.tv)
        tv.setText("Hello")

        // 코틀린에서는 setXXX() 메소드를 권장하지 않음.
        // 그냥 XXX를 맴버변수다루듯이 대입하는 코딩스타일 권장
        tv.text = "Nice To Meet You~"


    }

    override fun onResume() {
        super.onResume()
        // 코틀린에서 개발할 시, 안드로이드 스튜디오 편집기가 토스트 자동완성 ->toast 소문자로 쓰기.
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show()

    }



}