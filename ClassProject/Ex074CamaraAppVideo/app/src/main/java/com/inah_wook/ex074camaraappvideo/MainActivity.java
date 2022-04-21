package com.inah_wook.ex074camaraappvideo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv = findViewById(R.id.vv);
        //퍼미션 받으면 버튼 눌려지고
        //퍼미션 못받으면 안눌리게 ~
        //먼저 퍼미션 주러 매니페스트 ㄱ

        //동적 퍼미션 체크
        String[] permissons = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(checkSelfPermission(permissons[0] == PackageManager.PERMISSION_DENIED)){
            requestPermissions(permissons, 10);
        }



            findViewById(R.id.btn).setOnClickListener(v->{

                        btn.setEnabled();

// 교수님 코드 보고 다시 해보장
            }






            void clickBtn(){
            }

        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                    if() ```
                        // 비디오는 요요


                   }
                }

        )


    }