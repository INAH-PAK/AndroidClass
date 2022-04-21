package com.mrhi2022.ex053xmlpullparsermovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //영화진흥위원회 open api : 주간박스오피스 정보 이용하여 앱개발

    //영화진흥위원회 api사이트에서 발급받은 key
    String apiKey="74dc59693ec933ed4c4f0a8e538bc31d";

    ArrayList<Item> items= new ArrayList<>();
    RecyclerView recyclerView;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //테스트목적으로 우선 가상의 데이터 추가
        //items.add( new Item("1","aaa","bbbb","cccc") );

        recyclerView= findViewById(R.id.recycler);
        adapter= new MovieAdapter(this, items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn).setOnClickListener(v->{loadData();});
    }//onCreate method..

    //서버에서 데이터를 읽어오는 작업 수행하는 기능 메소드
    void loadData(){
        //네트워크를 통해서 xml문서을 읽어오기..
        //1.네트워크작업은 반드시 허가(퍼미션)필요함. - AndroidManifest.xml에서 작성
        //2.네트워크작업은 반드시 별도 Thread 가 해야만 함. [즉, main thread는 네트워크 작업 불가]
        Thread t= new Thread(){
            @Override
            public void run() {
                //이 run()메소드 안에 써야 별도의 직원객체(Thread)가 처리함

                //검색하고자 하는 날짜를 저장하는 변수
                //오늘 날짜의 전날로 자동으로 계산되도록
                Date date= new Date();//객체 new 될때의 순간의 날짜/시간을 가지고 있음.
                //어제 날짜로 새로이 설정하기 [ data.getTime() : 1970년 1월 1일 0시 0분 0초부터 1ms마다 카운트가 증가된 값 ]
                date.setTime( date.getTime() - (1000*60*60*24)  ); //현재시간에서 24시간을 빼기

                //날짜를 특정 포멧으로 만들어주는 클래스 객체 존재함
                SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
                String dateStr= sdf.format(date); //"20220310"

                //영화진흥위원회 open api 주소 url
                String address="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml"
                        +"?key=" + apiKey
                        +"&targetDt=" + dateStr
                        +"&itemPerPage=10";


                //이 앱과 서버는 지구와 아스가르드 처럼 서로 멀리 떨어진 곳임.
                //둘 사이에 데이터를 주고받기 위한 무지개로드가 필요함- 그 무지개 로드를 Stream이라고 부름

                try {
                    //무지개로드를 열어주는 해임달 객체 생성
                    URL url= new URL(address);
                    //무지개로드는 일방통행...데이터를 읽어들이는 Stream 필요함
                    InputStream is= url.openStream(); //바이트 스트림
                    InputStreamReader isr= new InputStreamReader(is);//문자 스트림

                    //Reader를 통해 한문자씩 읽어들여서 분석하는 작업은 너무 복잡함.
                    //그래서 이 isr 을 통해 읽어들인 xml문서를 분석(parse)해주는 객체 : XmlPullParser
                    XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                    XmlPullParser xpp= factory.newPullParser();
                    xpp.setInput(isr);

                    //xpp를 이용하여 설정된 서버에 xml문서를 분석하는 작업 수행

                    int eventType= xpp.getEventType();

                    Item item=null; //영화정보 1개 데이터들을 저장할 객체참조변수

                    //xml문서의 끝까지 반복적으로 읽어와서 분석
                    while (eventType != XmlPullParser.END_DOCUMENT){

                        //eventType에 따라 원하는 작업코드 수행
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                //화면(UI)변경 작업은 main(ui) thread 만 할 수 있음.
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //이 영역안에서는 UI작업 가능함
                                        Toast.makeText(MainActivity.this, "파싱시작!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case XmlPullParser.START_TAG:
                                String tagName= xpp.getName();
                                if(tagName.equals("dailyBoxOffice")){
                                    item= new Item();
                                }else if(tagName.equals("rank")){
                                    xpp.next();
                                    if(item!=null) item.rank= xpp.getText();
                                }else if(tagName.equals("movieNm")){
                                    xpp.next();
                                    if(item!=null) item.name= xpp.getText();
                                }else if(tagName.equals("openDt")){
                                    xpp.next();
                                    if(item!=null) item.openDt= xpp.getText();
                                }else if(tagName.equals("audiAcc")){
                                    xpp.next();
                                    if(item!=null) item.audiAcc= xpp.getText();
                                }
                                break;

                            case XmlPullParser.END_TAG:
                                String tagName2= xpp.getName();
                                if(tagName2.equals("dailyBoxOffice")){
                                    //일일 박스오피스의 영화1개당 List Item 하나가 되도록..
                                    //리사이클러뷰에서 보여줄 대량의 데이터들..(items)리스트에 추가
                                    if(item!=null) items.add(item);
                                }
                                break;
                        }

                        eventType= xpp.next();
                    }//while..

                    //리사이클러뷰가 보여줄 뷰들을 만들어내는 Adapter가
                    //대량의 데이터들(items)에 데이터가 새로이 추가되었다는 것을
                    //인식하지 못해서 리사이클러뷰의 화면이 갱신되지 않음.!!
                    //그래서!! 데이터가 변경되면 아답터에게 공지해야 함!!

                    //단, 화면갱신은 반드시 ui thread 에서만 해야 함.
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

            }
        };
        t.start();




    }


}