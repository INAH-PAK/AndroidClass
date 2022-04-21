package com.mrhi2022.ex021searchviewmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //옵션메뉴의 메뉴항목 안에 있는 SearchView의 참조변수
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // onCreate()메소드가 실행된 후 자동으로 Option Menu를 만들기 위해
    // 발동하는 콜백메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // res/menu폴더 안에 있는 option.xml을 읽어와서 메뉴객체로
        // 생성(부풀려주는: inflate)해주는 객체를 통해서 메뉴 생성
        getMenuInflater().inflate(R.menu.option, menu);

        //메뉴안에 있는 메뉴항목 참조하기
        MenuItem item= menu.findItem(R.id.menu_search);
        searchView= (SearchView) item.getActionView();

        //힌트 글씨 변경하기
        searchView.setQueryHint("input word...");

        //처음 시작이 아이콘이 아닌 상태로..
        searchView.setIconified(false);

        //소프트키보드의 검색버튼(돋보기모양버튼)을 클릭했을때 반응하는 리스너객체 생성 및 설정
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //돋보기 버튼을 클릭했을 때 발동하는 콜백메소드
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query+"를 검색합니다.", Toast.LENGTH_SHORT).show();

                //혹시 SearchView에 써있는 글씨가 없어졌으면...또는 원하는 글씨로 미리 설정하고 싶다면.
                searchView.setQuery("", true);
                //혹시 곧바로 아이콘 모양으로 돌아가고 싶다면
                searchView.setIconified(true);

                return false;
            }

            //글씨가 변경될때 마다 마다 실행되는 콜백메소드
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}