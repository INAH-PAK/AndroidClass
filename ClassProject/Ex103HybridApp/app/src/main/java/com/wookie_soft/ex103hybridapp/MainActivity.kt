package com.wookie_soft.ex103hybridapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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




    }

    override fun onBackPressed() {
        if(wv.can)
        super.onBackPressed()
    }


}