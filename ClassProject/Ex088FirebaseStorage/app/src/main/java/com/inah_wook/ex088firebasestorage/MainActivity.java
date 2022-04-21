package com.inah_wook.ex088firebasestorage;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.inah_wook.ex088firebasestorage.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
// Firebase : 서버쪽 작업을 대신해주는 구글 서비스 (php  이딴거 필요없음)

    //Firebase.consol.com 에 가서 프로젝트 만들기를 만들면
    //순서대로 이 프로젝트와 Firebase를 연동할거임. == 즉, 라이브러리 추가
    // 근데 단계가 좀 복잡
// https://console.firebase.google.com/ <- 콘솔만들기부터 시작.( 구글 로그인 필수)
// 프로젝트 이름은 암거나 가능 근데 전세계 유일해야 함

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLoad.setOnClickListener(v->clickLoad());
        binding.btnSelect.setOnClickListener(v->clickSelect());
        binding.btnUpload.setOnClickListener(v->clickUpload());






    }


    private void clickLoad() {
        // Firebase Storage에 저장되어있는 이미지 파일 읽어오기

        //1. Firebase Storage에  대한 관리객체 소환 -  getInstance로 알아서 다 주는~
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        //2. 파이어베이스 상의 최상위(root) 참조 객체를 얻어올 수 있음.
         // 내 파이어베이스 주소는 gs://ex088firebasestoragewookiesoft.appspot.com 이거잖아?
        // 그니까 몬가 루트 경로를 가져오고
        // 그 경로 아래! 자식들 == 내가올린 이미지 파일들 등등을 가져오는 느낌!
        StorageReference rootRef = FirebaseStorage.getInstance().getReference(); // 이렇게 하면 저장소 최상위 루트를 가리키고 있다고 생각해..
        //3. 원하는 파일의 참조개개체 가져오기
        StorageReference imageRef = rootRef.child("ch_chopa.png"); // 이건 이미지 파일 그 자체를 말한거구
        // 3-1. 폴더가 있을 땐? 명시하면 됨
        StorageReference imageRef2 = rootRef.child("photo/ch_chopa.png");
        //4. 파일 참조 객체로부터 이미지 URL 얻어오기
        // Firebase consol 에거 맨 오른쪽 메뉴에서 파일 위치 섹션에 억세스 할 수 있는 [억세스 토큰 ] : '억세스 토큰과 함께 다운로드URL' 의 주소를 가져오기 !
        if(imageRef!=null) {
            // 여기서 참조 객체로부터 다운로드URL 을 얻어오는 작업을 수행하자
            // 악 비동기방식임 == 별도 스레드 필요.. == 오래걸린단 얘끼
            // 성공되었다는 콜백메소드를 붇이기
            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(MainActivity.this).load(uri).into(binding.iv);
                }
            });
            //
        }

    }

    private void clickSelect() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        mStartForResult.launch(intent);

    }

   Uri imgUri = null;
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                //result.getResultCode()를 통하여 결과값 확인
                if(result.getResultCode() != RESULT_OK) return;
                imgUri = result.getData().getData();
                Glide.with(MainActivity.this).load(imgUri).into(binding.iv);

            }
    );


    private void clickUpload() {
    // 로드 데이터랑 비슷
FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();


            // 그럼 우리가 저장할 때 마다 이거 쓰잖아?
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

        String fileName = "IMG_"+sdf.format(new Date())+".png"; //우너래는 원본파일면에서 확장자 얻어와야 하지만 . . . 절대경로 코드가 지저분해서 그냥 무조건 png로 가자
        // ,jgp 여도 png로 저장 됨,
        // png의 압축 방식은 무손실 압축방식 > 투명도가 있는 압축방식.
        // jgp 손실 압축방식. -> 투명도가 없고 필요한것만 압축한거
        // png가 상위니까 다 가능~

//저장하는 참조객체
        StorageReference imgRef = FirebaseStorage.getInstance().getReference("uploads/"+fileName); //uploads 폴더가 없으면 만들고 그 밑에 aaa로 저장됨.

        // 선택한 imgUri객체를 이미지
        // A .   imgRef.putFile(imgUri);

        // 근데 A처럼 그냥 업로드 하는 거 보다는, 성공여부를 알아야 더 좋잖아?
        UploadTask uploadTask = imgRef.putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
            }
        });
    }


}