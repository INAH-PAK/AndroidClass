package com.inah_wook.ex033fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager ;
    MyFragment myFragment;
    Button btn ,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 버튼 클릭 시 , id가 container 인 녀석에게 MyFragment를 추가시키는 기능을 하구 시포
                MyFragment myFragment = new MyFragment();

                //매니저에게 프레그먼트의 동적작업( add, remove, replace ) 을 시작한다고 명령 !
                //  Transaction : Process - 하나의 프로그램 하나
//                                    Thread -  프로그램 안의 코드를 실행하는 직원 친구
//                                    Task -  작업 하나하나를 말함. 테스트당 스레드 하나 . . . 이렇게 사용
//                Transaction - Task에 가깝. 작업 하나 시작해 == Task 하나 시작해 !
//                                ==> Transaction 은 롤백 기능을 가진 Task

                //그럼 왜 씀?
                // 자, 단순한 기능인 은행송금 Task 를 보자,
                // 계좌 A -= 100원 , 계좌 B+= 100원  :  이렇게 송금 명령하나는 두 개의 작업을 해야 함.
                //근데 이렇게 할때 Transaction이 필요
                // 먼저 A 에서 돈을 빼고 갑자기 서버 다운되면,
                // 돈 증발됨. . .
                // 이런 걸 막기 위해서 , 일반적인 Task 로 하지 않고 Transaction 으로 함.
                // 작업을 단위로 만들어서, 아예 작업이 중간에 뻑나면 아예 안할걸로 하는 것이 Transaction.

                // Transaction : 롤백 기능이 있는 process ( == 작업 단위 )
                // 완려헸다고 말하기 전에는 실제 작업이 실행되지 않음.

                FragmentTransaction tran =  fragmentManager.beginTransaction();
                tran.add(R.id.container,myFragment); // R.id.container 이건 자동완성 안되니까 걍 알아서 쓰자 ~

                // 여기 내가 원하는 작업들을 쓰면 됨.
                // 총 세가지를 쓸 수 있는데, add, remove, replace 임.

                // Fragment는 FILO stack 구조임.
                //만일 Fragment를 Back Stack에 저장하고 싶다면 ,,
                tran.addToBackStack(null);
                // 이걸 어따 씀?
                // 스벅 하단 메뉴 보면, 내정보 버튼 누르면 로그인 안되었을 때, 로그인 창도 백스텍에 넣어둠 !

                // 또, 만약 새로추가되는 Fragment 에 어떤 정보를 전달하고 싶다면 ?
                // 자료형  :  Bundle -  자료형 보따리.
                //map class 기반으로 만듬. collections , 자바에서 맵, 리스트, 셋 !
                // 글씨로 이름을 줄 수 있는 map 방식 !

                Bundle bundle = new Bundle();
                bundle.putString("name","sam");  //key : 식별자 (값의 라벨) , value : 보내는 값
                bundle.putInt("age",20);

                myFragment.setArguments(bundle); // MyFragment.java ㄱㄱ

                tran.commit(); //완료
                // 근데 이렇게 하면 아예 작업이 다 꺼짐.




            }
        });

        fragmentManager = getSupportFragmentManager();

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // container에서 fragment매니저가 fragment 삭제 작업 진행
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                if(myFragment != null) transaction.remove(myFragment);

                transaction.commit();


            }
        });


    }



}