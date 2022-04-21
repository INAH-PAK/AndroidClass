package com.inah_wook.ex070gallery;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    // 에뮬레이트에 있는 사진을 보여주는 앱
    // 필수적인 기능 임.
    //실행은 run
    // 실행시켜서 가지고 와야하니까,
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(view -> {
            //갤러리 앱 또는사진 앱을 실행시켜서 사진을 가져오장.
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK); //  이 인텐트가 뭔 일 하는 앤지 알려주는 식별자를 해줌

            //ACTION_PICK 옵션은 사진, 동영상, 음악 등 걍 다 가져옴.
            // 그래서 이제 바뀜

            //  intent.setType("image/png"); //  이렇게 ㅏ면 이미지 중 png 갖오는거고
            // 긍까 파일종류( 이미지 동영상 등 정해져 있음.미메타입= 이메일 전송 파일 규약)/확장자 <- 이렇게 써야 함
            //우린 이미지 다 써야하니까
            intent.setType("image/*");

            // startActivity(intent);
            //  startActivityForResult(intent);  -> 이건 동작은 하는데 안스는 비추함, 그래서 디플리케이트 되쓰니까 쓰지말기~
            //근데 선배나 모든 책은 이걸로 쓰니까 신세대인 나는 밑에꺼 쓰지 ~

            //새로운 안드로이드버전 이후 반법
            //A. 결과를 받기위해 새로운 액티비티를 실행시켜주는 객체에게 요청

        });

        // 맴버변수 자리
        //결과를 받기 위해 새로운 액티비티를 실행시켜주는 객체 등록 및 생성
        // 위에 A 이어서임.
        //registerForActivityResult ()는 계약맺는 애랑 그 결과를 받고 반응하는 애가 필요
        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
// 결과를 ㅅ져오는 객체 result
                // 여기 주석 교ㅗ수님꺼에서 가져오기.

                if (result.getResultCode() != RESULT_CANCELED) {

                    Intent intent = result.getData();

                    Uri url = intent.getData();

                    Glide.with(MainActivity.this).load(url).into(iv);

                }
            }

        });


    }
}

