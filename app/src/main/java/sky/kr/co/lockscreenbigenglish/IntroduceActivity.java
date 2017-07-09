package sky.kr.co.lockscreenbigenglish;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.kr.sky.AccumThread;
import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;
import sky.kr.co.lockscreenbigenglish.common.Check_Preferences;
import sky.kr.co.lockscreenbigenglish.common.CommonUtil;
import sky.kr.co.lockscreenbigenglish.util.DeviceUuidFactory;
import sky.kr.co.lockscreenbigenglish.util.GuideFragment;

/**
 * Created by SKY on 2017-07-07.
 * 인트로 클래스
 * 설명 : 인트로에서 하는일..
 * 1. 푸시키 가져오기
 *
 */

public class IntroduceActivity extends AppCompatActivity {

    ScrollerViewPager viewPager;
    public static Button skip_btn;
    DeviceUuidFactory udid;
    Map<String, String> map = new HashMap<String, String>();
    AccumThread mThread;
    CommonUtil dataSet = CommonUtil.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        skip_btn = (Button) findViewById(R.id.skip_btn);
        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        // just set viewPager
        springIndicator.setViewPager(viewPager , skip_btn);


        udid = new DeviceUuidFactory(this);

        findViewById(R.id.skip_btn).setOnClickListener(btnListener);

    }
    //버튼 리스너 구현 부분
    View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressWarnings("deprecation")
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.skip_btn:

                    //지웟다 깔아도 같음
                    Log.e("SKY" , "udid :: " + udid.getDeviceUuid());
                    //bc4ab055-6aae-3cbd-8ea9-68e29ccf1a2b

                    map.put("url", dataSet.SERVER+"IN_JOIN.php");
                    map.put("udid", "" + udid.getDeviceUuid());
                    map.put("reg_id", "");      //아직 미구현...

		            //스레드 생성
		            mThread = new AccumThread(IntroduceActivity.this , mAfterAccum , map , 0 , 0 , null);
		            mThread.start();		//스레드 시작!!


                    //회원가입..
//
                    break;
            }
        }
    };
    Handler mAfterAccum = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if (msg.arg1  == 0 ) {
                String res = (String)msg.obj;
                Log.e("SKY" , "RESULT  -> " + res);
                res = res.trim();
                if(res.equals("true")){
                    Check_Preferences.setAppPreferences(IntroduceActivity.this , "Introduce_FLAG" , "true");

                    //가입성공
                    Intent intent = new Intent(IntroduceActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext() , "" + res , 0).show();
                    //가입성공
                    Intent intent = new Intent(IntroduceActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        }
    };
    private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3", "4");
    }

    private List<Integer> getBgRes(){
        return Lists.newArrayList(R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4);
    }
}
