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

    @OnClick(R.id.tap1)
    fun onNews(view: FrameLayout) {

        //   AnmiteView(textView1, frame1, view)

    }

    @OnClick(R.id.tap2)
    fun onExplore(view: FrameLayout) {

        //  AnmiteView(textView2, frame2, view)

    }

    @OnClick(R.id.tap3)
    fun onActivity(view: FrameLayout) {

        //   AnmiteView(textView3, frame3, view)

    }

/*
    internal fun AnmiteView(textView: TextView?, frame: FrameLayout?, tap: FrameLayout)
    {
        if (tap_previous != null && tap_previous!!.id == tap.id) {
            return
        }
        tap_previous = tap
        if (previous_F != null)
            cAnmiation?.AppllyAnmiation(CAnmiationHelper.HIDE_VIEW_GONE, previous_F!!)
            {
                previous_T!!.setTextColor(Color.GRAY)

                previous_T = textView
                previous_F = frame
                cAnmiation!!.AppllyAnmiation(CAnmiationHelper.SHOW_VIEW_VISIBLE, frame) {
                    textView!!.setTextColor(Color.WHITE)
                    //

                    //
                }
            }
        else {
            previous_T = textView
            previous_F = frame
            cAnmiation!!.AppllyAnmiation(CAnmiationHelper.SHOW_VIEW_VISIBLE, frame)
            {
                textView!!.setTextColor(Color.WHITE)
                //

                //
               }
            }


    }
    */
}
