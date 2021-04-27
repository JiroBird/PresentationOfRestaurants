package com.github.jirobird.techdemo.restaurantinfo

import android.content.Context
import com.github.jirobird.mvp.BasePresenter

class MainActivityPresenter(context: Context) :
    BasePresenter<IMainActivityContract.IMainActivityView>(context),
    IMainActivityContract.IMainActivityPresenter {

    override fun viewIsReady() {
        view?.restoreUIWithSavedState()
    }

    override fun uiStateSelected(iState: MainActivity.MainUIState) {
        view?.restoreUIWithSavedState()
        //TODO: push ui analytics message
    }
}