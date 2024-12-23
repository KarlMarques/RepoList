package com.twisted.minds.repolist.ui.screens.repolist

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

internal abstract class RepoListScrollListener(
    layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var currentPage = INITIAL_PAGE_INDEX
    private var previousTotalItemCount = INITIAL_TOTAL_ITEM_COUNT
    private var visibleThreshold = INITIAL_VISIBLE_THRESHOLD
    private var loading = true

    var layoutManager: RecyclerView.LayoutManager = layoutManager
    var customAdapter: RepoListAdapter? = null

    fun setAdapter(adapter: RepoListAdapter?) {
        this.customAdapter = adapter
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount: Int = customAdapter?.getItemCount() ?: INITIAL_ITEM_COUNT

        val lastVisibleItemPosition =
            (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = INITIAL_PAGE_INDEX
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == INITIAL_ITEM_COUNT) {
                this.loading = true
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++
            loadNextPage(currentPage, totalItemCount, view)
            loading = true
        }
    }

    abstract fun loadNextPage(page: Int, totalItemsCount: Int, view: RecyclerView?)

    companion object {
        private const val INITIAL_PAGE_INDEX = 0
        private const val INITIAL_ITEM_COUNT = 0
        private const val INITIAL_TOTAL_ITEM_COUNT = 0
        private const val INITIAL_VISIBLE_THRESHOLD = 5
    }
}
