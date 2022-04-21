package com.inah_jinwook.ex013scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    //화면에 보여지는 뷰 들의 참조변수들
    ScrollView scroll ;
    Button downbtn ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만든 뷰 객체들을 찾아와서 참조 변수 대입
        scroll = findViewById(R.id.scroll);
        downbtn = findViewById(R.id.downbtn);

        //버튼을 클릭하면 반응하는 리스너를 생성 및 설정

        downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 버튼이 클릭되었을 때 발생할 메소드 ~
            scroll.fullScroll( ScrollView.FOCUS_DOWN);  //옵션값이 int일 때 몬지 모르면 클래스 이름. 을 하자 ~
            }
        });


    }
}