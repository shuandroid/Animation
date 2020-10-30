package com.chendroid.animation.ui.main.activity

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.chendroid.animation.R
import com.chendroid.animation.utils.ViewUtils
import com.chendroid.animation.view.DragConstraintLayout
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.activity_drag.*

/**
 * author: @zhaochen
 * time: 2020/10/29 7:48 PM
 * description: 仿即刻拖箱拖拽动画
 */
class DragActivity : Activity() {

    private lateinit var contentView: DragConstraintLayout

    private lateinit var avatarView: SimpleDraweeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_drag)

        contentView = drag_content_view
        avatarView = avatar_view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        avatarView.setOnClickListener {
            Toast.makeText(applicationContext, "点击头像", Toast.LENGTH_SHORT).show()
        }
        contentView.setTargetView(avatarView)


    }

}