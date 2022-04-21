package com.inah_wook.ex032fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //
    // Fragment :  한 액티비티 안에서 여러 화면을 보여주고 싶을 때,
    //              이 화면들을 하나의 뷰로 만들어, 다중 화면 구현 ~~
    //  1. Fragment 레이아웃 XML 생성
    //  2. 생성된 XML 파일을 화면에 출력할 Fragment Class 생성
    //  3. activity_main.xml 에 원하는 위치에 Fragment 가 생성될 자리를 정해줌.
    //  4. MainActivity.java 에서 객체 생성, 작업코드 입력 !
    //


    TextView tv;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MyFragment가 보여주는 TextView의 글씨 변경
                // 근데 fragment 가 뷰의 일종이라 findViewById 하려고 했더니 안됨
                // 액티비티가 프레그먼트까지 관리하면 기능이 너무 많아져서 노노



                // ==> 액티비티안에 프레그먼트만 따로 관리하는 관리자 객체가 존재함.
                // 두명 : 서포트 프레그먼트 매니저 : ☆ 최신버전 androidx버전에서 사용 가능 /  프레그먼트 매니저 :android 버전에서 사용 가능
                // 원래는 android support였는데, 이젠 androidx 로 업글되었는데
                // support를 다 x로 못바꿔서
                // 우린 걍 둘 다 같다고 생각 ㄱ'
                FragmentManager fragmentManager = getSupportFragmentManager();


                // 이제 관리자에게 프레그먼트를 찾아달라고 요청하면 됨.
                MyFragment fragment = (MyFragment) fragmentManager.findFragmentById(R.id.fr);

                fragment.tv.setText(" good boy ~");


            }
        });

        //근데 여기에 페이지 여러개의 리스너를 하면 개복잡하니까,
        // main 액티비티가서 프레그먼트 만들기 ㄱㄱ
        // 페이지 조각들을 만들자 !
        // laout




    }
}