package com.inah_wook.ex022actionviewandactionmode;

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

    ///////////////////      목차

    //  A. ActoinView Layout :   OptionMenu Item 을 클릭시 지정한 레이아웃 모양으로 펼친다.
    //  B. Action mode : 제목줄 (Action Bar) : 제목줄을 덮는 새로운 액션바를 만든다. == 오버레이드

    ////////////////////////////

    EditText actionviewEt;
    Button btn;

///////////메인 엑티비티
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActionMode(new ActionMode.Callback() {

                    //액션모드 버튼을 눌렀을 때 !!!
                    // 액션 모드를 처음 시작시 메뉴들을 만들기 위해 한 번 실행되는 콜백 메소드
                    //여기서 보여줄 메뉴를 만든다 !
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

                        getMenuInflater().inflate(R.menu.actoinmode, menu);

                        actionMode.setTitle(" 액션모드");
                        actionMode.setSubtitle(" 이거슨 액션모드");

                        //이 리턴값이 true가 아니면 action mode가 발동하지 않음.
                        return true;
                    }

                    @Override// 매번 액션모드가 열릴 때 마다 발동
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    //액션 모드에 만든 메뉴 아이템들이 클릭되면 발동하는 메소드
                    //클릭 된 아이템이 menuItem 임
                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_aa:
                                Toast.makeText(MainActivity.this, "공유", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_bb:
                                Toast.makeText(MainActivity.this, "다ㅏ어", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_cc:
                                Toast.makeText(MainActivity.this, "다이얼러", Toast.LENGTH_SHORT).show();
                                break;

                        }

                        return false;
                    }

                    //액션 모드가 종료될때 자동으로 발동하는 메소드
                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {

                    }
                });
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //얘가 menu.xml을 가져와서 자동으로 객체 만들고 참조까지 해줌 !

        getMenuInflater().inflate(R.menu.option, menu);

        //ActionView 를 가지고 있는 MenuItem 객체 참조
        //옵션 메뉴를 가진 엑션 뷰가 필요.


        //자, public boolean onCreateOptionsMenu(Menu menu) 맨 위에 여길 봐

        MenuItem item = menu.findItem(R.id.menu_action);
        // 그러니까, 우리는 LinearLayout으로 아이콘과 에딧텍스트를 만들었잖아?
        // 이게 바로 액션 뷰야. 이걸 이제 우리가 가져와서 써야 함,
        //근데 이새끼는 결국 레이아웃을 가져왔다고만 알잖아? 그래서 우리가 형변환으로 다운캐스팅 해조야 함.
        // LinearLayout layout = (LinearLayout) item.getActionView();
        //근데 너무 길어서 밑에껄로 씀'
        View view = item.getActionView();


        actionviewEt = view.findViewById(R.id.actionview_et);


        // Edit text의 액션버튼 ( 소프트 키보드의 우하단 버튼 == 보통의 엔터버튼 )
        //을 클릭했을 때 반응하는 리스너 만들자 !
        actionviewEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                // 두번째 파라미터 i : 본 이름은 Action i
                // 아 내가 뭘 했다하는 행동 식별자임 .
                // == 키보드 에서 어떤 키를 눌렀는지 식별자를 줌.
                // 우린 아까 ems 옵션으로 서치를 했ㅇㅆ잖아?
                // 이
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String msg = actionviewEt.getText().toString();
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}