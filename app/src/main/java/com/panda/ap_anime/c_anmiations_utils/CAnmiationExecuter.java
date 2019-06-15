package com.panda.ap_anime.c_anmiations_utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

/**
 * main Role Set Of Functions To Apply Selected Animation
 */
class CAnmiationExecuter
{

     static void HideView(final View view, final CAnmiation.CaCallBack callBack,int Duration)
     {


        view.animate()
                .translationY(0)
                .alpha(0.0f).setDuration(Duration)
                .setListener(new AnimatorListenerAdapter() {
                                 @Override
                                 public void onAnimationEnd(Animator animation) {
                                     super.onAnimationEnd(animation);
                                     view.setVisibility(View.GONE);
                                     callBack.OnAnmiationFinish();
                                 }
                             }
                );
    }

     static void ShowView(final View view, final CAnmiation.CaCallBack callBack, int Duration)
    {
               view.animate()
                .translationY(0)
                .alpha(1f)
                .setDuration(Duration)
                .setListener(new AnimatorListenerAdapter() {
                                 @Override
                                 public void onAnimationEnd(Animator animation)
                                 {
                                     super.onAnimationEnd(animation);
                                     view.setVisibility(View.VISIBLE);
                                     callBack.OnAnmiationFinish();
                                 }
                             }
                );


    }
}
