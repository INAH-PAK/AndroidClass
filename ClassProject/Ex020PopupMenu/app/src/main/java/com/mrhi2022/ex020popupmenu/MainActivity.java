package com.mrhi2022.ex020popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= findViewById(R.id.btn);
        btn2= findViewById(R.id.btn2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Popup Menu 만들기

                //PopupMenu 객체 생성 및 메뉴항목 적용
                //PopupMenu popup= new PopupMenu(MainActivity.this, btn); //두번재 파라미터가 팝업메뉴가 붙을 뷰
                PopupMenu popup= new PopupMenu(MainActivity.this, btn2); //다른 위치에 메뉴가 보여지게 할 수 있음.
                getMenuInflater().inflate(R.menu.popup, popup.getMenu());
                //팝업메뉴 보이기
                popup.show();

                //팝업메뉴의 메뉴항목을 클릭했을때 반응하는 리스너객체 생성 및 설정
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        int id= menuItem.getItemId();

                        if( id==R.id.menu_info ) Toast.makeText(MainActivity.this,"INFORMATION", Toast.LENGTH_SHORT).show();
                        else if(id==R.id.menu_delete) Toast.makeText(MainActivity.this, "DELETE",Toast.LENGTH_SHORT).show();
                        else if(id==R.id.menu_modify) Toast.makeText(MainActivity.this,"MODIFY",Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });



            }
        });
    }
}