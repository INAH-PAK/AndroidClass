package com.inah_wook.ex067locationgeocoding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

// 구글 서버에 있는 좌표들
// 이 앱이 서버

// Geocoding : 좌표-> 주소, 주소-> 좌표
// 1.manifests 열기 ㄱㄱ 퍼미션 줘야 함



public class MainActivity extends AppCompatActivity {


    EditText etAddress;
    EditText etLat, etLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAddress = findViewById(R.id.et_address);
        etLat = findViewById(R.id.et_let);
        etLng = findViewById(R.id.et_lng);

        findViewById(R.id.btn).setOnClickListener(view -> aaa()
            // 주소 -> 좌표 ( Geocoding)
        );
        findViewById(R.id.btn2).setOnClickListener(view -> bbb()
            // 좌표 -> 주소 ( Reverce Geocoding)
        );

        findViewById(R.id.btn3).setOnClickListener(view -> {});







    }

    void aaa() {
        // 주소 -> 좌표 ( Geocoding)

        String addr = etAddress.getText().toString();

        Geocoder geocoder = new Geocoder(this, Locale.KOREA);

        try {
            List<Address> addressList = geocoder.getFromLocationName(addr, 3);        //maxResult : 건물 하나에 위도, 경도 등 좌표가 많잖아? 그거 얼마나 주냐
            // 최대 3개를 리스트로 줌..

            /*  이걸 반복문으로 ㄱ
            Address address = addressList.get(0);
            address.getLatitude();
            address.getLongitude();

            Address address = addressList.get(1);
            address.getLatitude();
            address.getLongitude();
*/

//            for (int i = 0 ; i < addressList.size();i++){
//
//            } 이거말고 포이치
            StringBuffer buffer = new StringBuffer();
            for (Address address : addressList) {
                double let = address.getLatitude(); //위도
                double lnt = address.getLongitude(); //경도

                buffer.append(let + " , " + lnt + "\n");
            }
            latitude = addressList.get(0).getLatitude();
            new AlertDialog.Builder(this).setMessage(buffer.toString()).create().show();

            // 구글지도 앱을 실행하기
            // 구글지도 앱의 화면 (Activity) dmf tlfgodgksms rjtdla.
            // 단 ! 다른 앱을 실행할때는 반드시 묵시젓으로 인텐트로 실행해야 함,
            Intent intent = new Intent();
            intent. setAction(Intent.ACTION_VIEW);

            Uri uri = Uri.parse("geo"); / ㅏㅏㅣ둘ㄷㅈㅈ

        }catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        for (Address address : addressList) {
            double let = address.getLatitude(); //위도
            double lnt = address.getLongitude(); //경도

            buffer.append(let + " , " + lnt + "\n");

        }

        void bbb () {
            // 과표 -> 주소로

            double let = Double.parseDouble(etLat.getText().toString());
            double lnt = Double.parseDouble(etLat.getText().toString());

            Geocoder geocoder = new Geocoder(this, Locale.KOREA);

            try {
                List<Address> addressList = geocoder.getFromLocation(let, lnt, 3);
            } catch (IOException e) {
                e.printStackTrace();
                StringBuffer buffer1 = new StringBuffer();

                buffer1.append(add.Con + " , " + lnt + "\n");
                buffer1.append(add.Con + " , " + lnt + "\n");
                buffer1.append(add.Con + " , " + lnt + "\n");
                buffer1.append(add.Con + " , " + lnt + "\n");
                buffer1.append(add.Con + " , " + lnt + "\n");
                buffer1.append(add.Con + " , " + lnt + "\n");


            }

            StringBuffer buffer = new StringBuffer();
            for (Address address : addressList) {
                double let = address.getLatitude(); //위도
                double lnt = address.getLongitude(); //경도


                //


            }
        }

    }