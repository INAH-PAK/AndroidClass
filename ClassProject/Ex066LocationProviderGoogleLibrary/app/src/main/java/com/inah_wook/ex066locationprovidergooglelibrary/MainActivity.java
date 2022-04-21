package com.inah_wook.ex066locationprovidergooglelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Looper;
import android.os.PatternMatcher;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {


    // Fused API : 구글 지도에 사용되고 있는 위치저오 제공자 최적화 라이브러리
    // gps냐 network 냐 선택하기 귀찮잖아?

    // Google Fused API 라이브러리 : play-services ( 구글이 사용하는 모든 라이브러리가 다 들어잇음 유투브 결제 등등)
    //                             play-services-map 이거만 쓰자 ~

    // gradle의 모듈로 ㄱㄱ

    // 위치정보 제공자 객체 : 얘가 알아서 최적의 제공자 선택함 - LocationManager 역할을 수행
    FusedLocationProviderClient providerClient;

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        int checkResult = checkSelfPermission(permissions[0]);
                if(checkResult == PackageManager.PERMISSION_DENIED){

                }
                tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(view -> {

            //위치정보 제공자 객체 얻어오기
            providerClient = LocationServices.getFusedLocationProviderClient(this);

            //위치정보 요청 객체 생성 및 관련 설정
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.QUALITY_HIGH_ACCURACY); // 높은
            locationRequest.setInterval(5000); // 5초 간격으로 갱신6

            //내 위치 실시간 갱신 요청
            providerClient.removeLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper(),)

        });


    }//on create


    //내 위치 실시간 갱신 요청
    //        providerClient.removeLocationUpdates(locationRequest,)
    // 요기에서 위치 갱신 정보를 듣는 객체 만들기 `
    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);

            // 파라미처로 전달된 위치정보결과 객체에세 위치정보 받기
            Location location = locationResult.getLastLocation();
            double let = location.getLatitude();
            double lng = location.getLongitude();

            tv.setText(let + " , "+ lng);



        }
    };

    // 액티비티가 화면에 안보이기 시작할 때. .  라이프사ㅣ이클 메소드
    protected  void onPause() {

        super.onPause();

        if(providerClient != null){
            providerClient.removeLocationUpdates(locationCallback);
        }

    }




                //리퀘스트 퍼미션으로 보여진 다이얼로그에서 허가/ 거부 를 선택 했을 땨
    //결과를 알려주기 위한 자동으로 발동하는 콜백 메소그
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 10){

            if(grantResults[0] == PackM)

        }

    }
}