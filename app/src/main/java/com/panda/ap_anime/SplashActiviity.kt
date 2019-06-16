package com.panda.ap_anime

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import carbon.widget.ImageView
import com.panda.ap_anime.c_anmiations_utils.CAnmiation
import com.panda.ap_anime.c_anmiations_utils.CAnmiationHelper

internal class SplashActiviity : AppCompatActivity() {
    @BindView(R.id.main_image)
    var imageView: ImageView? = null
   lateinit var cAnmiation: CAnmiation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ButterKnife.bind(this)
        initView()
        DoSplash()
    }

    private fun DoSplash()
    {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
        finish()

    }

    private fun initView() {
        cAnmiation = CAnmiation(this, CAnmiation.FixedDureation)
    }
}
