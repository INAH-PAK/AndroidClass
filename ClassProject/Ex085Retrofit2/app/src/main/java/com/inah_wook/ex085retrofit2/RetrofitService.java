package com.inah_wook.ex085retrofit2;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitService {
    // 1. 단순하게 GET방식으로 json 문자열을 읽어오는 기능 추상 메소드~~~~
    // 메소드 aaa() 를 만들자 ~
    // 근데 얘가 몰 하는지 모르잖아?
    //이 함수가 몰 하는 앤지 주석을 통해서 알려줌.
    // @ == 어노테이션 !!

    // 우린 GET방식으로 하고 싶어 ! 그럼
    // 근데 이미 우리가 만든 retrofit 객체는 서버 주소를 가지고 있어서
    // 얘한테는 읽어올 파일의 경로만 줌

    // call == 우리가 받고싶은 자료형인 Item 객체를
    // 자동으로 가져와서 json 형식값들을 넣어서 주는 애임 !

    @GET("/04Retrofit/board.json")
    Call<Item> getBoardJson();

    //2. 경로의 이름을 위 1처럼 고정하지 않고 파라미터로 전달받아서 유연하게 변경하자
    // @Path
    @GET("/{aaa}/{bbb}")
    Call<Item> getBoardJsonByPath(@Path("aaa") String path,@Path("bbb")  String fileName);

    //3. GET방식으로 값을 서버에 전달하는 방식을 학슺 ~
    @GET("/04Retrofit/getTest.php") // getTest.php 이 파일로 만들어서 보낼꼬얌
    //  Call<Item> getMethodTest(String title, String massege);
    // 이렇게 보내면 서버는 받는 값이 스트링 두개니까 모가 몬지 모르잖아?
    Call<Item> getMethodTest(@Query("title")String title, @Query("msg")String massege);
    // 서버에서 이 값은 key고 이게 이 key에 대한 value 다 ~ 하고 알려주는게 쿼리 `~~~

//4. GET 방식으로 서버에 전달 하기 ~ 2번 +3번 섞기 ~~ : Path + Query
    @GET("/04Retrofit/{aaa}")
    Call<Item> getMrthodTest2(@Path("aaa") String fileName , @Query("title") String title , @Query("msg") String msg);

    //5. GET방식으로 데이터를 보낼때, 한 방에 보내는 방식 -> Map 방식의 Collection 사용
    @GET("/04Retrofit/getTest.php")
    Call<Item> getMrthodTest3(@QueryMap Map<String , String > datas);

 // ------------------------------------------[POST]--------------------------------------------------------------

    //6. POST 방식읋 데이터를 전달 :  Item 객체를 폼으로 보내기 ! -> Retrofit 이 권장


        //포스트 방식으로 postTest.php 실행해라. 데이터는 item 이얌.
    // @Body : 데이터를 객체로 보냄. -> 네트워크는 객체를 받지 못함. 근데 어케 보냄?
    // 사실 객체를 전달하는 레트로핏이 자동으로 ㄱㄱ체를 json 문자열로 변과시켜서 서버로전송함 ~~!!!
    // 그럼 php는 받을 때 json을 받음. -> 비쥬얼 코드 ㄱㄱㄱ    Call<Item> postMethodTest(@Body Item item);
        @POST("/04Retrofit/postTest.php")
        Call<Item> postMrthodTest(@Body Item item);

        //이렇게 보내면

//7. POST 방식을 GET 방식의 @Query 처럼 보내는 방식
    // 단 !
    // @Field 쓰려면 -> @FormUrlEncoded 무조건 있어야 함. 이 뜻은 명시적으로 이걸 하겠다고 한 거임,
    // 왜?
    // 원래 Retrofit은 객체로 보내길ㄱ 권장하는데 우리는 개겣로 보내면 너무 지저분해서 하나하나 보내고 싶은고잖아?
    // 그래서 명시적으로 이거 하겠다 ! 고 말한거임.
    @FormUrlEncoded
    @POST("/04Retrofit/postTest2.php")
    Call<Item> postMethodTest2(@Field("title") String title,@Field("msg") String massege); //통으로 안보내고 하나하나씩 보내고 싶음~

// 8. json Array 받기
    // 데이터 가져오면 되서 속도빠른 GET 으로 ~
    @GET("/04Retrofit/members.json")
    Call<ArrayList<Member>> getJsonArray();


//9. 서버의 응답 결과를 단순히 String으로 받기 ( -> Gson 사용 X)
    @GET("/04Retrofit/members.json")
    Call<String> getJsonArrayToString();




}
