package com.inah_wook.ex085retrofit2;

import static retrofit2.Retrofit.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.os.Bundle;

import com.inah_wook.ex085retrofit2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    //통신 작업을 위한 라이브러리
    // 원래는 오라클사의 okHTTP
    //2. Retrofit2 -> okHTTP를 계랑한 친구 ( 면접 )
    // HTTP url connection 을 사용했다가 ~~~
    // volley 는 구글이 인수했지만, 업데이트 지원정책을 디플리케이티드 되땀 하는 불안불안함. 스케어러블 !
    // 그래서 우리는 레트로핏2 사용... 녹음 듣고 정리하기.

    //그럼 먼저 우리는 라이브러리 임플하면 되는데
    // 그레이들 모듈 ㄱㄱㄱ

    ActivityMainBinding binding;

    @Override
    protected void onCreate(avedInstanceState) {
        super.onCreate(savedInstanceState);Bundle s
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // binding의 가장 최상위 뷰를 가져옴~ LinerLayout ~~~

        // GET 방식들
        binding.btn01.setOnClickListener(v-> clickBtn01());

        binding.btn02.setOnClickListener(v-> clickBtn02());

        binding.btn03.setOnClickListener(v-> clickBtn03());

        binding.btn04.setOnClickListener(v-> clickBtn04());

        binding.btn05.setOnClickListener(v-> clickBtn05());

        // POST 방식들

        binding.btn06.setOnClickListener(v-> clickBtn06());

        binding.btn07.setOnClickListener(v-> clickBtn07());

        binding.btn08.setOnClickListener(v-> clickBtn08());

        binding.btn09.setOnClickListener(v-> clickBtn09());








    }

    private void clickBtn09() {

        // 자 만약에 API에서 다 json 형식으로 주진 않잖아?
        // 근데 만약에 String으로만 줄 수도 있잖아?

        //  json Array 를 파싱없이 ( Gson X) 스트링값으로 받기
        // 결과를 String 으로 받으려면 Scalars 가 필요.
        // 모듈추가   implementation 'com.squareup.retrofit2:converter-scalars:2.9.0'
        Retrofit.Builder builder = new Builder();
        builder.baseUrl("http://webserver.dothome.co.kr");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = builder.build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<String> call = retrofitService.getJsonArrayToString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                binding.tv.setText(s);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                binding.tv.setText("에러 !" + t);
            }
        });



    }

    private void clickBtn08() {
        // json Array 를 파싱하여 ArrayList 로 받아오기 ~
        // 비쥬얼 스튜디오의   http://webserver.dothome.co.kr/04Retrofit/members.json

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<Member>> call = retrofitService.getJsonArray();
        call.enqueue(new Callback<ArrayList<Member>>() {
            @Override
            public void onResponse(Call<ArrayList<Member>> call, Response<ArrayList<Member>> response) {
                ArrayList<Member> members = response.body();
                // 실무에서는 이 리스트를 보여주기 위해 recyclerView 를 이용하지만, 그냥 스트링 버터써서 가져온 값 다화면에 찍어보기
                StringBuffer b = new StringBuffer();
                for(Member m : members){
                    b.append(m.name + "," + m.address + " , " + m.age);
                }
                binding.tv.setText( b );


            }

            @Override
            public void onFailure(Call<ArrayList<Member>> call, Throwable t) {
binding.tv.setText("에러 !" + t);
            }
        });






    }

    private void clickBtn07() {
        // POST 방식으로 Item 객체가 아닌 값 하나하나씩 서버에 전달하기
        //원래 에디텍스트로 받을거 우린 테스트니까 쓰기
        String title = "inah";
        String msg = "ff";

        Retrofit retrofit =  RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Call<Item> call = retrofitService.postMethodTest2(title,msg);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item i = response.body();
                binding.tv.setText(i.title + ":" + i.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("실패");
            }
        });



    }

    private void clickBtn06() {
        // POST 방식으로 Item 객체를 서버에 전달하기

        Item item = new Item("nice","afternoon"); // 서버로 보낼 객체 만들어땀 !

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Call<Item> call =retrofitService.postMrthodTest(item);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText( item.title + " : " + item.msg); // 에러
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("통신 실패" +t.getMessage() );
            }
        });




    }

    private void clickBtn05() {
        // 겟 방식으로 서버에 전달 -> map방식 사용

        // 레트로핏 객체 만드는 작업이 맨날 똑같음. . .
       // RetrofitHelper 만들기 ~
        //1. 객체 만들기
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        //2. 추상메소드 설계
        //3
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        //4
        // 보낼 데이터들을 Map 컬렉션 방식으로 묶어서 보내야 함!
        Map<String,String> datas = new HashMap<>();
        datas.put("title","aaa"); // k: 식별자 , v : 값 == 벨류
        datas.put("msg","좋은 하루 되세요.");
        Call<Item> call = retrofitService.getMrthodTest3( datas);

        // 5. 네트워크 작업
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText( item.title + " : " + item.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("통신 실패" +t.getMessage() );
            }
        });


    }

    private void clickBtn04() {
        // 겟 방식으로 서버에 전달하기 ~ + 파일명 설정
        String title = " hh ";
        String message = "ddd";

        Retrofit.Builder builder = new Builder();
        builder.baseUrl("http://webserver.dothome.co.kr" );
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.getMrthodTest2("getTedt.php", title, message);

        Call<Item> call = retrofitService.getMrthodTest2("getTedt.php", title, message);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText( item.title + " : " + item.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("통신 실패" +t.getMessage() );
            }
        });


    }

    private void clickBtn03() {
        // 겟 방식으로 서버에 전달하기 ~
        // 내가 쓴 데이터들을 서버로 보내보자
        // 원래는 EsitText로 사용자에게 받아서 이걸 서버로 보내야 하는데 귀찮으니 쓰자.
        String title = " 아뇽?";
        String massege ="반가워 레트로핏 " ;

        //1.
        Retrofit.Builder builder = new Builder();
        builder.baseUrl("http://webserver.dothome.co.kr" );
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //2. 추상메소드 설계
        //3.
        RetrofitService service = retrofit.create(RetrofitService.class);
        //4.
        Call<Item> call =  service.getMethodTest(title,massege);
        //5. 네트워크 작업 시작
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                binding.tv.setText( item.title + " : " + item.msg);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) { // 에러가 뭔지 가지고 있는 t
                binding.tv.setText("통신 실패" + t.getMessage() );
            }
        });



    }

    private void clickBtn02() {
        // 두번째 예제 ~~
        // 서버에 Path를 동적으로 지정할 수 있는 기능을 통해 json 데이터 이용하기
        // 작업단계 총 5가지
        //1.Retrofit 객체 만들기 (알랏 다이알로그처럼 만들기 빌더 ! )
        Retrofit.Builder builder = new Builder();
        builder.baseUrl("http://webserver.dothome.co.kr" );
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        // 2. 인터페이스 설계 추상메소드 -> RetrofitService.class -> getBoardJsonByPath()
        //3. /작업단계 3. 2단계에서 설계한 정리 ㅜ
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        //4. 추상메소드 호출해서 값주기
        Call<Item> call = retrofitService.getBoardJsonByPath("04Retrofit","board.json");
        //5. call이  실제 네트워크 작업 시작
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                // 응답된 json 은 gson을 이용해서 아이템 객체로 자동 파싱한 responseㄹ르 받자 !
                Item item = response.body();
                binding.tv.setText( item.title+","+item.msg );
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                binding.tv.setText("통신 실패");
            }
        });

    }

    private void clickBtn01() {
        // 단순하게 GET 방식으로 json 문서를 읽어오자 ~
        // Retrofit2 라이브러리를 이용해서
        // 서버에서 json 데이터를 읽어와서 Item 객ㅊ로 곧바로 패싱

        // http://webserver.dothome.co.kr/04Retrofit/board.json
        // http://webserver.dothome.co.kr : 서버주소 ( baseUrl == 베이스 주소 == 호스팅 주소 )
        ///   04Retrofit/board.json  : 특정 파일 경로

        //레트로핏이란?
        // 작업단계 총 5가지
        //1.Retrofit 객체 만들기 (알랏 다이알로그처럼 만들기 빌더 ! )
        Retrofit.Builder builder = new Builder();
        builder.baseUrl("http://webserver.dothome.co.kr"); // 단!!!! 꼭 http 앞에 써야함 !!!
                                                            // 그리고 마지막 / 는 ~~~~~~~TODO
        // 레트로핏의 좋은점은, 데이터 가져와서지손이랑 이어서 ~~파싱하는게 좋다고 했잖아?
        // 서버에서 받는ㄷ json이 아닌 그냥 글씨, xml 일 수도 있잖아?
        // 그냥 글씨 덩어리 -> 스칼라스 컨버터 사용해서 글씨만 사용
        // xml -> simple xml 컨버터 사용

        builder.addConverterFactory(GsonConverterFactory.create());// gson으로 제이슨을 자동으로 파싱하는 녀석을 설정했다고 생각하자 ~
                                                                    // 이 설정을 하면 통신의 결과(json) 문자열을
                                                                    // 자동으로 파싱하여 객체로 자동으로 얻을 수 있음 !!!
                                                                    //GsonConverterFactory  여기가 글자면 스칼라. xml 이면 심플xml

        Retrofit retrofit = builder.build(); // 이제 빌드~
        // 근데 이제 우리는 레트로 객체한테 GET/POST 몰로 할지 알려주고
        // 서버 들어가서 파일 경로 들어가서 여기서 파일 파싱해와~
        //하고 알려줘야 함.
        // 근데 이걸 서식으로 줌 !!
        // 정리 -> 레트로핏의 동작을 정의하는 인터페이스를 설계해야 함.
        // 존나 재밌당 ㅜㅜ
        // 인터페이스 ? 기능은 없지만 규격을 줄 수 있는 표준화 ? 정도로 이해 ~

        //java class-> interface 생성. 이름은 암거나 . 우리는 RetrofitService.interface

        //작업단계 2. Retrofit 의 동작을 정의하는 인터페이스 설계
        //RetrofitService.java

        //작업단계 3. 2단계에서 설계한
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        // 자 ,RetrofitService.class 설ㅖ 도면에 있는걸로  retrofit이 만들게 시켰어 !

        //작업단계 4. 위에서     추살메솓를 호ㅜㄹ히여 서버작업을 수행하는 기능을 가진 call아라는 객체 리턴받기
        Call<Item> call = retrofitService.getBoardJson();

        // 작업단계 5. 위 4단계에서 리턴받은 call 객체에게 네트워트 작업을 수행하도록 요청하자 !
        // call.execute()  -> 이건 동기식 방식. . . 그래서 이거 할때 메인스레드가 동작을 멈추고 있음.. 개구림 그래서 인씀
        //    call.enqueue(); 이거 씀
        // 정리하기
        // 약간 비행기 활주로를 생각해바
        // 활주로에 순서대로 서서 날라간다고 생각하자 ! 큐 방식으로 FiFO 인 순차적 방식.으로~
        call.enqueue(new Callback<Item>() {
            @Override   //성공시
            public void onResponse(Call<Item> call, Response<Item> response) {
                //자, 파라미터로 전달 된 두번째꺼 응답객체 response 가 결과데이터를 가지고 있음.
                // 그 결과는 바로 Item 으로 ㅂㄷ으면 됨.
                // json 을 자동으로 Gson 파싱하여 Item 객체로 결과를 받음
                // 헤더 == 데이터 정보 , 바디 == 데이터들
                Item item = response.body();
                binding.tv.setText( item.title+","+item.msg );
            }

            @Override      //실패시
            public void onFailure(Call<Item> call, Throwable t) {
                    binding.tv.setText(" call.enqueue 실패");
            }
        });

    }

}//메인