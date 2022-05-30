package com.inah_jinwook.ex016alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// 화면ㅇ 보이는 뷰들의 뷰 참조변수 만들기
    Button btn;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;


    // 목록 다이얼로그에서 보여줄 item 항목들 == 아이템들의 배열 객체 !
    String[] items = {"Jave","XML","C++","Kotlin"};

    //멀티플 다이얼로그를 위한 배열 ~ : 체크박스
    boolean[] checked = new boolean[]{true,true,false,false};

    //싱글 다이알로그에서 선택 된 녀석 == 선택한 항목 인덱스
    int which =2;



    ////////////커스텀 뷰 //////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //5번째 버튼을 눌렀을 때 나타날 화면 .
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

//                builder.setTitle(" 커스텀 ~");
//
//                //이미지를 보여주려면 뷰 객체를 만들어서 줘야 함~
//                 근데 모든 뷰는 운영체제의 접근이 필요함. 그럼 여기서 매개변수에 현재 해당하는
 //                   이미지 뷰를 주면 됨 ~
//                ImageView img = new ImageView(MainActivity.this);
//
//                TextView tv = new TextView(MainActivity.this);
//                tv.setText("안녕 난 슬라임이야 ~ ");
//                tv.setTextSize(24); //무조건 단위는 px 임. 근데 여기에 sp 나 dp는 내가 일일히 계산해야 함.
//                // 아 존나 귀찮아 걍 xml로 쓰자 !
//
//                //다이얼로그는 한개의 뷰만 가능 함 !
//                //그럼 뷰 그룹으로 여러개를 보여주는 기법을 사용 함 !
//                LinearLayout layout = new LinearLayout(MainActivity.this);
//
//                //근데 roientation 안주면 디폴트 가로임.
//                layout.setOrientation(LinearLayout.VERTICAL);
//                layout.addView(img);
//                layout.addView(tv);
//
//
//                img.setImageResource(R.drawable.ms17);
//                builder.setView(layout);

                    //xml 에
        builder.setView(R.layout.dialog);
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });






        ///////////////멀티플 초이스 다이알로그 /////////////////////////////
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this);
                builder.setTitle("여러개 선택 가능한 ~");
                builder.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        //두번째 파라미터 -> 체크를 변경한 항목의 index
                        //세번째 파라미터 -> 바뀌어진 결과
                        checked[i]=b;


                    }
                });
                //완료 버튼을 누르면 결과가 반영됨 !
                builder.setPositiveButton("선택 완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s="";
                        for(int k = 0 ; k < checked.length ; k++){
                            if( checked[k] ) s+=items[k];
                        }

                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });




        ////////////////////싱글 초이스 다이알로그///////////////////////


        btn3=findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setTitle("Single Choice Dialog");
                                        builder.setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //두번째 파라미터가 선택한 항목의 인덱스 번호.
                                                which = i;

                                            }
                                        });
                                        builder.setPositiveButton(" ok ", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                        AlertDialog dialog = builder.create();
                                        dialog.setCanceledOnTouchOutside(false);
                                        dialog.show();
                                    }
                                });

        ////////////////// 리스트 선택 다이알로그
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {


                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setTitle("개발 얼어 선택");
                                        builder.setItems(items, new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //두번째 파라미터 1 : 선택한 항목의 위치 index . . .0,1,2 들이
                                                // Toast.makeText(MainActivity.this,i+"",Toast.LENGTH_SHORT).show();
                                                //두번째 파라미터 1 : 선택한 항목을 선택시 그 문자열이 궁금할 때 ! . . .0,1,2 들이
                                                Toast.makeText(MainActivity.this, items[i], Toast.LENGTH_SHORT).show();

                                                //근데 요즘엔 목록은 사용자가 실수 할 수 도 있으니까,
                                                // 저 목록 앞에다가 Radio 버튼을 이용 함.

                                            }
                                        });

                                        AlertDialog dialog = builder.create();
                                        dialog.setCanceledOnTouchOutside(false);
                                        dialog.show();

            }
        });


/////////////////////////////////// 단순 예 아니오 알랏 다이얼로그
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  1. 건축가 고용 !
                // Alert Dialog 를 만들어 주는 건축가 객체 생성
                // 건축가에게 운영체제 대리인 능력을 줌 ~
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // 이제 빌더에게 우너하는 다이얼로그의 모양을 의뢰 고고 ~
                // 일종의 설정 !
                builder.setTitle(" 주의 !");
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setMessage(" 정말 종료하시겠습니까? ");
                //이거만 써도 되지만 더 알아보기 ~
                // 자,
                // 뷰를 상속받은 버튼이 아닌, 알랏 다이알로그를 상속받은 뷰에 리스너가 필요 ~
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                    }
                });

                // Negative 버튼도 만들어 보자 ~
                builder.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"cancle",Toast.LENGTH_SHORT).show();
                    }
                });

                //여기까진 의뢰함.
                //그럼 이제 의뢰한대로 alert Dialog 객체를 만들어 달라고 건축가한테 요청.

                AlertDialog dialog = builder.create();

                //근데 다이얼로그에 버튼이 아닌 바깥쪽을 터치하면 없어지지 않도록. . .
                dialog.setCanceledOnTouchOutside(false); // 평상시 이 값이true 였던 거임 !


                //이제 다이얼로그를 보자 !
                dialog.show();

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                AlertDialog dialog = builder.create();

                //다이얼로그의 커스텀뷰안에 있는 이미지를 클릭시에 반응하기
                ImageView iv= (ImageView) dialog.findViewById(R.id.dialog_img);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iv.setImageResource(R.drawable.ms17);
                    }
                });
            }
        });






}
}