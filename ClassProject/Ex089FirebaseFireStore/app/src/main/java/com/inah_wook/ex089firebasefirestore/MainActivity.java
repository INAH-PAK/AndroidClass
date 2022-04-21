package com.inah_wook.ex089firebasefirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.inah_wook.ex089firebasefirestore.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ///firebaseCloudeDataBase ->  NOSQL 방식 ( RDBMS 처럼 테이블 형식으로 저장되지 않는 DB)
    // 계층구조 자체가 마인드맵처럼 엄청 퍼져있는 느낌

    // 1. Firebase와 앱 연동
    // -- 가이드 문서 따라가기
    //https://console.firebase.google.com/

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(v -> saveData());
        binding.btnLoad.setOnClickListener(v -> loadData());
        binding.btnRealLoad.setOnClickListener(v -> raelTimeLoadData());
        binding.btnSearch.setOnClickListener(v -> SearchMap());


    }

    private void SearchMap() {
        // "member" 컬렉션에서 특정 필드 값ㅇ 해달하는 데이터들만 가져오기
        String name = binding.etName.getText().toString();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference memberRef = firebaseFirestore.collection("member");

        memberRef.whereEqualTo("name", name).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                // 같은 이름이 여러개일 수 ㅇㅆ기 때문에
                StringBuffer buffer = new StringBuffer();
                for (QueryDocumentSnapshot snapshot : value) {
                    Map<String, Object> person = snapshot.getData();
                    int age = Integer.parseInt(person.get("age").toString());
                    String address = person.get("address").toString();
                    String name = person.get("name").toString();

                    buffer.append(name + "");
                }

            }
        });

    }

    private Object raelTimeLoadData() {
        // 데이터 변화를 실시간으로 감지하고ㅡ
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference memberRef = firebaseFirestore.collection("member");
        memberRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                // value 안에는 여러개의 도큐먼트 스냄샷만큼 있음 == 뭐리 스냅샷s
                StringBuffer buffer = new StringBuffer();
                for (DocumentSnapshot snapshot : value) {
                    Map<String, Object> person = snapshot.getData();
                    int age = Integer.parseInt(person.get("age").toString());
                    String address = person.get("address").toString();
                    String name = person.get("name").toString();

                    buffer.append(name + "");
                }

            }
        });


    }



    private void loadData() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();// 나중에 맴버변수로 만들어놔도 됨
         CollectionReference memberRef = firebaseFirestore.collection("member");
         Task<QuerySnapshot> task =  memberRef.get();
         task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(@NonNull Task<QuerySnapshot> task) {
                 if(task.isSuccessful()) {
                     QuerySnapshot snapshots = task.getResult();

                 // 결과 데이터가 여러개인 경우가 많음. ==document가 여러개라서.
                 for(DocumentSnapshot snapshot : snapshots ){
                     Map<String,Object> person = snapshot.getData();
                     String name = person.get("name").toString();
                     int age = Integer.parseInt(person.get("age").toString());
                     String address = person.get("address").toString();

                     binding.tv.append(name +"");


                 }
             }}
         });


    }

    private void saveData() {
        String name = binding.etName.getText().toString();
        String age =binding.etAge.getText().toString();
        String add = binding.etAddress.getText().toString();

        Map<String,Object> person = new HashMap<>();
        person.put("pp",name);
        person.put(33,age);
        person.put("seoul",add);


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        // 자 시작 !
        // 기존 DB의 테이블명 처럼 사용되는 것 == Collerction
        // "people" 이라느 ㄴ이름으로 컬렉션 만들고참조객체 소환 ==아까 레퍼런스 만든고처럼
        CollectionReference poepleRef = FirebaseFirestore.getInstance().collection("people");



        // poepleRef.document().set(person);
        // 단 이거 하면 성공여부 몰름

        // 테스크 고용

        //Task task = poepleRef.document().set(person);// 이렇게 하면 랜덤한 알 수 없는 이름의 도큐먼트로 저장 됨.
       // Task task = poepleRef.add(person);
        Task<DocumentReference> task = poepleRef.add(person);

//        //
//
//
//
//        task.addOnSuccessListener(new OnSuccessListener() {
//            @Override
//            public void onSuccess(Object o) {
//                Toast.makeText(MainActivity.this, " 성공 ", Toast.LENGTH_SHORT).show();
//            }
//        });

        //document( " 파일 이름 " )
        // firebaseFirestore.collection("member").document("1").set(person);
//        firebaseFirestore.collection("member").document("2").set(person);
//        firebaseFirestore.collection("member").document("3").set(person);

        // 날짜와 시간을 이용해서 순차적으로 저장 가능.
        // System.currentTimeMillis(); //1970년 1월1일0시0분0초부터 1ms 마다 값이 ++되는 애 있음
        firebaseFirestore.collection("member").document(System.currentTimeMillis()+"").set(person);

// 데이터를 굳이 해시맵으로 안만들고 값들을 맴버로 가진 데이터 객체(VO)를 한 번에 set 가능.
        // PersonVO.java
        PersonVO p = new PersonVO("inah",27,"cheonan");
        firebaseFirestore.collection("user").add(p);


//
    }
}