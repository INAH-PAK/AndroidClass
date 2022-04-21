package com.inah_wook.ex065location;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;

public class MainActivity extends AppCompatActivity {

    //Location Based System 위치 기반 서비스

    LocationManager locationManager;

    TextView tv,tv2,tv3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //내 위치정보를 가져오려면 반드시 동적 퍼미션부터 받아야 함
        //manifest에 정적 퍼미션 추가
        //내 위치정보 퍼미션을 받았는지
        int checkResult = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

        //Location Manager를 이용해 운영체제로부터 위치 정보를 가져온다.
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); //다운캐스팅

        //위치정보를 제공하는 장치는 여러개
        //이런 것들을 위치정보 제공자(Location Provider)라고 함
        //1. GPS(인공위성) : 가장 높은 정확도(0.5m 오차), 무료 but 실내에 있거나 기상이 안좋으면 부정확. 배터리 소모 비교적 많음
        //2. Network(WIFI, LTE, 5G) : 중간 정확도, LTE/5G 같은 경우 유료, 어디서나 가능. 배터리 소모량 중간 정도
        //과거에는 기지국 위치로 대체. 지금은 기지국 간 삼각측량 이용. 와이파이는?
        //3. passive : 다른 앱의 마지막 위치정보를 통해 내 위치를 얻어오는 방식. 정확도 낮을 수밖에. 사용 빈도 거의 없음.
        //4. fused : New! 구글 지도 앱의 위치정보탐색 라이브러리를 이용하는 방법(다음 예제에서 소개). 기술 자체는 오래 되었다.
        //개발할 때는 사용자가 실외로 나가면 GPS를 쓰게 하고 실내로 들어오거나 GPS 사용이 어려우면 네트워크를 사용하는 방식으로 만드는 게 제일 좋다.
        //하지만 사용자가 실내로 들어가고 실외로 나오는 걸 어떻게 아나? 상당히 어려운 작업이다.
        //이를 쉽게 만든 것이 구글의 fused 라이브러리

        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(view -> {
            Location location = null;

            if(locationManager.isProviderEnabled("gps")) {   //gps 사용 가능하면 이걸로 하고
                location = locationManager.getLastKnownLocation("gps");
            }else if(locationManager.isProviderEnabled("network")){  // 안되면 네트워크로 잡아라 ~
                location = locationManager.getLastKnownLocation("network");
            }

            if(location == null){
                tv.setText(" gps랑 네트워크가 안잡혀서 내 위치 못찾게떠");
            }else{
                // 위도 , 경도 -> float
                double latitude = location.getLatitude(); //위도
                double longitude = location.getLongitude();//경도

                tv.setText( latitude + " , " + longitude);
                // 이러고 실행하면 한 번도 앱을 실행 한 적 없으면 위치정보 못받아옴
                //이러면
                // 버튼을 누를 때 마다 업뎃되기 때문에,
                //움직이고 눌러야 내위치 뜨고 이런단말야?
                        // 너무 개극혐
                // 매 시간 언제나 가장 최근의 위치를 알려주는 내위치를 계속 쳐다보고있는 리스너를 등록하면 됨


            }





        });

tv2 = findViewById(R.id.tv2);
findViewById(R.id.btn2).setOnClickListener(view ->{
    // 내 위치를 실시간으로 얻어오기
    // TODO 이거 삼항연산자로 정리하기
    // 이걸 실행하면 상태표시줄에 위치 탐색중이라는 아이콘이 보임 ~

    if(locationManager.isProviderEnabled("gps")){
                                                //몰로 줄까?, 얼마마다 갱신? 최소 거리 단위, 리스너 하나 만들어서 쓰기 !
        locationManager.requestLocationUpdates("gps",5000,2, locationListener);

    }else if(locationManager.isProviderEnabled("network")){
        locationManager.requestLocationUpdates("network",5000,2, locationListener);
    }
} );

findViewById(R.id.btn3).setOnClickListener(view ->{
    // 내 취피 자동 ㅅ갱신 제거
    locationManager.removeUpdates(locationListener);
} );






    } //on

    //맴버변수 위치

    // 특정 반경 안에 들어갔는가?
    boolean wasEnter = false;









    //실시간 위치 정보 갱신을 듣는 리스너 객체 생성 ( btn2 랑 btn3 에 쓸 애)
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    tv2.setText( latitude + ": 위도 ,  경도 : " + longitude);

            // 자 여기선 특정 위치에 갔을때 맛집이다 ! 하고 알려주는걸 만들어보자
            //양재 프릳츠로 하자
            // 양재 프릳츠위 위치 정보 : 위도 37.4853311,  경도   127.0299945

            // 내위치
            float[] result = new float[3];
//            Location.distanceBetween(latitude, longitude ,37.4853311,127.0299945 )

                    //result [0] 에 두괖 사이으 ㅣ거이
            if(result[0]<50){

                if(wasEnter == false){
                  //  new AlertDialog.Builder((MainActivity.this).setm)

                }else{
                    wasEnter = false;
                }



            }





        }










    };







    // requestPermission() 로 보여준 다이얼로그에서 허가 / 거부 를 선택 했을 땨
    // 선택 결과를 알려주기 위해 발동하는 콜백 메소드


   //1. requestCode 아까 우리가 식별자 10번으로 쓴게 옴
    //2. permissions 아까 요청한 목록
    //3. 요청값의 결과 갯수

    //requestPermission()으로 보여준 다이얼로그에서 허가/거부를 선택했을 때 선택결과를 알려주기 위해 발동하는 콜백 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //위에서 퍼미션 다이얼로그 띄울 때 썼던 requestCode가 들어옴
        if(requestCode == 777){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){ //허가되었을 때
                Toast.makeText(this, "위치정보 제공에 동의하셨습니다.", Toast.LENGTH_SHORT).show();
            } else { //거부되었을 때
                Toast.makeText(this, "위치정보 제공을 거부하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}