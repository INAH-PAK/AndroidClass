package com.mrhi2022.ex087retrofit2marketapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mrhi2022.ex087retrofit2marketapp.databinding.ActivityEditBinding;

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

    ActivityEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //제목글씨 변경
        getSupportActionBar().setTitle("글쓰기");
        //제목줄 왼쪽의 <- 화살표 버튼(up button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnSelect.setOnClickListener(view -> {
            Intent intent= new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            resultLauncher.launch(intent);
        });

        binding.btnSave.setOnClickListener(v->clickSave());
    }

    ActivityResultLauncher<Intent> resultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                Uri uri= result.getData().getData();
                Glide.with(EditActivity.this).load(uri).into(binding.iv);

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
        String name= binding.etName.getText().toString();
        String title= binding.etTitle.getText().toString();
        String msg= binding.etMsg.getText().toString();
        String price= binding.etPrice.getText().toString();

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


    }


    //옵션메뉴 아이템 클릭에 반응하는 콜벡메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //up버튼 클릭시에 종료
        if(item.getItemId()==android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}