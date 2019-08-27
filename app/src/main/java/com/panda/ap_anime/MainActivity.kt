package com.panda.ap_anime

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import carbon.view.View
import carbon.widget.FrameLayout
import carbon.widget.TextView
import com.panda.ap_anime.c_anmiations_utils.CAnmiation
import com.panda.ap_anime.c_anmiations_utils.CAnmiationHelper

class MainActivity : AppCompatActivity() {

    @BindView(R.id.txt1)
    internal var textView1: TextView? = null
    @BindView(R.id.text2)
    internal var textView2: TextView? = null
    @BindView(R.id.text3)
    internal var textView3: TextView? = null
    @BindView(R.id.frame1)
    internal var frame1: FrameLayout? = null
    @BindView(R.id.frame2)
    internal var frame2: FrameLayout? = null
    @BindView(R.id.frame3)
    internal var frame3: FrameLayout? = null
    private var previous_T: TextView? = null
    private var previous_F: FrameLayout? = null
    private var tap_previous: FrameLayout? = null

    private var cAnmiation: CAnmiation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initView()
    }

    private fun initView() {
        cAnmiation = CAnmiation(this, 150)
        previous_T = null
        previous_F = null
        tap_previous = null
    }



}
