package com.github.jirobird.techdemo.restaurantinfo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.github.jirobird.techdemo.restaurantinfo.R
import com.github.jirobird.view.UntouchableConstrainLayout

class NothingToShowErrorView:UntouchableConstrainLayout {
    constructor(context: Context?):super(context) {
        context?.let {
            initAndLoadSubView(context)
        }
    }

    constructor(context: Context, attrs: AttributeSet?):super(context,attrs) {
        initAndLoadSubView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initAndLoadSubView(context)
    }

    private fun initAndLoadSubView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_nothing_to_show, this, true)
    }
}