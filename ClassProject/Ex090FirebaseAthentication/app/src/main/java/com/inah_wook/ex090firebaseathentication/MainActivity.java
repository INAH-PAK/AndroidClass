package com.inah_wook.ex090firebaseathentication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.inah_wook.ex090firebaseathentication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
// firebase의 로그인 기능
    // 1.  이메일과 비밀번호 기반의 인증 로그인 시스템 -  이 기능 사용시 이메일 인증 확인을 통한 사용자 인증
    // 2. ID 공급업체 사용 - Google, Apple, Twiter, Github . . .  계정으로 로그인 지원

    // 먼저 firebase와 연동동

    ActivityMainBinding binding;
    //FirebaseAuth 인증관리 객체   참조변수부터 만들어놓기

    FirebaseAuth firebaseAuth;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       firebaseAuth = FirebaseAuth.getInstance();// 인증관리 객체 소환
        binding.btnSignup.setOnClickListener(v->clickSignUp());
        binding.btnSignup.setOnClickListener(v->clickSignIn());
        binding.btnGoogle.setOnClickListener(v->clickGoogle());
       binding.btnLogout.setOnClickListener(v->clickLogout());



    }

    private void clickLogout() {
       firebaseAuth.signOut();
        Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
    }

    private void clickGoogle() {
       // google 계정을 이용한 구글 로그인
        // 구글 로그인은 자신만의 고유한 화면이 있잖오?
        // 구글 로그인 화면 ( 액티비티 ) 를 실행시키는 인텐트를 통한 스타트 액티비티
        // 단 구글계정 로그인 SDK를 별도로 추가해야 함.
        //모듈 추가  ㄱㄱ   play-services-auth

        // 새로운 기능 추가 !!! 아직 공식문서에도 안써이땀
        // 이 앱에서 다른 앱을 연동할 때, manidast.xml 의 공개패키지를 설정해야 함.
        // 아 아니다 유투브만 패키지 설정해야 함,
//https://console.firebase.google.com/project/ex089firebasefirestoredbwookie/authentication/providers

        // 1. 구글 로그인을 위한 옵션 설정 객체 생성 - 빌더 이용
        // 구글 계정엔 일반 / 게임 이렇게 두개 있음
        // 일반 - GoogleSignInOptions.DEFAULT_SIGN_IN
        // 게임 - GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN

        // 2. requestIdToken :
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                            .requestIdToken("AIzaSyD3n23Y9I-QFJa6i44oUupEunlztcUc_kY") // 접속 아이디를 요청하는 토큰
                                            .requestEmail()
                                            .build();


        Intent intent = GoogleSignIn.getClient(this,signInOptions).getSignInIntent();
        mStartForResult.launch(intent);

   }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                //result.getResultCode()를 통하여 결과값 확인
                if(result.getResultCode() != RESULT_OK) return;
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                Toast.makeText(this, " 이메일 성공", Toast.LENGTH_SHORT).show();
            }
    );


    private void clickSignIn() {

       //이메일과 비번을 이용한 로그인 인증
        String email = binding.etEmail.getText().toString();
        String pw = binding.etPassword.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(task -> {
            if(task.isSuccessful()){

                // !!!!!  맨 먼저 인증받은 사람이 지금 이 사람이 인증했는지를 확인해야 함.
                if(firebaseAuth.getCurrentUser().isEmailVerified()){
                    String name = firebaseAuth.getCurrentUser().getEmail();
                    binding.tv.setText(" 로그인 성공");
                }else{
                    Toast.makeText(this, "이메일 인증부터 완료해주세요.", Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        });

        //로그인 했으면 이제, 현재 사용자의 정보 가져오기 - Password는 가져올 수 없음 ㅋㅋ





    }

    private void clickSignUp() {
       //1예제 ) 이메일을 통한 인증방식 회원가입 -> 이메일로 인증 확인 메일리 보내지고, 사용자가 인증을 확인했을 때 , 가입이 완료되는 방식
        String email = binding.etEmail.getText().toString();
        String pw = binding.etPassword.getText().toString();

        //근데 이걸 넣는데 시간 걸리니께
        // 비동기 방식 기다릴 때, 알려주는 리스너 달아놓자!
        firebaseAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // 사용자가 입력한 email과 pw가 사용 가능한지를 검사한 결과. -> 유효성 검사.
                // 총 3가지를 검사해서 성공인지 아닌지 알려줌.
                // 1. 이메일 형식에 맞는가 ?
                // 2. pw가 6자리 미만인가 ?  ( 연속된 번호 이런건 안해주고 갯수만 검사함)
                //3. 기존 이메일에 같은 이름이 있는가?

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "이메일과 비밀번호 사용 가능 ㅊㅋ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "이메일과 비밀번호 형식을 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
                    // 현재 상태 까지도 파이어베이스에 등록은 됨.
                // 다만 인증이 안 된 상태. 인증 안하면 나중에 데이터 못가져옴 id 가져와 이런거 못함
                //1. 이제 인증확인 이메일 전송 , 2.전송 성공여부 확인할거임

                //getCurrentUser() 지금 로그인 한 애
                // sendEmailVerification() 이걸로 메일 감
                //addOnCompleteListener() 성공여부 알려주는 애 달자 ~ㅑ
                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "전송된 이메일 확인 후 인증하세요", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "메일 전송에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });




            }
        });






    }
}