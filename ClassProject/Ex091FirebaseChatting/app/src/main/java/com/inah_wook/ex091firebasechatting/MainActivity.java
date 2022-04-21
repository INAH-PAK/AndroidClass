package com.inah_wook.ex091firebasechatting;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.inah_wook.ex091firebasechatting.databinding.ActivityMainBinding;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // 1. 먼저 파이어 베이스와 연동 ! -> 파베 콘솔사이트 ㄱ
    // 가이드문서 참고 https://firebase.google.com/docs/android/setup?authuser=0&%3Bhl=ko&hl=ko
// 1-1. Firebase 콘솔에 프로젝트 생성

    //isFirstLogin : 처음 앱을 실행해서 프로필 정보가 없는가? 알기 위한 변수. clickBtn()판단 여부
    Boolean isFirstLogin = true;
    // 자 근데 사용자가 프로필 변경하고 싶잖아? 그걸 판단하는 변수 ~ clickImg()
    Boolean isChanged = false;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //프로필 이미지 설정 버튼
        binding.civ.setOnClickListener(v->clickImage());
        binding.btn.setOnClickListener(v->clickBtn());

        //C . SharedPref 에 저장된 닉네임과 URL이 있는지 확인 => 로그인 내역 여부 판단
        SharedPreferences pref = getSharedPreferences("account",MODE_PRIVATE);
        // 현재 글로벌도 다 날아간거잖아?
        // 에디터로 넣었던거 닉네임 ~~
        G.nickname = pref.getString("NickName",null); //s1 : 실패했을 때
        G.profileUrl = pref.getString("ProfileUrl",null); //s1 : 실패했을 때

        if(G.nickname!= null){
            binding.et.setText(G.nickname);
            Glide.with(this).load(imgUri).into(binding.civ);
            isFirstLogin = false; // 정보를 저장하는거니까 첫 로그인이 아니지~ 저장 한 적이 있다는 거니께
        }







    }

    private void clickBtn() {

        // 프로필 이미지와 닉네임을 firestore data base에 저장하자 !
        //단, 이미지파일은 storage에 먼저 업로드 해야 함 - 시간이 오래 걸리기 때문
       //D. 이렇게 무조건세이브 말고, 로그인 한 적이 없다면 -> 저장 + 화면저장 ; 로그인 이력 있으면 -> 화면전환


        if(isFirstLogin || isChanged) {  // 이미지를 변경 한 적이 없거나, 프로필을 선택했다면
            saveData();
        }else {

        //     chattingActivity로 이동
        Intent intent = new Intent(this, ChattingActivity.class);
        startActivity(intent);
        finish();

        }

    }
    FirebaseFirestore firebaseFirestore; // 파이어 베이스 스토리지 관리자 객체
    private void saveData() {

        //1. 이미지 업로드를 위한 파이어베이스 스토리지에 선택한 이미지 파일을 업로드 하자.
        // 근데 사용자가 사진 안넣고 그냥 저장하면 null이잖아?
        if(imgUri == null) {
            Toast.makeText(this, "프로필 이미지를 먼저 선택하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        //근데 올릴 때 폴더하나 만들고 ... 저장될 파일면이 중복되지 않도록 날짜를 이용한 파일명 이용
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileNmale = simpleDateFormat.format(new Date())+".png";

        //filebase storage에 이미지를 업로드 하기 위한 참조객체 얻어오기
        StorageReference imgRef = firebaseStorage.getReference("profileImage/"+fileNmale);
        // 이제 이미지 업로드
        imgRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // 여기까지 왔으면 업로드 성공
                //업로드된 파일의 '다운로드 두소'가 필요 -> 서버에 있는 이ㅣ지의 인텅ㅅ ㅑㅇ러 ==url
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // 이제 파이어베이스 저장소에 저장되어있는 이미지에대한 다운로드 주소를 문자열로 읽어오가

                        Toast.makeText(MainActivity.this, "프로필 이미지 저장 완료", Toast.LENGTH_SHORT).show();

                        //A
                        // 이제 파이어 데이스 데이타 비에스에 닉네임, 이미지 주소 URL을 저장할거임.
                        // 이 디바이스의 ㅇ=쉐어드프리페런스러 자\

                        G.profileUrl = uri.toString();
                        G.nickname = binding.et.getText().toString(); // 즐겨찾기 여부 이렇게 저장해도 됨
                        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                        // "profile 이라는 이름의 collerction 참조 ( 있으면 생성 , 없으면 참조)
                        // 우린 지금 없으니까 참조
                        CollectionReference profileRef = firebaseFirestore.collection("profile");
                        //  닉네임을 Document 값으로 쓰고, profile을 이미지 경로 URL 을 저장
                        // 필드값은 맵 컬렉션 형식
                        HashMap<String,Object> profile = new HashMap<>();
                        profile.put("profileUrl",G.profileUrl); // 필드에 넣을 프로필 주소~ 닉네임은 Document로 넣을거임 ~
                        profileRef.document(G.nickname).set(profile);

                        // B 이 디바이스의 sharedPreferernce 에도 저장하
                        SharedPreferences pref = getSharedPreferences("account",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit(); // 에디터 객체
                        editor.putString("NickName", G.nickname);
                        editor.putString("ProfileUrl", G.profileUrl);
                        editor.commit();

                        // 저장이 완료되었으니 , 이제 채팅 화면으로 전환 !
                        Intent intent = new Intent(MainActivity.this,ChattingActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }
        });

    }

    Uri imgUri ; // 선택된 이미지의 uri
    private void clickImage() {
        // 사진앱을 실행해서 이ㅣ지를 선택하고 결과받기
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);

        // 새로운 사진을 선택했으니까
        isChanged = true; // 이제 clickBtn() ㄱㄱ


    }
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()!= RESULT_OK) return;

            imgUri = result.getData().getData();
            Glide.with(MainActivity.this).load(imgUri).into(binding.civ);
        }
    });

    @Override
    public void onBackPressed() {
       finish(); // 31q버전부터는 이거 해야 뒤로가기로도 꺼짐
    }
}