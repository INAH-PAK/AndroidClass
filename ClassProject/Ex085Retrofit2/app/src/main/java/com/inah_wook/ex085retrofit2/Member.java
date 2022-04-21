package com.inah_wook.ex085retrofit2;

public class Member {
    // json 객체 하나의 정보를 가진 클래스
    //http://webserver.dothome.co.kr/04Retrofit/members.json
    // json의 식별 글씨와 같은 이름의 맴버변수를 선언해야 함 !!!!!!

    int no;
    String name;
    int age ;
    String address;
    Boolean favorite;

    public Member() {

    }


    public Member(int no, String name, int age, String address, Boolean favorite) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.address = address;
        this.favorite = favorite;
    }
}
