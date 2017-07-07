package sky.kr.co.lockscreenbigenglish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.common.collect.Lists;

import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

/**
 * Created by SKY on 2017-07-07.
 * 인트로 클래스
 * 설명 : 인트로에서 하는일..
 * 1. 푸시키 가져오기
 *
 */

public class IntroduceActivity extends AppCompatActivity {

    ScrollerViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

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
        springIndicator.setViewPager(viewPager);

    }

    private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3", "4");
    }

    private List<Integer> getBgRes(){
        return Lists.newArrayList(R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4);
    }

    public void onPageScrollStateChanged(int state) {
        Log.e("kjhk", ""+state);

    }



}
