package com.mrhi2022.ex018optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //액티비티는 제목줄(ActionBar)에 메뉴를 만들어내는 기능메소드가 이미 존재함
    //다만, 메뉴항목(MenuItem)이 없어서 안 보이는 것 뿐임
    //그래서 그 메뉴항목들을 만들어서 Menu에 추가해주는 코드를 작성하는 영역메소드가 있음.
    //이 메소드는 onCreate()메소드가 실행된 후에 자동으로 실행됨.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //자바로 메뉴항목을 추가하기 - 많이 선호되지는 않음
        //menu.add("SEARCH");

        //실무에서는 메뉴를 별도의 xml 문서에 항목들을 작성하고
        //이를 읽어와서 menu에 추가하는 형태로 코딩하는 방법을 선호함
        // xml문서를 읽어와서 객체로 만들어주는(부풀려주는:inflate) 객체가 존재함 : Inflater
        // menu폴더안에 있는 xml을 MenuItem객체로 만들어주는 객체 - MenuInflater
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.option, menu);

       return super.onCreateOptionsMenu(menu);
    }

    // OptionMenu의 메뉴항목(MenuItem)을 선택했을때
    // 자동으로 발동하는 콜백메소드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //선택한 메뉴항목의 식별자를 얻어오기
        int id= item.getItemId();

        switch ( id ){
            case R.id.menu_search:
                Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
                break;
                
            case R.id.menu_add:
                Toast.makeText(this,"ADD", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_help:
                Toast.makeText(this,"HELP", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}