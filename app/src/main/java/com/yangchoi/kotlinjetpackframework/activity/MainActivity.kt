package com.yangchoi.kotlinjetpackframework.activity

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Handler
import android.os.Message
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.yangchoi.kotlinjetpackframework.R
import com.yangchoi.kotlinjetpackframework.databinding.ActivityMainBinding
import com.yangchoi.kotlinjetpackframework.model.MainActivityModel
import com.yangchoi.lib_base.base.BaseActivity
import com.yangchoi.lib_public.utils.ARouterUtil
import com.yangchoi.module_mine.fragment.MineFragment
import com.yangchoi.module_project.ui.fragment.ProjectFragment
import com.yangchoi.module_tab.fragment.HomeFragment
import q.rorbin.badgeview.QBadgeView


@Route(path = ARouterUtil.MainActivity)
class MainActivity : BaseActivity<MainActivityModel, ActivityMainBinding>() {
    //实现两次后退才退出
    private var isExit = 0
    private var lastIndex = 0

    private var fragmentsList: MutableList<Fragment> = mutableListOf()

    override fun initView() {
        initFragment()
        initBottom()
    }

    override fun initClick() {
    }

    override fun initData() {
    }

    override fun initVM() {
    }
    private fun initFragment(){
        fragmentsList.add(HomeFragment())
        fragmentsList.add(ProjectFragment())
        fragmentsList.add(MineFragment())
        setFragmentPosition(0)
        showBadgeView(2,3)
    }


    private fun initBottom() {
        //剔除系统默认背景 = btmNavigation.itemIconTintList = null
        v.navView.run {
//            itemIconTintList = null//取消导航栏图标着色  设置为null之后点击无法设置图标颜色
            itemRippleColor = ColorStateList.valueOf(resources.getColor(R.color.transparent))//去掉BottomNavigationView点击时产生的灰色背景
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> setFragmentPosition(0)
                    R.id.menu_classic -> setFragmentPosition(1)
                    R.id.menu_mine -> setFragmentPosition(2)
                }
                // 这里注意返回true,否则点击失效
                true
            }
        }
    }
    private fun setFragmentPosition(position: Int) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val currentFragment: Fragment = fragmentsList[position]
        val lastFragment: Fragment = fragmentsList[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.main_fragment, currentFragment)
            ft.setMaxLifecycle(currentFragment, Lifecycle.State.RESUMED)
        }
        ft.show(currentFragment)
        ft.commit()
    }
    /**
     *监听返回事件-点击两次退出
     */
    var handler: Handler = @SuppressLint("HandlerLeak") object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0) {
                isExit--
            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isExit++
            if (isExit == 1) {
                ToastUtils.showShort("再按一次退出程序")
            }
            if (isExit < 2) {
                //利用handler延迟发送更改状态信息
                handler.sendEmptyMessageDelayed(0, 2000);
            }
            if (isExit == 2) {
                finish()
            }
            return false
        }
        return super.onKeyDown(keyCode, event)
    }
    /**
     * 放弃了 navigation 别问我为什么,懒得改bug。
     * */
//    private fun initBottomNavigationBar(){
//        val navGraphIds =
//            listOf(R.navigation.navi_home, R.navigation.navi_classic, R.navigation.navi_car,R.navigation.navi_mine)
//
//        v.navView.apply {
//            //取消导航栏图标着色  设置为null之后点击无法设置图标颜色
////            itemIconTintList = null
//            //去掉点击时产生的默认背景
//            itemRippleColor =  ColorStateList.valueOf(resources.getColor(R.color.transparent))
//        }
//
//        val controller = v.navView.setupWithNavController(
//            navGraphIds = navGraphIds,
//            fragmentManager = supportFragmentManager,
//            containerId = R.id.nav_host_container,
//            intent = intent
//        )
//        currentNavController = controller
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return currentNavController?.value?.navigateUp() ?: false
//    }


    /**
     * BottomNavigationView显示角标
     *
     * @param viewIndex 要设置角标得tab索引
     * @param showNumber 显示的数字，小于等于0是将不显示
     */
    private fun showBadgeView(viewIndex: Int, showNumber: Int) {
        // 具体child的查找和view的嵌套结构请在源码中查看
        // 从bottomNavigationView中获得BottomNavigationMenuView
        val menuView: BottomNavigationMenuView = v.navView.getChildAt(0) as BottomNavigationMenuView
        // 从BottomNavigationMenuView中获得childview, BottomNavigationItemView
        if (viewIndex < menuView.getChildCount()) {
            // 获得viewIndex对应子tab
            val view: View = menuView.getChildAt(viewIndex)
            // 从子tab中获得其中显示图片的ImageView
            val icon: View = view.findViewById(com.google.android.material.R.id.icon)
            // 获得图标的宽度
            val iconWidth: Int = icon.getWidth()
            // 获得tab的宽度/2
            val tabWidth: Int = view.getWidth() /4
            // 计算badge要距离右边的距离
            val spaceWidth = tabWidth - iconWidth

            // 显示badegeview
            QBadgeView(this).bindTarget(view).setGravityOffset(spaceWidth.toFloat()+20, 3f, false)
                .setBadgeNumber(showNumber)
        }
    }
}