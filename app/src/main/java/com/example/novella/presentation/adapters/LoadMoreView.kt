package com.example.novella.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.novella.R

class LoadMoreView : BaseLoadMoreView() {
    override fun getRootView(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.load_views, parent, false)

    override fun getLoadingView(holder: BaseViewHolder): View = holder.getView(R.id.load_more_loading_view)

    override fun getLoadComplete(holder: BaseViewHolder): View = holder.getView(R.id.load_more_load_end_view)

    override fun getLoadEndView(holder: BaseViewHolder): View = holder.getView(R.id.load_more_load_end_view)

    override fun getLoadFailView(holder: BaseViewHolder): View = holder.getView(R.id.load_more_load_fail_view)
}