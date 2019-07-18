package com.panda.ap_anime

import android.app.Application
import org.jetbrains.annotations.NotNull
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())


        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(@NotNull element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })

    }
}
