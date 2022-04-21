package com.inah_wook.ex091firebasechatting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.inah_wook.ex091firebasechatting.databinding.ActivityChatting2Binding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class ChattingActivity extends AppCompatActivity {

    ActivityChatting2Binding binding;
    String chattingRoomName = "chat";
    ArrayList<MessageItemVO> messageItemVOS = new ArrayList<>() ;
    ChatAdapter adapter;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatting2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //제목줄에 채팅방 이름과 닉네임 보이기 [ 보통은 상대방 이름이 나음 ]
        // 통신 테이블을 상대방과 1:1 방을 만들자

        // 여긴 예제니까 걍 암거나 쓰쟝
        getSupportActionBar().setTitle(chattingRoomName);
        getSupportActionBar().setSubtitle(G.nickname);

        //아답터 만들고 왔으면 붙이자 !
        adapter = new ChatAdapter(this,messageItemVOS);
        binding.recycler.setAdapter(adapter);


        binding.btn.setOnClickListener(v->clickSend());


        firebaseFirestore = FirebaseFirestore.getInstance();

        //'chat' 컬렉션의 데이터 변화가 생기는 것을 감지하자 !
        CollectionReference chatref = firebaseFirestore.collection(chattingRoomName);

        chatref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

            // QuerySnapshot value 내가 신호보내면 사진 챠챠챠챡 찍어서 요기에 Document 스냅샷들이 찍혀있음 ~
                // 이걸 받아와야 함 ~
               //  for(DocumentSnapshot snapshot:value.getDocuments()){ // getDocuments는그 동안 저장된 스냅샷들이 다 저장되어서 새로 바뀐거 + 그 전에 보낸 말풍선들이 옴 . .
                    for(DocumentChange documentChange :value.getDocumentChanges()){ // 그래서 이렇게 ㅂㅏ뀐거만 가져오라고 해야 함.

                        DocumentSnapshot snapshot = documentChange.getDocument();

                    Map<String,Object> msg = snapshot.getData();
                    String name = msg.get("name").toString();
                    String message= msg.get("message").toString();
                        String time= msg.get("time").toString();
                    String proUrl= msg.get("profileUrl").toString();


                    // 이제 읽어들인 메세지를 리사이클러뷰가 보여준느 메세지아이템개ㄱ체

                    messageItemVOS.add(new MessageItemVO(name,message,time,proUrl));
                    adapter.notifyItemInserted(messageItemVOS.size()-1); // 뒤에 토도독 붇으니까 ~
                        // 쭉쭉 채팅하고 화면에서 채팅 넘어가면 자동 스크롤 되야 하잖아?
                        //리사이클러뷰의 스크롤 위치를 마지막 position 으로 자동 스크롤 ~
                  //  binding.recycler.scrollToPosition(messageItemVOS.size()-1);
                        binding.recycler.scrollToPosition(adapter.getItemCount()-1);// 이렇게 하면 경우에 따라서 뷰타입에 따라 배치안되는 애가 있음. 이걸로 숨김기능도 구형 가능 ~ 나중에 여쭤보기
                }


            }
        });

    }

    private void clickSend() {
        //아까 프로필 저장하득이 채팅방 저장
        // 닉네임, 프로필 이미지, 메세지, 작성시간을 firebase에 저장
        // 보기 편하게
        String nickName = G.nickname;
        String msg  =  binding.et.getText().toString();
        String profileUrl= G.profileUrl.toString();
        Calendar calendar = Calendar.getInstance();
        String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);

        //firebase firestorage DB에 저장된 데이터들을맴버로 가지는 MessageItemVO 객체를 통으로 보내자 ~
        MessageItemVO messageItemVO = new MessageItemVO(nickName,msg,time,profileUrl);
        //'chat'이라는 채팅방 이름으로 collection을 만들어서 messaeItemVo 객체를 통으로 저장
        // 단 ! Document 이름이 랜덤이면 저장된 순서가 바뀔 수 있기 때문에, 이름을 시간으로 설정해야 함 !

        firebaseFirestore.collection(chattingRoomName).document("MSG_"+System.currentTimeMillis()).set(messageItemVO);

        //전송 했지~~ 다음 입력을 위해 edit Text의 글씨를 지우쟝~
        binding.et.setText("");
        // 잠깐 ! 끝내기 전에 !
        // 키패드의 전송버튼 누르면 키패드를 안보이도록 내리기
        // getSystemService(Context.INPUT_METHOD_SERVICE); 에서 왜 Context.을 쓰냐 !? -> 녹음 5:00:00
        // 자 여기서 원래 에딧 텍스트가 토큰을 가지고 있었잖아?
        //그래서 에딧텍스트의
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);


    }
}