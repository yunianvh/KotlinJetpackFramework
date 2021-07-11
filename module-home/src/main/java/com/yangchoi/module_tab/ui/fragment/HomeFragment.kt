package com.yangchoi.module_tab.ui.fragment

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangchoi.lib_base.base.BaseFragment
import com.yangchoi.lib_network.entity.ArticleBean
import com.yangchoi.lib_network.entity.ArticleListBean
import com.yangchoi.lib_network.entity.BannerEntity
import com.yangchoi.lib_public.mmkv.MMKVDataTypeMenu
import com.yangchoi.lib_public.mmkv.MMKVUtil
import com.yangchoi.module_tab.R
import com.yangchoi.module_tab.adapter.HomeArticleAdapter
import com.yangchoi.module_tab.adapter.HomeBannerAdapter
import com.yangchoi.module_tab.databinding.FragmentHomeBinding
import com.yangchoi.module_tab.ui.activity.SearchActivity
import com.yangchoi.module_tab.viewmodel.HomeFragmentVM
import com.youth.banner.indicator.CircleIndicator

/**
 * Created on 2021/6/23
 * describe:
 */
class HomeFragment : BaseFragment<HomeFragmentVM, FragmentHomeBinding>(),View.OnClickListener {
    private var homeArticleAdapter:HomeArticleAdapter? = null
    private var homeBannerAdapter:HomeBannerAdapter? = null

    private var page = 0

    private var list: MutableList<ArticleBean>? = null//数据集合
    private var dataBean:ArticleListBean? = null

    private var bannerList:MutableList<BannerEntity>? = null

    /**
     * 拿到接口返回的值
     * */
    override fun initVM() {

        vm.articlesData.observe(this, Observer {
            if (page == 0) list?.clear()

            dataBean = it
            it.datas?.let { it1 -> initHomeArticle(it1 as MutableList<ArticleBean>) }
            homeArticleAdapter?.notifyDataSetChanged()
        })

        vm.bannerData.observe(this, Observer {
            bannerList = it

            initBanner(it)
        })
    }
    /**
     * 获取接口数据
     * */
    override fun initView() {

        MMKVUtil.saveValue(MMKVDataTypeMenu.INT.toString(),"testString","1111")

        vm.getArticListData(page,true)
        vm.getBannerList()
    }

    override fun initClick() {
        v.btnSearch.setOnClickListener(this)
    }

    override fun initData() {

    }

    override fun lazyLoadData() {
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_search -> startActivity(Intent(requireActivity(),SearchActivity::class.java))
        }
    }

    private fun initHomeArticle(data:MutableList<ArticleBean>){
        homeArticleAdapter = HomeArticleAdapter(requireActivity(),data)

        v.homeArticleList.apply {
            layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
            adapter = homeArticleAdapter
        }
    }

    private fun initBanner(data: MutableList<BannerEntity>){
        homeBannerAdapter = HomeBannerAdapter(requireContext(),data)

        v.homeBanner.apply {
            addBannerLifecycleObserver(requireActivity())
            setAdapter(homeBannerAdapter)
            setIndicator(CircleIndicator(requireContext()))
            start()
        }
    }
}