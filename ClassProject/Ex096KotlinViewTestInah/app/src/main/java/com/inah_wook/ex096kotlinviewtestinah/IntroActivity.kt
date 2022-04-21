package com.inah_wook.ex096kotlinviewtestinah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class IntroActivity : AppCompatActivity() {

    //맴버변수  ( 코틀린은 반드시 맴버변수 초기화 해야 함. )
    var btn01:Button? = null   // nullable 변수

    //   -> 늦은 초기화로 나중에 초기화 ~
    lateinit var btn02:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        btn01 = findViewById(R.id.btn01)
        // 익명클래스로 리스너 처리해보자
        // 코틀린은 new라는 키워드 자체가 없음
        // 또 ! nullable 변수를 사용할 땐  NullSafe 연산자를 꼭 써야 함 !!!    ->    [    ?.    ] : null이면 아예 실행을 안함.
        btn01?.setOnClickListener ( object:View.OnClickListener{
            override fun onClick(p0: View?) {
                // 자바에서 메인 실행할 때
                //  Intent intent = new Intent(IntroActivity.this, MainActivity.class)

                // 코틀린에선 ~
                val intent: Intent = Intent( this@IntroActivity, MainActivity::class.java)
                startActivity(intent)
            }

        })    // btn01 리스너

        // 근데 이렇게 하니까 초기화 졸라 귀찮음
        //   -> 늦은 초기화

        btn02 = findViewById(R.id.btn02)
        // 익명 클래스보다 표기법이 간소한 SAM 변환 사용해보쟈~ 람다식이랑 비슷~~
        // 메소드가 한개일때,
        btn02.setOnClickListener{
            finish()
        }







    }
}