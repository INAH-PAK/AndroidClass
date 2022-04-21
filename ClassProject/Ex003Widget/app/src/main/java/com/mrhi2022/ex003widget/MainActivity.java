package com.mrhi2022.ex003widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //화면에 보여지는 View들을 제어하는 참조변수는 가급적 멤버변수로...
    TextView tv;
    Button btn;

    EditText et;
    Button btn2;
    TextView tv2;

    //이 액티비티가 생성되면 자동으로 실행되는 콜백메소드
    //이 메소드안에서 보여줄 뷰들을 설정하는 작업과 초기화 작업을 함.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만들어진 View객체를 찾아와서 참조변수에 대입
        //액티비티 클래스는 연결된 xml문서에서 뷰를 찾아주는 기능 메소드가 이미 있음.
        tv= this.findViewById(R.id.tv);
        btn= findViewById(R.id.btn);

        //버튼이 클릭되는 것을 듣는 리스너객체 생성 및 추가
        btn.setOnClickListener(new View.OnClickListener() {
            //이 리스너객체가 감시하는 버튼이 클릭되면 자동으로 발동하는 콜백메소드
            @Override
            public void onClick(View view) {
                tv.setText("Nice to meet you.");
            }
        });


        //실습 1) 버튼2를 클릭할때 EidtText의 글씨를 읽어와서 TextView2에 보여주기
        et= findViewById(R.id.et);
        btn2= findViewById(R.id.btn2);
        tv2= findViewById(R.id.tv2);

        //btn2이 클릭되는 것을 듣는 리스너객체를 생성 및 설정
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에게 써있는 글씨를 얻어오기
                //Editable able= et.getText();
                //String s= able.toString();
                String s= et.getText().toString();

                //얻어온 글씨를 TextView에게 설정하기(보여주기)
                tv2.setText(s);
            }
        });

    }

}