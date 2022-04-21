package com.mrhi2022.ex030recyclerview2;

public class Item {
    String name;      //"루피"
    String message;   //"해적단 선장"
    int profileId;    //R.drawable.crew_sanji
    int imageId;      //R.drawable.gb_img01

    public Item(String name, String message, int profileId, int imageId) {
        this.name = name;
        this.message = message;
        this.profileId = profileId;
        this.imageId = imageId;
    }

    public Item() {
    }
}
