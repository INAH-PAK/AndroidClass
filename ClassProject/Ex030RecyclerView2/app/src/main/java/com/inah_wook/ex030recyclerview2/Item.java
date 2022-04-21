package com.inah_wook.ex030recyclerview2;

public class Item {
    String name;    // "루피"
    String massage; //"해적단 선장
    int imageId;     //R.drawable.ch_sandi
    int profileId; // R.drawable.img01

    // 꼭 앞으로 생성자 만들땐 메개변수 없는것도 만들자 !
    public Item(String name, String massage, int imageId, int profileId) {
        this.name = name;
        this.massage = massage;
        this.imageId = imageId;
        this.profileId = profileId;
    }

    public Item() {
    }
}
