package com.inah_wook.ex076introactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    // layout.xml 만들고 anim.xml 만들었으면
    //여기서 이제 이어주기 작업 고고
    // 아 맞다 ! 액티비티 화면 순서는 매니페스트가서 작업 하는건데ㅡ
    // intent-filter 있는 애가 맨 첨 뜸 ~
    //암튼 하고 왔으면 작업 시작하쟝 ~

ImageView iv;
TextView tv;

    Animation ani, ani2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        //에니메이션 객체 생성 : xml 에 생성한고

        ani= AnimationUtils.loadAnimation(this,R.anim.apear_logo);
        iv = findViewById(R.id.iv);
        iv.startAnimation(ani);

        // 에니메이션에 대한 동작에 대한걸 확인하는 에니메이션 리스너
        ani.setAnimationListener(new Animation.AnimationListener() {

            // 에니메이션 시작 시 하고픈 일 기입
            @Override
            public void onAnimationStart(Animation animation) {

            }

            // 에니메이션 끝났을때 하고픈 일 기입
            @Override
            public void onAnimationEnd(Animation animation) {

                // 다음으로 넘어가는 버튼을 만들거나~
                // 아예 다른 화면으로 넘어가도록 ~
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            //애니메이션이 반독될 때 호출
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



tv=findViewById(R.id.tv);

ani2 = AnimationUtils.loadAnimation(this,R.anim.apear_title);
tv.startAnimation(ani2);

    }
}