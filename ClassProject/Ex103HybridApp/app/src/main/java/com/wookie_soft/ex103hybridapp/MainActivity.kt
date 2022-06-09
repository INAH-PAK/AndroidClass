package com.wookie_soft.ex103hybridapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.wookie_soft.ex103hybridapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 이 4가지가 거의 필수 속성 설정들. . .
        binding.wv.settings.javaScriptEnabled = true // js 사용 허용
        binding.wv.settings.allowFileAccess = true // 온라인 에서는 Ajax 사용 가능 하지만, 오프라인에서는 안되는데 이걸 되게 설정.
        binding.wv.webViewClient = WebViewClient()
        binding.wv.webChromeClient = WebChromeClient()

        // 웹 뷰가 보여줄 뷰 설정
        binding.wv.loadUrl("file:///android_asset/index.html")

        //1) Native Android 쪽에서 WebView 의 UI 제어
        binding.btn.setOnClickListener {
            // WebView 에서 보여주느 ㄴindex.html 안에 있는 특정함수를 호출해서 웹 UI 제어
            // Navive 에서 직접 HTML 의 dom 요소는 제어 불가

            var msg:String = binding.et.text.toString()
            binding.wv.loadUrl("javascript:setMessage('$msg')")




        }// 리스너


        // 2. WebView 에서 Native Android 를 제어하기 위해 중계인 역할을
        // 수해아는 객체를 생성하며, WebView 에서 이 객체를 지칭 할 별명 설정
        binding.wv.addJavascriptInterface(webViewConnertor(),"Droid")



        }//onCreate

        //1. webView 의 javascript와 통신을 담당 할 중계인 객체 클래스 정의
        inner class webViewConnertor { //이너클래스 안에서 부모 꺼를 맘대로 사용가능.

            // 클래스 자체는 특별한 기능 없으니,
            // javascript 에서 호출할 수 있는 메소드는
            // 반드시 @javascriptInterface 라는 어노테이션을 지정해야 함.
            @JavascriptInterface
            fun setTextView(msg:String){
                binding.tv.text = "웹 뷰로부터 받은 메세지 : $msg"
            }

            @JavascriptInterface
            fun openGalleryApp(){
                val intent = Intent(Intent.ACTION_PICK)
                intent.type="image/*"
                startActivity(intent)
            }



    }






}