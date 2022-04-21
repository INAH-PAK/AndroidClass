package com.inah_wook.ex076introactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

// 먼저 우린 앱을 키면 먼저 인트로 화면을 띄우고 다음 화면으로 넘어가잖아?
    // 그걸 구현 해 볼건데,
    // 자,
    // 1. 에니메이션이 있는 아이콘 인트로 화면
    // 2. 4초 뒤에 뜨는 메인 엑티비티 화면
    // 3. 메인에서 뒤로가기 버튼을 한 번만 누르면 뜨는 엑티비티 화면 ~

    // 111111 먼저 intro.xml ㄱㄱ



    //인트로 다 하고 왔으면
    //여기선 몰 할거냐~
    // 인트로 화면 에서 만약에, 로그인 한 적 없냐? 그럼 로그인 엑티비티 띄워.
    // 이런식으로
    // 메인 가기 전 중간에 로그인 창이 필요 할 때 사용 할 걸 만들어 보자 ~

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //4초 후에 자동으로 다음 엑티비티로 이동하기 ~

        handler.sendEmptyMessageDelayed(0,4000);


    }

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
            finish();
        }
    };

}