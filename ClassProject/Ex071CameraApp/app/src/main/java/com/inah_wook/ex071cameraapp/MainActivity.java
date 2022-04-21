package com.inah_wook.ex071cameraapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.net.URL;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(view -> {
            //카메라 앱을 실행시키는 인텐트

            //1
            //3
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //이제 결과를 바딕 위한 액티비티를 실행시켜주는 객체에게 실행 요청 -> A

            //4
            resultLauncher.launch(intent);

        });


    }

    //A
    //2
    ActivityResultLauncher resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // result 는 결과받은 객체
            if( result.getResultCode() == RESULT_OK){

                //마시멜로 m 버전 부터 앱으로 실행한 카메라 앱으로 촬영한 사진을 곧바로 자동저장되지 않고,
                //결과를 단순 비트맵( 이미지객체) 로 줌
                // 긍까 에뮬로 사진찍으면 바로 사진으로 저장되는데
                // M 이후엔 비트맵 ' 객체 ' 를 줌.
                // 근데 샤오미, 삼성 등 제조사 마다 다름.
                //샤오미는 자동저장되고 따른건 안되고 모든 디바이스가 달름 ㅜ

                // 그럼 일단 결과를 가져오자.
                //택배기사 하나 고용
                Intent intent = result.getData();
                Uri uri = intent.getData(); // 경로가 있을 때 받는 방법이고, 다른 데이터는 B

                // [요즘 모든 회사는 이렇게 씀 ~~~]
                if(uri == null){ //  파일이 저장되어있지 않다면, Bitmap으로 받음
                    Toast.makeText(MainActivity.this, "비트맵으로 받음", Toast.LENGTH_SHORT).show();
                    //B
                    Bundle bundle = intent.getExtras(); // Bundle == 꾸러미
                    Bitmap bip = (Bitmap) bundle.get("data"); // 이 data는 정해진 글자임 ~
                    Glide.with(MainActivity.this).load(bip).into(iv);
                    // 근데 이렇게 찍으면 화질 좆구림
                    // Bitmap 객체는 촬영된 사진의 섬네일 이미지임.존나 작은거
                    //.. 그러다 보니 사진을 크게 보면 화질이 매우 나쁨
                    // 그래서 실제 카메라 앱을 사용할땨는
                    //파일로 저장되도록 미리 작업해놓고 실행해야 함.
                    // 이 예제는 그냥 카메라 확인용으로 생각하고
                    //다음 예제 ㄱㄱ~~ 이제 찐임 ~

                }else{
                    Toast.makeText(MainActivity.this, "사진인 Uri로 받음", Toast.LENGTH_SHORT).show();
                    Glide.with(MainActivity.this).load(uri).into(iv);
                }
            }


        }
    });



}