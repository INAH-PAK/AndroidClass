package com.mrhi2022.ex013scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    //화면에 보여지는 뷰들의 참조변수들
    ScrollView scroll;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만든 뷰객체들을 찾아와서 참조변수 대입
        scroll= findViewById(R.id.scroll);
        btn= findViewById(R.id.btn);

        //버튼을 클릭하면 반응하는 리스너를 생성 및 설정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//버튼이 클릭되었을때 실행되는 메소드 영역
                scroll.fullScroll( ScrollView.FOCUS_DOWN );
            }
        });

    }
}