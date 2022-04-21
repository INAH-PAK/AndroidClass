package com.inah_wook.ex072cameraapp3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //동적 퍼미션 요청하는 요청코드
        //D
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED){
            requestPermissions(permissions, 100); //100은 모 걍 식별하기 위한거니까 아무거나 쓰기~
        }



        iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(view -> {
            // 자,
// 메인엑티비티가 만든 인텐트 아저씨,
            // 이 아저씨가 카메라 앱에세 사진 달라고 하면 비트맵으로 줘버리잖아?
            //이렇게 하지 않고,
            // 먼저 !
            // 갤러리 앱 으로 요청을 할 때, 아예 사진으로 달라고 말하는고임. A
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


            //저장될 파일의 uri를 설정하는  기능 메소드를 호출.
            //따로 없어서 직접 만들어야 함.
            //C
            setImageUri();



            //여기가 핵심 ~~~
            //A . 카메라 앱에게 촬영한 이미지를 파일로 저장하라고 요청하기 위해
            // 저장할 파일으ㅣ uri를 추가데이터를 인텐트에게 전달
            // 긍까 아예 사진 경로가 저장
            //putExtra(식별자, 값)
            // imgUri : 밑에서 참조한 값.

            if(imgUri != null) intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);





           // resultLauncher.launch(intent);


        });

    }
    //맴버변수 위치
    // 카멜 앱이 촬영한 사진을 저장할 파일의 결호 uri 참조변수
    Uri imgUri;
    // C. 저장할 파일의 경로 Uri 를 설정하는 기능 메소드
    void setImageUri(){

        //자 경로를 어케 구하냐

        //옛날에 파일 입출력에서 배우긴 했는데. 인터널 스토리지 등
        //  getExternalStoragePublicDirectory()  :  안드로이드에 다 있는 폴더, 알람, DCMI 등등
        // getExternalStorageDirectory() : 카카오 파일같이 새로운 폴더
        // 외부 저장소의 File 경로를 먼저 얻어옴.

        // 근데 이 경로는 무조건 동적 퍼미션 필요 !!!! 매니페스트 ㄱㄱ
//그리고 맨 위에 D
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        // 경로가 정해졌으니까 파일명을 정하자 !
        // 근데 이름 중복되어 있으면 안돼는데 원래 있는지 어케 암?
        // 두가지 방법이 있음
        // File.createTempFile() : 이걸로 쓰면 복잡해서 잘 안씀
        // 날짜로 이름을 쓰면 겹칠일이 없음 ~
        // GMT 천문대 기준 시간이므로 ~ 우린 10시라도 00으로 저장됨 ~
        // 그럼 시간을 어케 알아? 현재 시간을?
        // 특정 날짜 포맷으로 만들어주는 객체가 있음
        SimpleDateFormat scf = new SimpleDateFormat("yyyymmdd_hhmmss");  // 그럼 결과는 20220323_103711~
        String fileNAme = "IMG" + scf.format(new Date()) + ".jpg"; // png는 jpg에 투명도까지 가진 거라서 용량커서 사진은 .jpg 로 하기 ~
        // 그럼 경로 scf, 파일명 fileName 이 정해졌으니 ~
        //그럼 이 둘을 합친 file 객체 만들기 고고 ~
        File file = new File(path,fileNAme);
        // 그럼 잘 저장되었는지 확인 하자 ~
       //카메라 앱 돌아가는지 확인용 new AlertDialog.Builder(this).setMessage(file.getAbsolutePath()).create();
    // 자, File : 실제 파일의 절대 결호
        // Uri : 파일의 DB경로 ( 즉, 운영체제에서 관리하는 컨텐츠의 경로 : 시작시, )

        // Uri 밑에 URL 이 있는거임.
        // URL 은 http~~로 시작하는거고
        //Uri 를 보자. Uri 는 Tel~~, file~~, 등등 맨 앞에 이렇게 붙음.
        // 그래서 이 Uri를 컨텐츠 경로라고 함.

         // 카메라 앱은 사진이 저장 될 경로인 EXTRA_OUTPUT 에 절대경로인 File 이 아니라
        //콘텐츠 경로인 Uri 를 요구함,
        //근데 우린 위에서 File인 절대경로로 구했잖아?
        // 그래서 File -> Uri 로 바꾸자 ~
        //근데 원래 쉬웠는데 M 버전부터 이 변환작업 시, FileProvider하는 애가 필요

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.N){  //누가버전 이전의 옜날 방식
            imgUri = Uri.fromFile(file);
        }else{
                //공유폴더 에 액티비티 4대 provider 참고.
            //다른 앱에게 접근을 허용해주도록 하는 provider가 필요함,
            // 특정의 파일 경로만들 알려주는 거라면 FileProvider를 ㅇ용하면 됨.
            //두번째 파라미터 authority : FileProvider 객체의 식별 명칭 . 우린 얠 만들어서 써야 함, 이때 주는거
            imgUri = FileProvider.getUriForFile(this,"com.inah_wook.ex072cameraapp3.FileProvider",file );

            //FileProvider 객체 만들기
            // 어케?
            // AndroidMenifests에 등록만 하면 됨 ~
            // 원래 프로바이더를 사용하려면 MyProvider 를 만들고 등록하고 써야 하는데
            //우리가 쓸 FileProvider 는 이미 있으니까 등록하러 ㄱㄱ~ 3.22 / 30:00
            // 매니페스트 ㄱㄱ
            // 했어? 왔음면  밑에 ㄱㄱ
        }
        //
        // new AlertDialog.Builder(this).setMessage(imgUri.toString())



    }

    ActivityResultLauncher resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                    // 개발자가 지정한 imgUri에  카메라가 촬영 한 사진이 저장되어 있을 것이므로,
                //결과 받을 필요 없이 걍 찍음 돼~
                Glide.with(MainActivity.this).load(imgUri).into(iv);

            }

            //존나 어려우니,, 다음 비디오 예쩨로 다시 연습 ㄱㄱㄱㄱ


        }
    });

// D3 동적 퍼미션 요청 결과 콜백
    //grantResults : 허가 결과
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "사진 촬영 가능", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "사진 촬영 불가 ", Toast.LENGTH_SHORT).show();
        }
    }
}