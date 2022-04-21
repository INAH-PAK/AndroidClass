package com.inah_wook.ex085retrofit2;

public class Item {
    // 이 클래스는 우리가 가져올 json 파일의 데이터를 가진 객체로 만들고 싶어서 만든고얌.
    // 이 맴버 변수 이름들은 json 의 식별자면과 완전히 일치해야 함 !
    String title;
    String msg;

    public Item(String title, String msg) {
        this.title = title;
        this.msg = msg;
    }

    public Item() {
    }
}
