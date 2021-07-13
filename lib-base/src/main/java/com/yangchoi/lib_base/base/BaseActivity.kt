package com.yangchoi.lib_base.base

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.yangchoi.lib_base.R
import com.yangchoi.lib_base.error.ErrorResult
import com.yangchoi.lib_base.utils.MessageEvent
import com.yangchoi.lib_base.utils.StatusBarUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.reflect.ParameterizedType

/**
 * Created on 2021/4/27
 * describe: activity的基类
 */
abstract class BaseActivity<VM: BaseViewModel,VB: ViewBinding> : AppCompatActivity(){

    lateinit var mContext: FragmentActivity
    //view model
    lateinit var vm: VM
    //view binding
    lateinit var v: VB

    //loading
    private var loadingDialog: ProgressDialog? = null

    protected open fun isRegisterImmersionBar() = true

    abstract fun initView()

    abstract fun initClick()

    abstract fun initData()

    abstract fun initVM()

    //获取类名
    open fun getClassName(): String? {
        val className = "BaseActivity"
        try {
            return javaClass.name
        } catch (e: Exception) {
        }
        return className
    }


    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //注意 type.actualTypeArguments[0]=BaseViewModel，type.actualTypeArguments[1]=ViewBinding
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz1 = type.actualTypeArguments[0] as Class<VM>
        vm = ViewModelProvider(this).get(clazz1)

        val clazz2 = type.actualTypeArguments[1] as Class<VB>
        val method = clazz2.getMethod("inflate", LayoutInflater::class.java)
        v = method.invoke(null, layoutInflater) as VB

        StatusBarUtil.StatusBarLightMode(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        setContentView(v.root)

        mContext = this

//        initImmersionBar()

        init()
        initView()
        initClick()
        initData()
        initVM()
        LogUtils.e(getClassName())
    }

    private fun initImmersionBar(){
        if (isRegisterImmersionBar()) {
            ImmersionBar.with(this)
                .transparentStatusBar()
                .transparentNavigationBar()
                .transparentBar()
                .statusBarColor(R.color.statusBarDefaultColor)
                .statusBarDarkFont(false)
                .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
                .init()
        }
    }

    private fun init() {
        //loading
        vm.isShowLoading.observe(this, Observer {
            if (it) showLoading() else dismissLoading()
        })
        //错误信息
        vm.errorResult.observe(this, Observer {
            if (it.show) ToastUtils.showShort(it.errMsg)
            errorResult(it)
        })
    }


    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = ProgressDialog(this)
        }
        loadingDialog!!.show()
    }

    fun dismissLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    /**
     * 接口请求错误回调
     */
    open fun errorResult(errorResult: ErrorResult) {}
    /**
     * 注册event事件
     * */
    override fun onResume() {
        super.onResume()
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
    }
    /**
     * 解绑event事件
     * */
    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }
    /**
     * event事件传递
     * */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun onEventMainThread(event: MessageEvent) {
        onActivityEvent(event)
    }
    /**
     * 消息、事件接收回调
     */
    open fun onActivityEvent(event: MessageEvent) {

    }
}