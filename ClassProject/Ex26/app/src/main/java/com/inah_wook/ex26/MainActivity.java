package com.inah_wook.ex26;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {


    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);



        findViewById(R.id.btn_save).setOnClickListener(v-> saveData());
        findViewById(R.id.btn_load).setOnClickListener(v-> loadData());

        //동적 퍼미션에 필요한 외부 저장소를 저장하는 버튼
        findViewById(R.id.btn3).setOnClickListener( v-> permissionSaveData());


    }

    void saveData() {
        //이젠 저장소는 storage 여기에 저장됨
        //내부저장소와 마찬가지로 ㅇㅂ을 삭제하면 이 공간의 파일도같이 삭제 됨.

        //먼저 !! sdcard의 유무를 확인해야 함.
        String state = Environment.getExternalStorageState();
        // 외장메모리상태 (state) 가 연결(mounted) 되어 있지 않은가를 확인
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            // 저장 할 수 없는 상태 == 연결 안됨
            Toast.makeText(this, "sdcard is not mounted", Toast.LENGTH_SHORT).show();
            return; // 그냥 이렇게 쓰면 함수 종료
        }

        //저장할 데이터 얻어오기
        String data = et.getText().toString();
        et.setText("");

        // 옛날 자바수업때 함
        // Data.txt 파일이 저ㅏ장될 디렉토리 경로부터 구하기
        File path ;
        //앱에게 할단된 영역 ( ~~ ) 폴더 안에
        //MyDir 이라는 폴더 경로 얻어오기

        File[] dirs = getExternalFilesDirs("MyDir");
        path= dirs[0]; // 0: 이미 폰을 살 때 부터 내외부 장착되고 외부 슬롯이 없는 애가 있었음
                        // 방수나 그런 것 때문에 할라고 했는데 지금은 없어짐.
                        // 그래서 폰에도 외부 메모리가 여러개일 수 도 있다고 생각해서 File 배열로 주는 거임
                        //자, 먼저 경로가 잘 받아졌는지 확인
        tv.setText(path.getPath());

        //위 경로에 Data.txt 라는 이름의 파일명.확장자 까지포함한 File객체 생성
        File file = new File(path,"Data.txt");

        //이제 파일까지 연결되는 무지개로드 만들기
        // 문자 스트림으로 바로 만들기
        try {
            FileWriter fw = new FileWriter(file,true);
            PrintWriter writer = new PrintWriter(fw);
            Log.i("tag","dddd");

            writer.println(data);
            writer.flush();
            writer.close();

            Toast.makeText(this, " 성고 ㅇ ", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.i("tag",e.getMessage());
        }

    }



    void loadData(){


        // sdcard 를 읽을 수 있는 상태인지
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            // 읽을 수 있는 상태.

            File path;
            File[] dirs = getExternalFilesDirs("MyDir");
            path = dirs[0];

            File file = new File(path,"Data.txt");


            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);

                StringBuffer buffer = new StringBuffer();

                while(true){
                    String line = reader.readLine();
                    if(line==null) break;
                    buffer.append(line+"\n");

                }

                tv.setText(buffer.toString());
                reader.close();

            } catch (FileNotFoundException e) {
                Toast.makeText(this, "파일 경로가 잘못되었습니다. ", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "읽기 실패", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "읽을 수 없어여 ", Toast.LENGTH_SHORT).show();
        }


    }



    //실무에서는 자동으로 온 크리에이트 할 때 아예 저장소들을 로드해옴.
    // 근데 sdcard는 보안에 취약하잖아?
    // 근데 앱에 할당된 공간 중에서, 알람, DCIM, Downdoad 등등 공공의 디렉토리들이 있잖아?
    // 그니까 내 앱에할당된 곳 말고 다른 폴더에 저장하고 싶을 때 가 있잖아?
    //버튼 3 ㄱㄱ

//퍼미션이 필요한 외부 저장서
    // - > 앱에게 할당된 경로뺘고, , ,  나머지는 모든 폴도,
    //이 공간에 저장하ㅕㄴ 앱을 지워도 데이터는 삭제되지 않음

    void permissionSaveData(){

        //외부 저장소 사용가능 여부 체ㅋ
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "외부 저겅소 럾어", Toast.LENGTH_SHORT).show();
        return;
        }

        // ..자 일단 먼저 마시멜로우 버전(api 23) 이성에서 도입된 보안강화 기능
        // 동적퍼미션 작업 - 우선 , AndroidManifast.xml 에 정적 퍼미션 작업
        //자바코드로 퍼미션에 대한 허가 체크
        int checkResult = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if( checkResult == PackageManager.PERMISSION_DENIED){
            // 퍼미션을 요청하는 다이알로그를 보이겠다.
            // 퍼미션 요청 다이알로그를 보이는 기능 메소드가 이미 액티비티에 존재한다 !
            String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}; //이거 나중에 많이 쓰니까
            //너무 어렵게 생각하지 말고 천천히 이해하자 ~.
            //일단 퍼미션들을 배열인거 보면 여러개 받을 수 있는데, 우린 하나임.
            requestPermissions(permissions,100); //두번째는 식별자.

            //Manifest 건들였으면 반드시 앱 삭제 후 다시 시작,



            return; // 여긴 허용받는 곳이니까 일단 함수를 끝냄.
        }
         //우리는 여기서 저장 작업을 할거임.
        //SDcard 특정위치에 저장하기
        //  -> 앱을 삭제해도 파일 그대로 남아 있는 영역.
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); // Document 는 없는 것도 많음.
        if(path!=null) tv.setText(path.getPath());

        File file = new File("aaa.txt");

        try {FileWriter fw = new FileWriter(file, true);
            PrintWriter writer = new PrintWriter(fw);

            writer.println(et.getText().toString());
            writer.flush();
            writer.close();

            et.setText("");
           tv.setText("SAVED");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }//on 메소드


    //requestPermission () 에 의해 보여진 다이얼로그 허ㅛㅇ 서부를 선택했을 때
    //자동으로 그 결과를 알려주기 위해 발동하는 콜백 메소드


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){

            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){ //허용했냐 그렇지 않냐
                Toast.makeText(this, "외부 저장소 쓰기 가능", Toast.LENGTH_SHORT).show();


            }else{
                Toast.makeText(this, "외부 젖ㅇ소 쓱; 불가", Toast.LENGTH_SHORT).show();
            }

        }



    }
} //액티비티