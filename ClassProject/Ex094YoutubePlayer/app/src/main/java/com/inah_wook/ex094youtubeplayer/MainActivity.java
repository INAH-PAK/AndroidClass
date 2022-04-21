package com.inah_wook.ex094youtubeplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.inah_wook.ex094youtubeplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity { // 여기서

    // 유투브 전용 플레이어 - 구글 개발자 사이트 가이드 참고 https://developers.google.com/youtube/android/player
    // Youtube Player Libraru

    // A. API 는 사이트에서 .zip 압축파일을 다운받아 압축해제해서
    //    내 프로젝트의 libs에 복사 붙여넣기 - 카카오맵이랑 똑같음
    // B. YouTubeAndroidPlayerApi.jar 을 스트럭쳐 모듈 에서
    // 프로젝트 모드에서 app ->lib -> YouTubeAndroidPlayerApi.jar 파일 추가

    // 회사 갔는데 회사가 홍보용 동영상을 유툽에 올렸을 때, 이걸 활용함~
    // 동영상은 서버비용이 쎄서 유툽 많이 사용

    // 그리고 이걸 쓰는건 리사이클러 뷰에서도 쓸 수 있음 ~
    // 복잡하긴 해도 쓸 수 있긴 함.
    // 글구 동영상 파일 자체로 하면 ,firebase로 하긴 하는데, firebase 는 5기가밖에 안되사 아깝

    // 글고 마켓 홍보 동영상에 유툽을 주소 넣으라고 하기도 함 ~

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // xml에 있는 youyubePlaterFragment 참조
        // FragmentManager -> 주의 !!!! 꼭 androidx 말고 옛날꺼인 fragmentManager 고용해야 함!!
        FragmentManager fragmentManager = getFragmentManager();
        YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment)fragmentManager.findFragmentById(R.id.youtube_fragment);
        YouTubePlayerFragment youTubePlayerFragment2 = (YouTubePlayerFragment)fragmentManager.findFragmentById(R.id.youtube_fragment2);

        // 유투브 플레이어 초기화 == 로딩하기 exoPlayer의 prepare 랑 비슷.  동영상 준비하기 ~
        // 첫 번째 식별자는 앱 안에 유툽 동영상이 여러개일까봐
        youTubePlayerFragment.initialize("first", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
              // 유투브 비디오의 아이디 지정하기 - URL이 아님!!!
                // ID : 유투브 사이트에서 동영상 페이지의 URL에서 끝에 v= 옆의 식별글씨
                youTubePlayer.loadVideo("RS-H7y6k-vw");



            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MainActivity.this, "동영상 불러오기 실패 " + youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
                Log.i("fffffffffff",youTubeInitializationResult.toString() );
            }
        });

        youTubePlayerFragment2.initialize("second", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                // 유투브 비디오의 아이디 지정하기 - URL이 아님!!!
                // ID : 유투브 사이트에서 동영상 페이지의 URL에서 끝에 v= 옆의 식별글씨
                youTubePlayer.loadVideo("RS-H7y6k-vw");


            }


            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MainActivity.this, "동영상 불러오기 실패 " + youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
                Log.i("fffffffffff",youTubeInitializationResult.toString() );
            }
        });

            binding.youtubeThum.initialize("thum", new YouTubeThumbnailView.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    youTubeThumbnailLoader.setVideo("RS-H7y6k-vw");
                }

                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });

    }
}