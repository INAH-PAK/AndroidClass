package com.inah_wook.ex077butternife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {

    // findViewById() 의 번거로움을 해소하기 위한 외부 라이브러리
    // ButterKnife - jake wharten


    // 버터나이프를 이용하여 xml에서 id 가 tv인 녀석과 연겨


    @BindView(R.id.tv) TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버터나이프가 연결해줄 뷰들을 가진 타켓 변경 ( 타켓 == 액티비티 )
        ButterKnife.bind(this);

        tv.setText("gogogogogogo");

    }

    //버튼 클릭시에 반응하는 메소드
    @OnClick(R.id.btn);
    void clikc(){
        tv.setText("dkdkdkdkdkd");

    }

    @OnLongClick(R.id.btn)
    void longClick(){
        tv.setText("dkdkewrwrefsdfsfdkdkdkd");
    }



}