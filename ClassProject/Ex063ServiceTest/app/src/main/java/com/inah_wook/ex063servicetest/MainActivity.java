package com.inah_wook.ex063servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
// 우린 방금 062 예제에서 스레드로 백그라운드 작업을 하면 안된다는 것을 배웠어.
//이 예제에서는 이 단점을 보안하는 서비스라는 기능을 알아볼거야.


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(view -> startMusic());
        findViewById(R.id.btn_stop).setOnClickListener(view -> stopMusic());


    }


    @Override
    public void onBackPressed() {
        finish(); // 앱을 눌러도 뮤직서비스는 실행중 ~~~
    }

    void startMusic(){
        //백그라운드 작업용 Service 컴포넌트 실행!
        Intent intent = new Intent(this,MusicService.class);


        //Oreo(api26) 버전 부터는 서비스를 스냥 start 명령으로 백그라운드로 돌아가게 하면
        //중간에 자동으로 꺼버림. . .
        // 그래서어어 서비스도 foreground service 라는 개념을 만들었당 ~
        // 즉 쉽게 말해서 서비스는 백그라운드에서 동작하지만, 사용자가 이를 인식하도록 하기 위해
        //반드시 notification과 함께 실행되도록 강제한 기법
        // 자, 원래 서비스에는 startService, stopService 두개가 있잖아? 요기서 startForegroundService 추가하면 됨 ~
       if(Build.VERSION.SDK_INT>=26) startForegroundService(intent);
       else startService(intent);

    }

    void stopMusic(){

        Intent intent = new Intent(this,MusicService.class);
        stopService(intent);

    }

}