package com.inah_jinwook.ex015toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
 //상속 관계 :  Context <- Activity <- MainActivity
public class MainActivity extends AppCompatActivity {  //Activity 도 운영체제 기능을
//화면에 보여질 뷰들의 참조변수
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml에서 만든 뷰객체를 찾아와서 참조변수에 대입.
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 토스트를 일일히 new 해야 하잖아? 근데 너무 많이 쓰니까
                //아예 new Toast가 되는 메소드가 있음. !

                // class AAA{class BBB{ AAA.this;}} 이래야 AAA의 this임.

                Toast t = Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT  );
                t.show();

                //Toast t = Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        });
    }
}