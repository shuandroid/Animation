package com.chendroid.animation.utils

import android.app.Service
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Outline
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import java.lang.Exception


object ViewUtils {

    /**
     * getDrawingCache 被抛弃，使用 PixelCopy
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchBitmapFromView(view: View, window: Window, callback: (Bitmap) -> Unit) {

        if (window == null) {
            return
        }

        if (view.width <= 0 || view.height <= 0) {
            throw Exception("view 的宽和高，不能小于 0")
        }

        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        val locationOfViewInWindow = IntArray(2)
        view.getLocationInWindow(locationOfViewInWindow)

        try {
            PixelCopy
                .request(
                    window, Rect(
                        locationOfViewInWindow[0],
                        locationOfViewInWindow[1],
                        locationOfViewInWindow[0] + view.width,
                        locationOfViewInWindow[1] + view.height
                    ),
                    bitmap, { copyResult ->
                        if (copyResult == PixelCopy.SUCCESS) {
                            callback(bitmap)
                        }
                    }, Handler()
                )
        } catch (e: Exception) {
            Log.i("zc_test", "exception is $e")
            e.printStackTrace()
        }
    }


    /**
     * 判断当前 event 是否发生在特定的 view 里面
     */
    fun isTouchEventInTargetView(event: MotionEvent, targetView: View): Boolean {

        val eventX = event.rawX
        val eventY = event.rawY

        // 获取当前 view 在屏幕的位置
        val location = IntArray(2)
        targetView.getLocationOnScreen(location)

        val targetX = location[0]
        val targetY = location[1]

        Log.i("zc_test", "isTouchEventInTargetView x is $targetX, and y is $targetY ")

        val radius = (targetView.right - targetView.left) / 2

        val centerX = targetX + radius
        val centerY = targetY + radius

        // event 和 中心点坐标的差值
        val offsetX = Math.abs(eventX - centerX)
        val offsetY = Math.abs(eventY - centerY)

        val offsetRadius =
            Math.sqrt(Math.pow(offsetX.toDouble(), 2.0) + Math.pow(offsetY.toDouble(), 2.0))

        if (offsetRadius > radius) {
            Log.i("zc_test", "inAvatarViewInterval return false ")

            return false
        }

        Log.i("zc_test", "inAvatarViewInterval return true ")

        return true
    }

    // 开启震动效果
    fun startVibrateEffect(context: Context) {

        val vibrator = context.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator

        // 需要添加权限  uses-permission
        vibrator.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrate(VibrationEffect.createOneShot(30L, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrate(30L)
            }
        }
    }


    /**
     * 为当前 view 设置四周圆角
     * radiusPx ， 设置圆角的半径，单位是 px
     */
    @JvmStatic
    fun setRoundCorner(roundView: View, radiusPx: Int) {
        roundView.clipToOutline = true
        roundView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, radiusPx.toFloat())
            }
        }
        // 刷新
        roundView.invalidateOutline()
    }
}