package com.wookie_soft.findyourplace.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wookie_soft.findyourplace.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    val binding:ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 둘러보기 버튼으로 로그인 없이 Main 화면 실행
        binding.tvGo.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // 2. 회원가입 버튼 클릭
        binding.tvSignUp.setOnClickListener {
            // TODO 회원가입 화면 작업 시작하기.
        }
        //3. ㅇ메일 로그인
        binding.layoutEmailLogin.setOnClickListener {

        }

        //4. 간편로그인 버튼들
        binding.btnKakao.setOnClickListener { clickLoginKakao() }
        binding.btnGoogle.setOnClickListener { clickLoginGoogle() }
        binding.btnNaver.setOnClickListener { clickLoginNaver() }



    }// onCreate

    private fun clickLoginKakao(){

    }
    private fun clickLoginGoogle(){

    }
    private fun clickLoginNaver(){

    }




}//Main Class