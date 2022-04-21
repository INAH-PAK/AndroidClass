package com.mrhi2022.ex05compoundbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //화면에 보여지는 뷰들의 참조변수들
    CheckBox cb01, cb02, cb03;
    Button btn;
    TextView tv;

    ToggleButton toggle;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만든 뷰객체들을 찾아와서 참조변수에 대입
        cb01= findViewById(R.id.cb01);
        cb02= findViewById(R.id.cb02);
        cb03= findViewById(R.id.cb03);
        btn= findViewById(R.id.btn);
        tv= findViewById(R.id.tv);

        toggle= findViewById(R.id.toggle);
        sw= findViewById(R.id.sw);

        //버튼이 클릭되면 반응하는 리스너객체 생성 및 설정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //버튼이 클릭되면 실행되는 영역
                //체크박스들 중에서 체크된 녀석의 글씨을 얻어와서 TextView에 보여주기

                String s=""; //빈 문자열로 초기화
                if( cb01.isChecked() ) s= s + cb01.getText().toString();
                if( cb02.isChecked() ) s+= cb02.getText().toString();
                if( cb03.isChecked() ) s+= cb03.getText().toString();

                tv.setText(s);
            }
        });


        //체크박스의 체크상태가 변경되는 것을 듣는 리스너객체 생성
        CompoundButton.OnCheckedChangeListener changeListener= new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //체크상태가 변경될때 마다 발동하는 콜백메소드 영역

                String s=""; //빈 문자열로 초기화
                if( cb01.isChecked() ) s= s + cb01.getText().toString();
                if( cb02.isChecked() ) s+= cb02.getText().toString();
                if( cb03.isChecked() ) s+= cb03.getText().toString();

                tv.setText(s);
            }
        };

        //리스너를 체크박스에 설정
        cb01.setOnCheckedChangeListener(changeListener);
        cb02.setOnCheckedChangeListener(changeListener);
        cb03.setOnCheckedChangeListener(changeListener);

        //토클버튼의 체크상태가 변경되는것을 듣는 리스너 생성 및 설정
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //이 메소드의 2번째 파라미터 b : 체크상태값 [true/false]

                tv.setText( b + "" );
            }
        });

        //스위치에 체크상태변경 리스너 생성 및 설정
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) tv.setText("사운드를 허용했습니다.");
                else tv.setText("사운드를 허용하지 않았습니다.");
            }
        });


    }
}