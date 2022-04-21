package com.inah_wook.ex029recyclierview;

public class Item {

    //요기 아이템 뷰 하나에서 쓰일 데이터 값의 class 정의.

    String name;
    String message;

    public Item(String name, String message) {
        this.name = name;
        this.message = message;
    }

    //가급적 파라미터가 없는 생성자도 오버로딩 해놔라 ~
    public Item() {
    }
}
