package com.panda.ap_anime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.ImageView;
import com.panda.ap_anime.c_anmiations_utils.CAnmiation;
import com.panda.ap_anime.c_anmiations_utils.CAnmiationHelper;

class SplashActiviity extends AppCompatActivity {
    @BindView(R.id.main_image)
    ImageView imageView;
    CAnmiation cAnmiation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
        DoSplash();
    }

    private void DoSplash() {
        cAnmiation.AppllyAnmiation(CAnmiationHelper.SHOW_VIEW_VISIBLE, imageView, () ->
        {
            startActivity(new Intent(this,MainActivity.class));
        });
    }
    private void initView()
    {
        cAnmiation = new CAnmiation(this,CAnmiation.FixedDureation);
    }
}
