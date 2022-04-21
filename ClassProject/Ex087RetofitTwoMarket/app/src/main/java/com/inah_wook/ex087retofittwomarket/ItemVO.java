package com.inah_wook.ex087retofittwomarket;

import com.google.gson.annotations.SerializedName;

public class ItemVO {
    // VO :  접두어의 한 종류, 일단 VO는 데이터(값) 만 저장하는 목적 클래스들, , ,
    // VO ( value of object)
    // DAO,ATO 등등 있음

    // retrosit으로 쓸거기 때문에 꼭 컬럼 명과 일치해야 함!!!
     int no;
        String name;
        String title;
    // 만약 이 변수들의 이름을 바꾸고 싶으면
    @SerializedName("msg")
    String message;
    String file;

    public ItemVO() {
    }

    public ItemVO(int no, String name, String title, String message,String file) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.message = message;
        this.file = file;
    }
}
