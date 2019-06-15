package com.panda.ap_anime;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.view.View;
import carbon.widget.FrameLayout;
import carbon.widget.TextView;
import com.panda.ap_anime.c_anmiations_utils.CAnmiation;
import com.panda.ap_anime.c_anmiations_utils.CAnmiationHelper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt1)
    TextView textView1;
    @BindView(R.id.text2)
    TextView textView2;
    @BindView(R.id.text3)
    TextView textView3;
    @BindView(R.id.frame1)
    FrameLayout frame1;
    @BindView(R.id.frame2)
    FrameLayout frame2;
    @BindView(R.id.frame3)
    FrameLayout frame3;
    private TextView previous_T;
    private FrameLayout previous_F;
    private FrameLayout tap_previous;

    private CAnmiation cAnmiation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        cAnmiation = new CAnmiation(this, 150);
        previous_T = null;
        previous_F = null;
        tap_previous = null;
    }

    @OnClick(R.id.tap1)
    public void onNews(FrameLayout view) {

        AnmiteView(textView1, frame1, view);

    }

    @OnClick(R.id.tap2)
    public void onExplore(FrameLayout view) {

        AnmiteView(textView2, frame2, view);

    }

    @OnClick(R.id.tap3)
    public void onActivity(FrameLayout view) {

        AnmiteView(textView3, frame3, view);

    }


    void AnmiteView(TextView textView, FrameLayout frame, FrameLayout tap) {
        if (tap_previous != null && tap_previous.getId() == tap.getId()) {
            return;
        }
        tap_previous = (FrameLayout) tap;
        if (previous_F != null)
            cAnmiation.AppllyAnmiation(CAnmiationHelper.HIDE_VIEW_GONE, previous_F, new CAnmiation.CaCallBack() {
                @Override
                public void OnAnmiationFinish() {
                    previous_T.setTextColor(Color.GRAY);

                    previous_T = textView;
                    previous_F = frame;
                    cAnmiation.AppllyAnmiation(CAnmiationHelper.SHOW_VIEW_VISIBLE, frame, () -> {
                        textView.setTextColor(Color.WHITE);
                        //

                        //
                    });

                }
            });
        else {
            previous_T = textView;
            previous_F = frame;
            cAnmiation.AppllyAnmiation(CAnmiationHelper.SHOW_VIEW_VISIBLE, frame, () -> {
                textView.setTextColor(Color.WHITE);
                //

                //
            });
        }


    }
}
