package com.inah_wook.ex074camerax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 구글의 Jetpack 을 이용한 카메라x
    // https://developer.android.com/training/camerax
    // 예전 카메라 버전들의 기능들을 업글하는  선택적 부가기능을 구현 할 수 있음.
    // 뷰마다 그냥 뷰와 컴펫이 있음. 이 컴펫이라는 단어가 붙으면 옛날 버전에서도 다 돌아가게 만든고.
    // 폰 종류랑 상관 없이 ~
    // 이게 바로 jetpack.

    //정리 : Jetpack에서 만든 카메라 라이브러리 : 카메라 라이브러리를 쉽게 다루기 위한 새로운 라이브러리
    // 1. cameraX libarary 적용 -> build.gradle ( 개발자 사이트 참조 )
    // https://developer.android.com/codelabs/camerax-getting-started#0

//프리뷰를 보여주는 뷰
    PreciewVeiw pvv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 카메라, 오디오녹음(동영상 촬영시 필요), 외부저장소에 대한 동적 퍼미션 요청
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(permissions[1]) != PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(permissions[2]) != PackageManager.PERMISSION_DENIED){

        } else {
            //프리뷰 시작
            startPreview();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0) {
            //퍼미션 중 하나라도 허가되지 않으면 미리보기 안 되도록 하기
            for(int result : grantResults){
                if(result == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this, "카메라 API 사용 불가 \n이 앱을 종료합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                    return; //컷해주지 않으면 finish()로 앱이 꺼지는 동안에도 다음 작업을 하러 간다.
                }
            }//for

            Toast.makeText(this, "카메라 API 사용 가능", Toast.LENGTH_SHORT).show();
            startPreview();
        }
    }





    void startPreview(){

    }



}
