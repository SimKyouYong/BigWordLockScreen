package sky.kr.co.lockscreenbigenglish;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import sky.kr.co.lockscreenbigenglish.common.CommonUtil;


/**
 * Created by SKY on 2017-07-07.
 * 인트로 클래스
 * 설명 : 인트로에서 하는일..
 * 1. 푸시키 가져오기
 *
 */

public class IntroActivity extends AppCompatActivity {
    CommonUtil dataSet = CommonUtil.getInstance();
    ImageView introimg;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);
        init();
    }

    public void init(){
        introimg	=	(ImageView)	findViewById(R.id.intro_image);
        MainMove();

    }

    private void MainMove(){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, IntroduceActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }
}
