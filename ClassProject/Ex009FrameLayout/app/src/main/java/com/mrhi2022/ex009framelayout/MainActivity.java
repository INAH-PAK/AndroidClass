package com.mrhi2022.ex009framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn01, btn02, btn03;
    LinearLayout layout01;
    LinearLayout layout02;
    LinearLayout layout03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01= findViewById(R.id.btn01);
        btn02= findViewById(R.id.btn02);
        btn03= findViewById(R.id.btn03);

        layout01= findViewById(R.id.layout01);
        layout02= findViewById(R.id.layout02);
        layout03= findViewById(R.id.layout03);

        //버튼들 클릭할때 마다 반응하는 리스너객체 생성
        View.OnClickListener listener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //일단 전원 다 안보이도록
                layout01.setVisibility(View.GONE);
                layout02.setVisibility(View.GONE);
                layout03.setVisibility(View.GONE);

                //파라미터로 전달된 view : 클릭이 발생된 버튼객체 참조변수
                int id= view.getId();
                switch (id){
                    case R.id.btn01:
                        layout01.setVisibility(View.VISIBLE);
                        break;

                    case R.id.btn02:
                        layout02.setVisibility(View.VISIBLE);
                        break;

                    case R.id.btn03:
                        layout03.setVisibility(View.VISIBLE);
                        break;
                }

            }
        };

        //리스너객체를 버튼들에게 설정
        btn01.setOnClickListener(listener);
        btn02.setOnClickListener(listener);
        btn03.setOnClickListener(listener);
    }
}