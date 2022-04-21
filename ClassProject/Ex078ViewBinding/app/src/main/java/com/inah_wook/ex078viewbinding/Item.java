package com.inah_wook.ex078viewbinding;

import java.util.ArrayList;

public class Item {

    int imgId;      // R.drawble.moana
    String title;       //"모아나"

    public Item(int imgId, String title) {
        this.imgId = imgId;
        this.title = title;
    }

    // 리사이클러가 보여줄 대량의 데이터
    ArrayList<Item> items = new ArrayList<>();
    MyAdapter adapter;





}
