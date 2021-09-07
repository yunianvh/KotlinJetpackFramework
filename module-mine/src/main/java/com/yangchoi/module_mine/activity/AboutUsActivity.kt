package com.yangchoi.module_mine.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.yangchoi.lib_base.base.BaseActivity
import com.yangchoi.lib_public.utils.ARouterUtil
import com.yangchoi.module_mine.databinding.ActivityAboutUsBinding
import com.yangchoi.module_mine.viewmodel.AboutUsActivityVM

/**
 * create by yangchoi on 2021/9/7
 * describe:
 */
@Route(path = ARouterUtil.AboutUsActivity)
class AboutUsActivity : BaseActivity<AboutUsActivityVM,ActivityAboutUsBinding>() {
    override fun initView() {
    }

    override fun initClick() {
    }

    override fun initData() {
    }

    override fun initVM() {
    }
}