package com.github.jirobird.mvp

import android.content.Context

abstract class BasePresenter<V : MvpView>(val context: Context) :
    MvpPresenter<V> {
    var view: V? = null

    override fun attachView(mvpView: V) {
        view = mvpView
    }

    override fun deattachView() {
        view = null
    }

    override fun destroy() {
        view = null
    }

    protected fun isViewAttached():Boolean {
        return view != null
    }
}
