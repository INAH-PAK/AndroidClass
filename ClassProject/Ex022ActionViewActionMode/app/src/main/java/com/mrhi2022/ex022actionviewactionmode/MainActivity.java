package com.mrhi2022.ex022actionviewactionmode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 1. ActionView Layout - OptionMenu Item 을 클릭했을때 지정한 layout 모양으로 뷰가 펼쳐저서 보여짐
    // 2. ActionMode - 제목줄(ActionBar)을 덮는 새로운 ActionBar에 옵션메뉴를 구성하는 모습

    EditText actionViewEt;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ActionMode 시작하기 - 액션바위치에 메뉴가 보여짐
                MainActivity.this.startActionMode(new ActionMode.Callback() {
                    //액션모드를 처음 시작할때 메뉴들을 만들기 위해 한번 호출되는 메소드
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        getMenuInflater().inflate(R.menu.actionmode, menu);

                        //리턴값이 true가 아니면 액션모드가 발동하지 않음.

                        actionMode.setTitle("action mode");
                        actionMode.setSubtitle("this is action mode");

                        return true;
                    }

                    //매번 액션모드가 열릴때 마다 발동하는 메소드
                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    //액션모드에 만든 메뉴아이템들이 클릭되면 발동하는 메소드
                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_aa:
                                Toast.makeText(MainActivity.this,"share", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.menu_bb:
                                Toast.makeText(MainActivity.this,"map", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.menu_cc:
                                Toast.makeText(MainActivity.this, "dialer", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }

                    //액션모드가 종료될때 발동하는 메소드
                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);

        //ActionView를 가지고 있는 MenuItem 객체 참조
        MenuItem item= menu.findItem(R.id.menu_action);
        //LinearLayout layout= (LinearLayout)item.getActionView();
        View view= item.getActionView();
        actionViewEt= view.findViewById(R.id.actionview_et);

        // EditText의 액션버튼(소프트키보드의 우하단 쪽의 색상버튼)을
        // 클릭했을때 반응하는 리스너 적용
        actionViewEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //두번째 파라미터 i : 소프트키보드에서 어떤 키를 눌렀는지 식별자..
                if( i == EditorInfo.IME_ACTION_SEARCH){
                    String msg= actionViewEt.getText().toString();
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}