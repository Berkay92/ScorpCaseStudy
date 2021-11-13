package com.example.scorpcasestudy.view

import androidx.recyclerview.widget.RecyclerView

abstract class ScrollDirectionListener : RecyclerView.OnScrollListener() {

    abstract fun onScrollLimit()

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val offset = recyclerView.computeVerticalScrollOffset()
            val extent = recyclerView.computeVerticalScrollExtent()
            val range = recyclerView.computeVerticalScrollRange()
            if (offset + extent == range) {
                    onScrollLimit()
            }
        }
    }
}