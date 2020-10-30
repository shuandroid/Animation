package com.chendroid.animation.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chendroid.animation.R
import com.chendroid.animation.ui.main.activity.DragActivity
import kotlinx.android.synthetic.main.fragment_animation_test.*

/**
 * author: zhaoChen
 * time: 2020/10/29 6:28 PM
 * description: 动画测试的 fragment 类
 */
class AnimationTestFragment : Fragment() {


    companion object {
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): AnimationTestFragment {
            return AnimationTestFragment().apply {
                arguments = Bundle().apply {
                    // do something
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animation_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drag_button.setOnClickListener {
            Toast.makeText(context, "即刻头像拖拽动画", Toast.LENGTH_SHORT).show()
            startDragActivity()
        }
    }

    private fun startDragActivity() {
        startActivity(Intent(context, DragActivity::class.java))
    }

}