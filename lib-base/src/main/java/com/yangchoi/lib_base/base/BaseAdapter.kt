package com.yangchoi.lib_base.base

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * Created on 2021/4/27
 * describe:通过传入ViewBinding，不再需要写具体xml资源，省略onBindViewHolder中findviewById
 *          注意点：item的最外层布局高度要设为wrap_content，
 *          如果item有需求要设置为固定宽高，可以在子类的convert方法里，通过代码设置
 *
 * @param mContext 上下文对象  activity
 * @param dataListAD 数据集合
 */
abstract class BaseAdapter<VB : ViewBinding, T>(
    var mContext: Activity,
    var dataListAD: MutableList<T>
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<VB>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        var vb = method.invoke(null, LayoutInflater.from(mContext)) as VB
        vb.root.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,//宽度和高度一定不能使用MATCH_PARENT,否则无法正确使用xml宽高
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return BaseViewHolder(vb, vb.root)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.let { it(position) }
        }
        holder.itemView.setOnLongClickListener {
            itemLongClick?.let { it1 -> it1(position) }
            true
        }

        convert(holder.v as VB, dataListAD[position], position)
    }

    abstract fun convert(viewBind: VB, item: T, position: Int)

    override fun getItemCount(): Int {
        return dataListAD.size
    }
    /**
     * 设置新数据
     * */
    fun setNewData(dataListAD: MutableList<T>){
        this.dataListAD = dataListAD
        notifyDataSetChanged()
    }


    private var itemClick: ((Int) -> Unit)? = null
    private var itemLongClick: ((Int) -> Unit)? = null

    /**
     * item点击事件
     * 返回当前点击的item下标
     * @param itemClick 点击事件回调
     * */
    fun setOnItemClick(itemClick: (Int) -> Unit) {
        this.itemClick = itemClick
    }
    /**
     * 当前item的长按点击事件
     * 返回当前点击的item下标
     * @param itemLongClick 长按事件回调
     * */
    fun setOnItemLongClick(itemLongClick: (Int) -> Unit) {
        this.itemLongClick = itemLongClick
    }
    /**
     * 返回当前adapter的数据集合
     * @return
     * */
    fun getADDataList():MutableList<T>{
        return dataListAD
    }
}