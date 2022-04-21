package com.mrhi2022.ex004imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //화면에 보여지는 뷰들의 참조변수들...
    ImageView iv;
    Button btnAus, btnBel, btnCan, btnKor;

    //이미지 index번호
    int num=0;

    Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml에서 만든 뷰객체들을 찾아와서 참조변수에 대입
        iv=findViewById(R.id.iv);
        btnAus=findViewById(R.id.btn_aus);
        btnBel= findViewById(R.id.btn_bel);
        btnCan= findViewById(R.id.btn_can);
        btnKor= findViewById(R.id.btn_kor);

        //각 각의 버튼을 클릭했을때 이미지를 변경
        btnAus.setOnClickListener(listener);
        btnBel.setOnClickListener(listener);
        btnCan.setOnClickListener(listener);
        btnKor.setOnClickListener(listener);

        //이미지뷰도 클릭하면 반응하는 리스너를 설정할 수 있음
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //랜덤하게 국기가 나오게 하려면
                Random rnd= new Random();
                num= rnd.nextInt(13);//0~12
                iv.setImageResource(R.drawable.flag_australia + num );
                //num++;
                //if(num>12) num=0;

            }
        });


        btnPrev= findViewById(R.id.btn_prev);
        btnNext= findViewById(R.id.btn_next);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num--;
                if(num<0) num=0;
                iv.setImageResource(R.drawable.flag_australia+num);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                if(num>12) num=12;
                iv.setImageResource(R.drawable.flag_australia+num);
            }
        });


    }//onCreate method...

    //버튼 클릭을 듣는 리스너객체를 생성하고 참조변수도 멤버변수로 만들기
    View.OnClickListener listener= new View.OnClickListener() {
        // 이 리스너객체가 감시하는 버튼이 클릭되는 자동으로 발동하는 콜백메소드
        @Override
        public void onClick(View view) { //즉, 버튼클릭하여 이 영역이 실행됨
            //이 메소드의 파라미터인 view가 바로 클릭한 버튼객체를 참조하고 있음.
            //이 파라미터 view가 누구인지를 체크하여 해당하는 작업을 하면 됨
            int id= view.getId(); //R장부에 기록된 id는 int형으로 지정되어 있음.
            switch (id){
                case R.id.btn_aus:
                    iv.setImageResource(R.drawable.flag_australia);
                    break;
                case R.id.btn_bel:
                    iv.setImageResource(R.drawable.flag_belgium);
                    break;
                case R.id.btn_can:
                    iv.setImageResource(R.drawable.flag_canada);
                    break;
                case R.id.btn_kor:
                    iv.setImageResource(R.drawable.flag_korea);
                    break;
            }


        }
    };


}//MainActivity class..