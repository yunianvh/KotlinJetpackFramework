package com.yangchoi.lib_base.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV

/**
 * Created on 2021/6/25
 * describe:
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        //初始化mmkv组件
        MMKV.initialize(context)
        //初始化ARouter组件
        initARouter()
    }
    private fun initARouter(){
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }
    companion object{

        @JvmStatic
        lateinit var context: Context
            private set

    }
    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}