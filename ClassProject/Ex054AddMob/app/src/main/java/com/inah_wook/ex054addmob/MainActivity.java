package com.inah_wook.ex054addmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;


//   돈 버는 법  1. 과금 (인 앱 결제 - 아이템 사기, 특정 메뉴 접근 )
//              2. 광고 - 광고 플랫폼 회사에서 광고 결제만 하고, 우린 플랫폼의 클래스를 가져다가 써야 함. AddView class 가져다 쓰면 됨
// 카카오 에드 등 광고 플랫폼 회사 많은데 젤 큰건 구글의 admob.

//  모바일 광고에 대한 플랫폼 회사 :  AdMob [ Google ]
// 싸이트 : https://admob.google.com/home/
// 설명서 : https://developers.google.com/admob

// 1) 구글 계정으로 가입 및 로그인
// 2) 앱 들옥 광고단위 ID 제작



public class MainActivity extends AppCompatActivity {





        //AdMob SDK :  외부 라이브러리를 이 프로젝트에 적용 . (다운하고 설치후 연결한다.) -> AdView가 써지는 순간까지 해야한다.
            // 이건 가이드문서를 참고하면서 한다.
    //https://developers.google.com/admob/android/quick-start#import_the_mobile_ads_sdk
        // Gradle 빌드 프로그램이 라이브러리 적용 작업을 손쉽게 해줌.
        // Android BumbleBee 버전의 변경사항.
        // -> 프로젝트 수준의 빌드.grable 에 하던 작업을 settings.gradle 에서 함.


        AdView adView;  // 이게 써지면 라이브러리 잘 적용된거임.


            // [1] 배너광고 뷰 참조변수



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 모바일 광고에 대한 SDK 초기화
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
              //광고 초기화가 완료되는 실행되는 영역.
            }
        });  //기본준비 끝. 외부 라이브러리 등록, 아이디등록, 광고초기화 이게 끝 ~


        //네이티브 광고 : 광고 커스텀. 근데 이거 설명서도 이상하고 아직은 ㄴㄴ
        // 보상형 : 광고보면 코인 주는거.


        // [1]배너광고뷰 참조하기
        adView = findViewById(R.id.adview);

        // [1]광고 로드하기
        AdRequest adRequest = new AdRequest.Builder().build(); // 현재 빌더가 빌드 한 걸 가져온게 이 광고요청 객체!
        adView.loadAd(adRequest); //끝!


        //[2] 전면광고 - 배너광고 사람들이 개극혐해서 잘 안씀.
        // 얘는 다이얼로그처럼 별도로 뜸.
        //버튼 클릭시 전면광고 보여주기

        findViewById(R.id.btn).setOnClickListener(view -> {
        }    );



            //[3] 보상형 광고 보여주기 : 람다식의 실행문이 1줄이면 {} 생략 가능
        findViewById(R.id.btn2).setOnClickListener(v -> showRewerdedAd());



    }// on Create method.


    //맴버변수 위치
    //   [2] 전면광고 참조변수
    InterstitialAd interstitialAd;

    //[2] 전면광고 보여주는 기능 메소드
    void showInterstitualAd(){
        // 전면광고 보여주는 아이디 샘플 :  https://developers.google.com/admob/android/banner#always_test_with_test_ads
        // ca-app-pub-3940256099942544/6300978111

        AdRequest adRequest = new AdRequest.Builder().build();

        //[2] 전면광고 로드하기
        InterstitialAd.load(this, "ca-app-pub-1927437183452279/6349460359", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError); //광고 로드 실패시 자동 발동 메소드
                Toast.makeText(MainActivity.this, " interstitialAd fail to load ! ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd); //광고  로드 성공시 자동 발동 메소드
                //성공했을 때 파라미터 광고 객체가 전달 됨

                //전면광고 객체를 맴버변수애 대입해주기.
                //이렇게 하면 다른버튼 눌렀을 때도 광고보여주기 작업이 가능함.
                //우린 지금 버튼 누르면 그 때 광고 로딩하잖아?
                //근데 설명서에서는 시작할 때 이미 로딩 끝내고 맴버변수에 넣어두기만 함,
                //설명서의 쇼는
                MainActivity.this.interstitialAd = interstitialAd;

                interstitialAd.show(MainActivity.this); // 전면광고 보이기
            }
        });






    } //배너광고 함수


    //[3] 보상형 광고 참조변수 (전면이랑 똑같음)
    RewardedAd rewardedAd;
    //[3] 보상형 광고 보여주는 기능 메소드

    public void showRewerdedAd() {
        //sample 유닛 아이디 필요 : https://developers.google.com/admob/android/rewarded#always_test_with_test_ads
        //ca-app-pub-3940256099942544/5224354917
        //광고 요청 객체 생성
        AdRequest adRequest = new AdRequest.Builder().build();
        //보상형 광고 로딩하기
        RewardedAd.load(this, "ca-app-pub-1927437183452279/3864620010", adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Toast.makeText(MainActivity.this, "fail to load ! ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                super.onAdLoaded(rewardedAd);
                // 맴버변수에 보상형 광고 객체 참조시키기
                MainActivity.this.rewardedAd = rewardedAd;

                //보상형 광고 보기 : 사용자가 일정 시간 이상 광고 동영상을 보고, 이로 인해 보상을 얻었을 때 리스너 처리 해야 함. ( 전면광고랑 딱 하나 다른점 ~)
                rewardedAd.show(MainActivity.this, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        // 파라미터로 온 rewardItem !
                        // 이는 기준시간 이상 광고를 시청하면 주어지는 보상 아이템 !
                        //이 값은 개발자가 광고단위 ID를 만들 때, 설정한 값.
                        // type : 'coins' , amount = '10'
                         String type = rewardItem.getType();
                        int amount = rewardItem.getAmount();
                        Toast.makeText(MainActivity.this, type + " : "+ amount, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }

    //[4] 네이티브 광고 { 원하는 모양으로 광고모양 레이아웃 설계 }
    // 가이드 문서가 온전하지 않음. 지금은 수업 보류. 따로 질문하면 교수님이 해준당





}