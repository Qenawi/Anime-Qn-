package com.panda.ap_anime.c_anmiations_utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;
import android.view.View;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import org.jetbrains.annotations.NotNull;

public class CAnmiation implements LifecycleObserver
{
    private Context context;
    private CompositeDisposable disposable;
private int Duration;
    public final static  int FixedDureation=350;
    public CAnmiation(Context C, int Duration  )
    {
        this.context = C;
        this.Duration=Duration;
        disposable = new CompositeDisposable();
        if (C instanceof LifecycleOwner) {
            ((LifecycleOwner) C).getLifecycle().addObserver(this);

            Log.v("Life Cycle Yea", "ATTACHED");
        }
    }

    public interface CaCallBack
    {
        void OnAnmiationFinish();
    }

    public void AppllyAnmiation(int ANMIType, View view, CaCallBack callBack)
    {
        switch (ANMIType) {
            case CAnmiationHelper.HIDE_VIEW_GONE:
                CAnmiationExecuter.HideView(view,callBack,Duration);
                break;
            case CAnmiationHelper.SHOW_VIEW_VISIBLE:
                CAnmiationExecuter.ShowView(view,callBack,Duration);

                break;

        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach()

    {
        Log.v("ATTACHED", "ATTACHED");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetech() {
        Log.v("Detached", "ATTACHED");
        /*
        view is not ready to handel api calls so cancel them
         */
        disposable.clear();
    }
}
