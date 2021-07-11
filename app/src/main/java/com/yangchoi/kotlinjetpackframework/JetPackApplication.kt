package com.yangchoi.kotlinjetpackframework

import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.yangchoi.kotlinjetpackframework.R
import com.yangchoi.lib_base.base.BaseApplication

class JetPackApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initRefresh()
        ARouter.init(this)
    }
    private fun initRefresh() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { _, layout ->
            layout.setPrimaryColorsId(R.color.c333, R.color.black);
            ClassicsHeader(context)
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { _, _ ->
            ClassicsFooter(context).setDrawableSize(20F)
        }
    }
}