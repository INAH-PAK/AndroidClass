package com.wookie_soft.ex102webapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    val wv:WebView by lazy { findViewById(R.id.wv) }
    @SuppressLint("SetJavaScriptEnabled") // 이거 안스의 추천
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 하이브리드 앱이란? -> 웹 앱 + 핸드폰의 기능을 사용할 수 있는 것.

        // 기본적으로 보안문제 때문에, WebView는 JavaScript code 실행을 허용하지 않음.
        // -> 웹 뷰 설정객체를 통해 JS 허용하도록 변경 !
        wv.settings.javaScriptEnabled = true

        // 웹 뷰 사용할 때 필수 설정값 2가지
        // 1. 예전에는 새로운 웹 문서를 열 때, 이 웹 뷰를 쓰지 않고,
        // 디바이스의 웹브라우저(크롬, 사파리 . . .) 의 앱이 실행되ㅓ면서 열림.  ==>  내 앱 -> 크롭앱 -> 화면
        // 이걸 항상 막았어야 햇다 ~ 지금은 필요 없당~ 근데 해보기. 우린 지금 내 앱에서 만들어야 그게 찐이잖어?
        wv.webViewClient = WebViewClient() // 새로운 웹 뷰 클라이언트를 만들어줌. 이걸 오버라이드 해서 안에 열어보면 개많음 메소드 .
        //지금은 안써도 됨.

        // 2. alert() 아니 새로운 다이얼로그같은 팝업이 보이도록
        wv.webChromeClient = WebChromeClient()


        // 웹 뷰가 보여줄 웹 문서만 연결하면 됨.
        // 호스팅 서버의 단점 : 네트워크가 무조건 필요.
        // 오프라인 상태일 때 화면이 보이고 싶다면 -> html 문서가 이 프로젝트 안에 위치해야 함.
        // 근데, 안드로이드는 ..
        // 아니, res 폴더는 html을 저장하는 용도의 창고가 아님, 그래서 다른 창고를 사용.
        // 안드로이드는 크게 두가지 창고 있음 - R, Asset
        // 그래서 아예  :  app package -> New Folder -> change Directory 하지 말고 생성.
        // asset 폴더의 실제 이름 == android_asset

        // https://www.w3schools.com/w3css/w3css_templates.asp
        // w3s 에서 만든 라이브러리 : web templates
        wv.loadUrl("file:///android_asset/index.html")

        // 당연히 별도 서버 호스트의 html 문서도 볼 수 있음.
        wv.loadUrl("http://kkpk.dothome.co.kr")



    }

    override fun onBackPressed() {
        //웹 페이지의 뒤로가기 버튼과 내 폰에서 뒤로가기 맞추기
        if(wv.canGoBack()) wv.goBack()
        else super.onBackPressed()
    }


}

