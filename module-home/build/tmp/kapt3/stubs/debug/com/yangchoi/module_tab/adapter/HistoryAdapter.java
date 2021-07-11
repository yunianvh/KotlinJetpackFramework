package com.yangchoi.module_tab.adapter;

import java.lang.System;

/**
 * Created on 2021/6/25
 * describe:
 */
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u00a2\u0006\u0002\u0010\bJ \u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0014\u0010\u001a\u001a\u00020\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007J\u0010\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\f2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nR\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/yangchoi/module_tab/adapter/HistoryAdapter;", "Lcom/yangchoi/lib_base/base/BaseAdapter;", "Lcom/yangchoi/module_tab/databinding/ItemHistoryBinding;", "Lcom/yangchoi/module_tab/dao/SearchHistoryEntity;", "context", "Landroid/app/Activity;", "dataList", "", "(Landroid/app/Activity;Ljava/util/List;)V", "childItemClick", "Lkotlin/Function1;", "", "", "mFlexItemTextViewCaches", "Ljava/util/Queue;", "Landroid/widget/TextView;", "mInflater", "Landroid/view/LayoutInflater;", "convert", "viewBind", "item", "position", "createFlexItemTextView", "fbl", "Lcom/google/android/flexbox/FlexboxLayout;", "createOrGetCacheFlexItemTextView", "newData", "onViewRecycled", "holder", "Lcom/yangchoi/lib_base/base/BaseViewHolder;", "setChildItemClick", "itemClick", "module-home_debug"})
public final class HistoryAdapter extends com.yangchoi.lib_base.base.BaseAdapter<com.yangchoi.module_tab.databinding.ItemHistoryBinding, com.yangchoi.module_tab.dao.SearchHistoryEntity> {
    private android.view.LayoutInflater mInflater;
    private final java.util.Queue<android.widget.TextView> mFlexItemTextViewCaches = null;
    private kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> childItemClick;
    private java.util.List<com.yangchoi.module_tab.dao.SearchHistoryEntity> dataList;
    
    public final void setChildItemClick(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> itemClick) {
    }
    
    @java.lang.Override()
    public void convert(@org.jetbrains.annotations.NotNull()
    com.yangchoi.module_tab.databinding.ItemHistoryBinding viewBind, @org.jetbrains.annotations.NotNull()
    com.yangchoi.module_tab.dao.SearchHistoryEntity item, int position) {
    }
    
    @java.lang.Override()
    public void onViewRecycled(@org.jetbrains.annotations.NotNull()
    com.yangchoi.lib_base.base.BaseViewHolder holder) {
    }
    
    private final android.widget.TextView createOrGetCacheFlexItemTextView(com.google.android.flexbox.FlexboxLayout fbl) {
        return null;
    }
    
    private final android.widget.TextView createFlexItemTextView(com.google.android.flexbox.FlexboxLayout fbl) {
        return null;
    }
    
    public final void newData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.yangchoi.module_tab.dao.SearchHistoryEntity> dataList) {
    }
    
    public HistoryAdapter(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.yangchoi.module_tab.dao.SearchHistoryEntity> dataList) {
        super(null, null);
    }
}