package com.panda.ap_anime.c_anmiations_utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.Log
import android.view.View

import io.reactivex.annotations.Nullable
import io.reactivex.disposables.CompositeDisposable

class CAnmiation(private val context: Context, private val Duration: Int) : LifecycleObserver {
    private val disposable: CompositeDisposable

    init {
        disposable = CompositeDisposable()
        if (context is LifecycleOwner) {
            (context as LifecycleOwner).lifecycle.addObserver(this)

            Log.v("Life Cycle Yea", "ATTACHED")
        }
    }

    interface CaCallBack {
        fun OnAnmiationFinish()
    }

    fun AppllyAnmiation(ANMIType: Int, view: View, callBack: CaCallBack) {
        when (ANMIType) {
            CAnmiationHelper.HIDE_VIEW_GONE -> CAnmiationExecuter.HideView(view, callBack, Duration)
            CAnmiationHelper.SHOW_VIEW_VISIBLE -> CAnmiationExecuter.ShowView(view, callBack, Duration)
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onAttach() {
        Log.v("ATTACHED", "ATTACHED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onDetech() {
        Log.v("Detached", "ATTACHED")
        /*
        view is not ready to handel api calls so cancel them
         */
        disposable.clear()
    }

    companion object {
        val FixedDureation = 350
    }
}
