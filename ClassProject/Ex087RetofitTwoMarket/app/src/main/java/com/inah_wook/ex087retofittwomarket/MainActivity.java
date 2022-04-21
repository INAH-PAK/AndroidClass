package com.inah_wook.ex087retofittwomarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.inah_wook.ex087retofittwomarket.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

ActivityMainBinding binding ;
MarketAdapter adapter;

ArrayList<ItemVO> items = new ArrayList<ItemVO>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       adapter = new MarketAdapter(this, items);
       binding.recycler.setAdapter(adapter);
       // 리사이클러뷰에 구분선 꾸미기( ItemDecoration(클래스임) -DividweItemDecoration(클래스임) :내가 원하는 모양 만들 수 있음) )
    binding.recycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


       binding.btn.setOnClickListener(v-> {

            //글작성 화면 (액티비티) 전환 -> 서버개념이 생기면서, 작성하면 서버에 보내서 저장하고 , 굳이 다음 화면에서 에딧텍스트는 필요없음;
           Intent intent = new Intent(this, EditActivity.class);
           startActivity(intent);

        });

        //동적퍼미션
        String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(checkSelfPermission(permissions[0])== PackageManager.PERMISSION_DENIED){
            requestPermissions(permissions, 0);
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "사진 촬영 가능", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "사진 촬영 불가 ", Toast.LENGTH_SHORT).show();
        }
    }

// ㅇㄱ팁티가 화면에 보여질 때

// 자,
    // 메인은 항상 동작중...
    // 글쓰기 버튼을 누르면 , 그때 데이터들을 띄워야 하잖아?
    // 근데 loadData(); 가 화면이 보여질 때 로드되면
    // 글쓰기 샥 누르고 메인 돌아가면 자동으로 새로고침 됨 ~~~

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    private void loadData() {

        binding.p.setVisibility(View.VISIBLE); // 프로그래스바 보여주는 거

        // 레트로핏으로 이제 받아와야 함 ~
        // php ㄱㄱ
        // 가따와써?
        // 이제 서버에서 데이터를 불러오는 기능 메소드 loadData() 작업 시작.
        Retrofit retrofit = RetrofitHelper.getRetrofitInstanceGson();
        RetrofitService service = retrofit.create(RetrofitService.class);
        // 명세서 만들러 interface RetrofitService ㄱㄱ
        Call<ArrayList<ItemVO>> call = service.loadDataFromServer();
        call.enqueue(new Callback<ArrayList<ItemVO>>() {
                         @Override
                         public void onResponse(Call<ArrayList<ItemVO>> call, Response<ArrayList<ItemVO>> response) {
//                           //  items=response.body(); // 근데 이렇게 넣으면 통채로 넣는거라 만일 대량의 데이터라면 시간 개오래 걸림
//                           //  adapter.notifyDataSetChanged(); // 이게 전체를 다 뒤집는거.

                             items.clear();// 녹음 4:00:00 참고ㄴ
                             adapter.notifyDataSetChanged();

                             ArrayList<ItemVO> list = response.body();
                             for(ItemVO item : list){
                                 Log.i("ddddddddd",item.title);
                                 items.add(0,item); // 0번 인덱스에 item을 붙이겠다 !그래야 최신순으로 숑숑 맨 앞에 뜨잖아?
                                 adapter.notifyItemInserted(0); // adapter.notifyDataSetChanged();는 전체니까 notifyItemInserted 이거씀
                             }
                            binding.p.setVisibility(View.GONE); // 이제 프로그레스바
                         }

                         @Override
                         public void onFailure(Call<ArrayList<ItemVO>> call, Throwable t) {

                          Log.i(" 야야야야야야야야ㅑ야야야야야야  ",t.getMessage());
                             Toast.makeText(MainActivity.this, "망해따 에러  ㅠ " + t.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }
        );


    }
}