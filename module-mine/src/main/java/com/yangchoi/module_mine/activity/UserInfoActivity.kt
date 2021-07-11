package com.yangchoi.module_mine.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.yangchoi.lib_base.base.BaseActivity
import com.yangchoi.lib_public.utils.ARouterUtil
import com.yangchoi.module_mine.databinding.ActivityUserInfoBinding
import com.yangchoi.module_mine.viewmodel.UserInfoActivityVM

/**
 * Created by yangchoi on 2021/7/11
 * describe: 个人信息
 */
@Route(path = ARouterUtil.UserInfoActivity)
class UserInfoActivity : BaseActivity<UserInfoActivityVM,ActivityUserInfoBinding>() {

    override fun initView() {
    }

    override fun initClick() {
    }

    override fun initData() {
    }

    override fun initVM() {
    }
}