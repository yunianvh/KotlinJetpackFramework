package com.yangchoi.module_project.ui.fragment

import com.alibaba.android.arouter.launcher.ARouter
import com.yangchoi.lib_base.base.BaseFragment
import com.yangchoi.lib_public.utils.ARouterUtil
import com.yangchoi.module_project.databinding.FragmentProjectBinding
import com.yangchoi.module_project.viewmodel.ProjectFragmentVM

/**
 * Created on 2021/6/23
 * describe:
 */
class ProjectFragment : BaseFragment<ProjectFragmentVM, FragmentProjectBinding>() {
    override fun initVM() {
    }

    override fun initView() {
    }

    override fun initClick() {
        v.toAboutUs.setOnClickListener {
            ARouter.getInstance().build(ARouterUtil.AboutUsActivity).navigation()
        }
    }

    override fun initData() {
    }

    override fun lazyLoadData() {
    }
}