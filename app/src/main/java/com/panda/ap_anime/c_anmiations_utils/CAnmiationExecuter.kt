package com.panda.ap_anime.c_anmiations_utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView

/**
 * main Role Set Of Functions To Apply Selected Animation
 */
internal object CAnmiationExecuter {

    fun HideView(view: View, callBack: CAnmiation.CaCallBack, Duration: Int) {


        view.animate()
            .translationY(0f)
            .alpha(0.0f).setDuration(Duration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.GONE
                    callBack.OnAnmiationFinish()
                }
            }
            )
    }

    fun ShowView(view: View, callBack: CAnmiation.CaCallBack, Duration: Int) {
        view.animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(Duration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.VISIBLE
                    callBack.OnAnmiationFinish()
                }
            }
            )


    }
}
