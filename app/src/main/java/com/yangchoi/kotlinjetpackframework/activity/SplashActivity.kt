package com.yangchoi.kotlinjetpackframework.activity

import android.content.Intent
import com.yangchoi.kotlinjetpackframework.databinding.ActivitySplashBinding
import com.yangchoi.kotlinjetpackframework.model.SplashActivityModel
import com.yangchoi.lib_base.base.BaseActivity

/**
 * create by yangchoi
 * on 2021/7/7
 * describe: 启动页
 */
class SplashActivity : BaseActivity<SplashActivityModel, ActivitySplashBinding>() {


    override fun initView() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun initClick() {
    }

    override fun initData() {
    }

    override fun initVM() {
    }
}