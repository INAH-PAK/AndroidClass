package com.inah_wook.ex087retofittwomarket;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;
import com.inah_wook.ex087retofittwomarket.databinding.ActivityEditBinding;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditActivity extends AppCompatActivity {
    ActivityEditBinding editbinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editbinding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(editbinding.getRoot());

        //제목글씨 변경 - 제목글씨 클래스 관리자 == 액션바
        getSupportActionBar().setTitle("글쓰기");
        // 제목줄 왼쪽의 <- 화살표 ( 기존화면으로 돌아가는거 )  : 모양만 설정하는거
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 업버튼 눌렀을 때 발동하는 콜백 메소드
        editbinding.btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");
                resultLauncher.launch(intent);


            }
        });
        editbinding.btnSave.setOnClickListener(v->clickSave());

   }

    ActivityResultLauncher<Intent> resultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                Uri uri= result.getData().getData();
                Glide.with(EditActivity.this).load(uri).into(editbinding.iv);

                //uri는 콘텐츠 주소(DB주소).
                //서버에 전송하려면 이미지파일의 절대경로가 필요함.
                //uri-->절대경로
                imgPath= getRealPathFromUri(uri);
                //확인
                new AlertDialog.Builder(EditActivity.this).setMessage(imgPath).create().show();
            }
        }
    });

    //이미지파일의 절대경로
    String imgPath;

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }


    void clickSave(){

        //작성한 데이터들 서버에 업로드 하기

        //전송할 데이터들 [ name, title, msg, price, imgPath ]

        String name  = editbinding.etName.getText().toString();
        String title= editbinding.etTitle.getText().toString();
        String msg= editbinding.etMsg.getText().toString();
        String price= editbinding.etPrice.getText().toString();

        //레트로핏 작업
        Retrofit retrofit= RetrofitHelper.getRetrofitInstanceScalars();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);

        //1) 이미지파일을 MultiPartBody.Part 로 포장 : @Part
        MultipartBody.Part filePart= null;
        if(imgPath!=null){
            File file= new File(imgPath);
            RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"), file);
            filePart= MultipartBody.Part.createFormData("img", file.getName(), requestBody);
        }

        //2) 나머지 String 데이터들은 Map Collection 에 저장 : @PartMap
        Map<String, String> dataPart= new HashMap<>();
        dataPart.put("name", name);
        dataPart.put("title", title);
        dataPart.put("msg", msg);
        dataPart.put("price", price);

        Call<String> call= retrofitService.postDataToServer(dataPart, filePart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s= response.body();
                Toast.makeText(EditActivity.this, s+"", Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(EditActivity.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // 자 이제 실행 ㄱ
        // 근데 아 맞당 그 하트 체크하는 코드 안만들었으니께
        //아답터 ㄱㄱ

    }

//옵션메뉴 아이템 클릭에 반응하는 콜백메소드
    // 오른쪽 위에 막 넣어놓잖아?
    // 그거 전용이 onNavigation ~~어쩌구
    // 액티비티 예제1ㄴ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) finish();

        return super.onOptionsItemSelected(item);
    }
}
