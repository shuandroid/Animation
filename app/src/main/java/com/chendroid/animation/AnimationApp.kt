package com.chendroid.animation

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * author: @zhaochen
 * time: 2020/10/29 8:12 PM
 * description:
 */
class AnimationApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}