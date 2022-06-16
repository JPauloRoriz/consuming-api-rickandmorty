package com.joaop.rickandmorty.ui.extension

import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout

fun ImageView.loadFromUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun RecyclerView.setOnFinsihScrollListener(response: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1)) {
                response.invoke()
            }
        }
    })
}

fun ShimmerFrameLayout.showLoadingLayout(show: Boolean) {
    isVisible = show
    if (show) {
        startShimmerAnimation()
    } else {
        stopShimmerAnimation()
    }
}

