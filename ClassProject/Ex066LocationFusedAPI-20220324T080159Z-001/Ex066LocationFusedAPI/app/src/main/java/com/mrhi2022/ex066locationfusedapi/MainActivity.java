package com.mrhi2022.ex066locationfusedapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    //Fused API : Google 지도에 사용되고 있는 위치정보 제공자 최적화 라이브러리

    //Google Fused API 라이브러리를 추가 : play-services-location

    FusedLocationProviderClient providerClient;//위치정보제공자 객체(알아서 최적의 제공자 선택함)- LocationManager 역할.. 수행

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //동적퍼미션
        String[] permissions= new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        int checkResult= checkSelfPermission(permissions[0]);
        if(checkResult == PackageManager.PERMISSION_DENIED){
            requestPermissions(permissions, 10);
        }

        tv= findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(v->{

            //위치정보 제공자 객체얻어오기
            providerClient= LocationServices.getFusedLocationProviderClient(this);

            //위치 정보 요청 객체 생성 및 설정
            LocationRequest locationRequest= LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//높은 정확도 우선시..[gps]
            locationRequest.setInterval(5000);//5000ms[5초]간격으로 갱신

            //내 위치 실시간 갱신 요청
            providerClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

        });
    }

    //위치갱신 정보를 듣는 콜백객체
    LocationCallback locationCallback= new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);

            //파라미터로 전달된 위치정보결과 객체에게 위치정보를 얻어오기
            Location location= locationResult.getLastLocation();
            double lat= location.getLatitude();
            double lng= location.getLongitude();

            tv.setText(lat+" , " + lng);
        }
    };

    //액티비티가 화면에 안보이기 시작할때..라이프사이클 메소드
    @Override
    protected void onPause() {
        super.onPause();

        if(providerClient!=null){
            providerClient.removeLocationUpdates(locationCallback);
        }
    }

    //requestPermissions()으로 보여진 다이얼로그에서 허가/거부 를 선택했을 때
    //결과를 알려주기 위해 자동으로 발동하는 콜백메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==10){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "위치정보 사용 가능", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "위치정보 사용 불가", Toast.LENGTH_SHORT).show();
            }
        }

    }
}