package com.inah_wook.ex050threadnetworkimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb; //이렇게 하면 동그라미밖에 안됨. 다음 예제 ㄱㄱ


    Button btn; //이미지를 인터넷에서 불러오는 정석방법
    ImageView iv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);
        btn = findViewById(R.id.btn);
        pb = findViewById(R.id.pb);

        btn.setOnClickListener( view -> {

            // 메인 스레드는 네트워크 작업 불가 !
            //별도의 스레드를 생성해서 네트워크 작업을 수행하도록 함.

            //사용자에게 " 이 앱이 당신의 카메라를 사용합니다 " 등 허가받아야 하는 상황에서
            //네트워크 작업할 때, 반드시 ! 이 앱에서 인터넷을 사용한다는 permition을 받아야 함 !
            //퍼미션은 AndroidManifest.xml 에서 받아야 함 !!!
            new Thread(){ //익명 클래스 생성해서 인용!! 많이 쓰는 기술 !
                @Override
                public void run() {

                    //프로그래스 바 부터 보이기
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pb.setVisibility(view.VISIBLE);
                        }
                    });
                    // super.run(); 지워도 됨. 아무것도 안쓰여있음.

                    //이제 오래걸리는 네트워크 작업을 해보자 !
                    // 데이터를 가져오는 작업을 위해 필요한 개념


                    // 1. 네트워크상에 있는 이미지의 주소(url) 을 가져오기.
                    //  근데 https 는 보안이 낀거라서 걍 주소 쓸 수 있는데,
                    //  걍  http 쓰고 싶으면 : AndroidManifest.xml 의 application ㄱㄱ
                    //  걍 무조건 !! 인터넷 쓰려면 퍼미션 주고  android:usesCleartextTraffic="true" 속성 ㄱㄱ

                    String imgUrl = "https://cdn.pixabay.com/photo/2019/08/19/07/45/dog-4415649_960_720.jpg";

                    //이제 서버 이미지주소까지 연결되는 무지개로드 만들기 == Stream
                    // 해임달 (URL) 에게 Stream 열도록 요청

                    try {
                        URL url = new URL(imgUrl );
                        InputStream is = url.openStream();  // 받아오는 길을 만듬

                        // 이제 스트림을 통해 이미지파일을 읽어와서 이미지뷰에 설정
                        // 네트워크의 파일 이미지를 가져와서 비트맵 객체를 만들어서 넣어줘야 함.
                        //네트워크 사진을 바로 가져와서 findByid 불가해성..ㅜ
                        // 안드로이드에서 이미지파일을 가지는 Bitmap  클래스 객체 생성

                        //decode : 해독하다.
                        //우리가 보낸 파일 jpg 이고 우리는 .java라서 ! 해독해야 함!
                        // Bitmap 객체를 만드는 공장 BitmapFactory 이 있음.
                        final Bitmap bm = BitmapFactory.decodeStream(is); //이 비트맵 공장에게 무지개로드 알려주고 받아오라고 함.
                        // iv.setImageBitmap(bm); 이거 여기서 하면 에러 !
                        //별도 스레드는 UI 작업이 변경불가하므로, UI 변경작업은 UI Thread 인  main만 가능
                        // UI Thread 에서 실행되도록 코드 작성

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 이 영역 안에서는 UI 변경 작업이 가능
                                iv.setImageBitmap(bm);  //원래 익명 클래스 안에서는 지역변수 못 씀. final과 맴버변수만 사용가능

                                pb.setVisibility(view.GONE);
                            }
                        });

                        is.close();


                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();


        }); // btn1

        findViewById(R.id.btn2).setOnClickListener( view -> {

            //이미지 로드 외부 라이브러리를 이용해서 이미지 설정해보기 !
            // 여러 종류 중 , 가장 대표적인 두가지 !
            //1. Picasso - 개인
            //2. Glide - Google 소유
            //쓰는 방법은 동일함. 나중에 업뎃도 편하게 글라이드 ㄱㄱ

            String imgUrl = "https://media.istockphoto.com/photos/big-cat-on-the-street-picture-id513425034?s=612x612";
           // Glide.with(this).load(imgUrl).into(iv); // 운영체제 주고. 주소 주고, 붙일 곳 주면 끝 !
            //Glide.with(this).load(R.drawable.flag_germany).into(iv);//해상도에 맞춰서 보여줌 ~
            Glide.with(this).load(R.drawable.moana).into(iv);
        });




    }
}//main