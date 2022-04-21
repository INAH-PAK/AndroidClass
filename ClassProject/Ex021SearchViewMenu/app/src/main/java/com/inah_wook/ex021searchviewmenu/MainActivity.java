package com.inah_wook.ex021searchviewmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//제목줄에 메뉴든 버튼이든 뭘 넣고 싶으면 옵션 메뉴 !
    //xml은 아무 상관 없음 ! 메인 엑티비티에 메뉴를 만들고 싶음.


    //옵션메뉴 안에 있는 search view 항목을 따로 만들자 !
    //사용자가 검색어를 입력하고 그 결과를 찾는 행위가 있어야 함 !
    //이를 위해선 searchView 가 필요.

    SearchView searchView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    //onCreate 메소드가 실행된 후 자동으로 옵션 메뉴를
    // 만들기 위해 발동하는 콜백 메소드 !!!!!!\
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // res 폴더 안에 menu라는 폴더 안에 있는
        //option.xml 을 읽어와서 메뉴객체로 생성 해주는 녀석 (Implator)  : 얘로 객체 자동 생성 !

        getMenuInflater().inflate(R.menu.option,menu);

        //메뉴 안에 있는 ==  메뉴 항목 참조하기  == 1. 빨강이 찾아오기 ==searchView 가 빨강이 안에 있으니까
        //menu.getItem 은 아이템의 해당 인덱스를 순서대로 가져옴 == 잘 안씀.
        MenuItem item = menu.findItem(R.id.menu_search);

        searchView = (SearchView) item.getActionView(); // 형변환 필요

        //힌트 글씨 변경
        searchView.setQueryHint("input word. . . . ");

        //처음 시작이 아이콘이 아닌 열려있는 상태로 코딩 가능
        //앱 키자마자 바로 검색해서 글씨 쓰고 검색 한다는 얘기 !
        searchView.setIconified(false);

        //서치 메뉴의 마무리는 키패드의 서치키임 !
        //따로 클릭 버튼이 없음.
        //검색을 누르면 나타나는 소프트키보드의 검색버튼 (돋보기 모양 버튼 )을 클릭했을 때 반응하는
        //리스너 객체를 생성
        // Query :  질의 글자

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override // 돋보기 버튼을 눌렀을 때 발동하는 콜백 메소드 !
            public boolean onQueryTextSubmit(String query) {
                @SuppressLint("ShowToast") Toast t = Toast.makeText(MainActivity.this,query+"를 검색합니다.",Toast.LENGTH_SHORT);
                        t.show();
                //혹시 Search View 에 써있는 글씨가 없어졌으면 . . . 또는 원하는 글자로 미리 설정하고 싶다면 ==> Query 로 설정
                searchView.setQuery(" ",true);

                // 검색 끝나고 곧바로 아이콘모양으로 회기하고 싶다면 ?
                //근데 이건 무조건 searchView.setQuery() 이걸로 글씨가 지워졌어야 가능 !!!!
                searchView.setIconified(true);
                return false;
            }

            @Override //글씨가 변경될 때 마다마다 실행되는 콜백 메소드
                        //이걸론 글자 수를 알 수 있고, 글자 하나 쓸 때마다 바로바로 반응 ~
                        //리소스 많이 먹을테니까 우린 위에꺼 onQueryTextSubmit 씀
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });





        return super.onCreateOptionsMenu(menu);
    }
}