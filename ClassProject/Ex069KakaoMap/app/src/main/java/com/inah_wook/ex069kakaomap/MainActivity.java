package com.inah_wook.ex069kakaomap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kakao.util.maps.helper.Utility;

import net.daum.android.map.MapView;
import net.daum.android.map.MapViewEventListener;
import net.daum.mf.map.api.MapPoint;

public class MainActivity extends AppCompatActivity {


    //카카오 개발자 사이트 가이트 참고 ㄱ

    // 1) 카카오 지도 SDK 적용하기
    //  1.1) .zip 다운받고 압축풀어 해당위치에 복붙
    //   1.2) 이 프로젝트에 위 ㅏ라이브러리 적용시키기


    MapView mapView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // 디버그용 키 해시값 얻어오기
        String keyHash = Utility.getKeyHash(this);

        Log.i("KEY",keyHash);


        // 맵 뷰 생성하기
       mapView = new MapView(this);

        // xml의 RelativeLayout을 가져오기
      //  RelativeLayout mapViewContainer = (RelativeLayout) findViewById(R.id.container_mapview);
       // mapViewContainer.addView(mapView);
        //근데 이거 실디바이스여야지 실행 됨






    }
}