package com.wookie_soft.ex099kakaologin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.wookie_soft.ex099kakaologin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate( layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 카카오 developers - 전체 설명
        //   https://developers.kakao.com/docs/latest/ko/getting-started/sdk-android#apply-sdk
        // 먼저 카카오의 키해시값 얻어오기
        //   https://developers.kakao.com/docs/latest/ko/getting-started/sdk-android#add-key-hash

        //   1  .   카카오 키 해시 얻어오기 => Utility 클래스를 쓰려면 , 먼저 Kakao SDK 추가해야 함 !

        var keyHash = Utility.getKeyHash(this)
        Log.i("키해시", keyHash)

        //   2  .    앱 키에 키해시 등록 후, 매니페스트 -> 퍼미션 주고 !

        // 근데 카카오 SDK 는 먼저 네이티브 앱 키로 초기화해야 함
        // https://developers.kakao.com/docs/latest/ko/getting-started/sdk-android#init
        // 앱이 가장 먼저 시작할 때, 매니패스트부터 실행되잖아?
        // 그래서 요기 어플리케이션 시작할 때 네이티브ㅡ 앱키로 초기화 해줘야 하니까 어플리케이션을 상속받은
        //    3  .
        // StartApplication.class 만들고 매니페스트의 application에 이름설정.


            // 자 , 내 앱을 외국인이 쓸 수도 있고, 한국인이 쓸 수도 있잖아?
        // 페러럴 리소싱
        // drawable 에 카카오에서 받은 영어 그림들 다운받아 넣기
        // 내 폰에는 언어설정이 한국어로 되어있음.

        // R 장부는 drawable , drawable-24 이 폴더를 걍 drawable 하나로 보고 -24는 하위느낌으로 봄
        // 그래서 영어 그림을 그냥 drawable

        // 그럼 이제 내 프로젝트의 res -> drawable-ko 폴더를 만들어서 여기에 한국어 그림 넣어두기
        // 그 다음 매니페스트에 SDK Url 접속 엑티비티 만들고

        binding.iv.setOnClickListener{ clickLogin()}

        //로그아웃 구현 총 두가지를 구현할 수 있음.
        // 1. 연결 끊기   ->  첨에 동의 받아온 거 있잖아 ! 그런거
        // 2. 로그아웃

        binding.btnUnlink.setOnClickListener { clickUnlink() }
        binding.btnLogout.setOnClickListener { clickLogout() }

    }
private fun clickUnlink(){
    //
    UserApiClient.instance.unlink {
        if(it != null){
            Toast.makeText(this, "연결끊기 실패", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "연결끊기 성공", Toast.LENGTH_SHORT).show()
            binding.tvNickname.text = "닉네임"
            binding.tvEmail.text = "이메일"
            Glide.with(this).load(R.mipmap.ic_launcher_round).into(binding.civ)
        }
    }
}

    private fun clickLogout(){
    UserApiClient.instance.logout { error ->
        if(error != null) Toast.makeText(this, "로그아웃 실패", Toast.LENGTH_SHORT).show()
        else{
            Toast.makeText(this, "로그아웃 실패", Toast.LENGTH_SHORT).show()
            binding.tvNickname.text = "닉네임"
            binding.tvEmail.text = "이메일"
            Glide.with(this).load(R.mipmap.ic_launcher_round).into(binding.civ)
        }
    }
}
    private fun clickLogin(){

        // 함수형 언어 꼭! ! ! 공부하기
        //로그인 결과 여부 콜백함수 ( 익명 함수 == 함수의 이름 없이 변수인 애 )
        val callback1 = fun(toekn:OAuthToken?, error:Throwable?){
        }

        // 위의 익명 함수를 다 쓰기 너무 귀찮으니까 익명함수로 만들자.
        // 익명 함수 --> 람다 표현식
        val callback2:(OAuthToken?,Throwable? )->Unit = {
            token, error ->
        }


       //      https://developers.kakao.com/console/app/739315/product/login
        //          5       .  꼭 카카오 프로젝트 페이지에서 카카로 로그인 활성화 시키기 !!!!!
        // 카카오 로그인 동의항목도 설정하기 ! https://developers.kakao.com/console/app/739315/product/login/scope

        // 카카오 로그인이 가능한지 물어보기
        if( UserApiClient.instance.isKakaoTalkLoginAvailable(this) ){
            // 카톡 로그인(카카오의 권장)   --> loginWithKakaoTalk 의 두 번째 파라미터는 맨 위에 만들었음.
                // callback2 를 쓴다면 이렇게 쓸 수 있고
                // callback1 을 쓰면 callback = callback1 이렇게 써야 함.

            UserApiClient.instance.loginWithKakaoTalk(this, callback = { token, error ->
                if(error != null){ //에러가 있으면 망한고잖아? ㅋㅋ 에러 띄우자. 근데 카카오 디벨로퍼 사이트에선 여기서 카카오 계정 로그인으로 하라고 함. 근데 귀찮으니까 일단 이걸로.
                    Toast.makeText(this, "카카오 로그인 실패 : ${error}", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "카카오 로그인 성공 : ${error}", Toast.LENGTH_SHORT).show()
                }
                // 어차피 카카오 로그인은 콜백메소드 무조건 실행하니, 여기서 정보를 가져오자.
                loadUserInfo()
            })

        }else{
            // 카카오 계정 로그인
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback1)
        }
    }

    private fun loadUserInfo(){
        UserApiClient.instance.me { user, error ->
            if(error != null)   Toast.makeText(this, "사용자 정보 요청 실패 ", Toast.LENGTH_SHORT).show()
            else if ( user != null ){
                // 어떤 정보를 받아올 지 ~~
                val memberId:Long? = user.id    // 회원번호 -> 회원식별자로 사용 가능 . . . 근데 지금 우린 별로 필료하지 않음
                val nickName = user.kakaoAccount?.profile?.nickname
                val email = user.kakaoAccount?.email
                val profileImag = user.kakaoAccount?.profile?.profileImageUrl

                binding.tvNickname.text = nickName
                binding.tvEmail.text = email
                Glide.with(this).load(profileImag).into(binding.civ)
            }
        }
    }







}