package com.yangchoi.lib_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.FrameLayout

/**
 * create by yangchoi
 * on 2021/7/2
 * describe:自定义FrameLayout 继承至 FragmentLayout,
 * 主要作用于在fragment中使用android:fitsSystemWindows="true"属性时只有第一个fragment有效果的情况
 */
class WindowInsetsFrameLayout : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun dispatchApplyWindowInsets(insets: WindowInsets): WindowInsets {
        try {
            if (!insets.isConsumed){
                val count = childCount
                for (i in 0 until count){
                    val freshInsets = WindowInsets(insets)
                    getChildAt(i).dispatchApplyWindowInsets(freshInsets)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return insets
    }
}