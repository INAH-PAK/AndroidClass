package com.inah_wook.ex068googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    //Google Map API  적용하기
    // 1.
    // https://developers.google.com/maps/documentation/android-sdk/config
    // 의 가이드 문서에 따른 프로젝트 라이브러리 추가 및 생성

    // 2.
    // 구글 맵 플랫폼에 프로젝트를 등록하고 추가
    //https://developers.google.com/maps/documentation/android-sdk/get-api-key





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 먼저 xml에 추가한 fragment찾아와서 참조,

        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapfragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        // 이제 지도를 비동기 방식으로 가져올거임,
        // == 별도 스레드를 사용한다.
        // 즉 비동기 방식으로 구글 서버에서 맵의 데이터를 읽어오도록
        mapfragment.getMapAsync(new OnMapReadyCallback() { // 맵 준비가 끝났을 때.
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                // 아무 코드를 안써도 기본 지도가 보여야 함.

            //가이드를 참고하여 지도에 관련되 여러 설정들 작업,

                // 일단 지도의 특정 좌표로 카메라를 이동 후, 줌-인 까지 하는걸 구현해보자. . .

                LatLng seoul = new LatLng(37.5609,127.0347); // 왕십리 좌표
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul,16));// zoom 은 1~25 1이 대한민국

                //마커 추가
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title("미래교육원IT캠퍼스");
                markerOptions.snippet("왕십리 역에 있는 미래 IT캠퍼스");
                markerOptions.position(seoul);

                googleMap.addMarker(markerOptions); // 마커 클릭하면 정보뜸
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_shop ));//백터 이미지 불가. .png 난 jpg aks rksmd
            markerOptions.anchor(0.5f,1.0f);// x축 y축 배치



            }
        });






    }
}