package com.inah_wook.ex000sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.SoundPool;

// Sound 재생 종류 두가지.
//1. Media Player - 긴 음악, 동영상 등 ( Thread 배워야 사용 가능)
// 2. SoundPool  -  긴 음원 불가. 9초 이내 재생 :  효과음
//               - 라디오 기기라고 생각하면,




public class MainActivity extends AppCompatActivity {


    //1. 뷰들의 참조변수를 만들자

    Button btnStart,btnAgain,btnGoodjob,btnMusic;

    // 사운드 풀 객체 생성을 위한 빌더
    SoundPool.Builder builder ;

    //등록된 사운드음원들의 식별번호 저장 변수
    int sdStart, sdAgain, sdGoodJob, sdMusic;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        btnAgain = findViewById(R.id.btn_again);
        btnGoodjob = findViewById(R.id.btn_goodjob);
        btnMusic = findViewById((R.id.btn_music));

        builder = new SoundPool.Builder();
        builder.setMaxStreams(10);

        // 사운드풀 객체( 음악 플레이어 ) 를 생성하여 효과음을 등록. 얘도 빌더가 만듬
        SoundPool sp= builder.build();

        // 사운드를 하나씩 등록하면 자동으로 음원들을 구별하는 식별번호(id)가 리턴됨.
        sdStart = sp.load(this,R.raw.s_sijak,0); // priority (우선순위)는 일단 0 , play 시 우선순위 주려고,
        sdAgain = sp.load(this,R.raw.s_again,0);
        sdGoodJob = sp.load(this,R.raw.s_goodjob,0);
        sdMusic = sp.load(this,R.raw.kalimba,0);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // s_sijak.mp3 음원을 등록하여 받는 식별번호 (sdStart)를 이용하여 효과음 플레이
                // 자바, 안드로이드는 소숫점 작성시, 자동으로 double형이 됨.형변환 필수 !
                if(sp!=null)sp.play(sdStart,1.0F,1.0F,3,2,1.0f);


            }
        });

btnAgain.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(sp!=null) sp.play(sdAgain,1.0f,1.0f,2,1,1.0f);
    }
});

btnGoodjob.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(sp!=null) sp.play(sdGoodJob,1.0f,1.0f,1,1,1.0f ); }
});

// 긴 음원은 재생중에 멈춤. 5-9초 사이에 자동으로 멈츔.. . -> 긴 음원은 미디어 플레이어 이용. 추후 수업 예정.
btnMusic.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(sp!=null) sp.play(sdMusic,1.0f,1.0f,1,1,1.0f ); }

});

    }
}