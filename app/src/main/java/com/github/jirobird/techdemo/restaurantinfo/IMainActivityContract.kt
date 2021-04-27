package com.github.jirobird.techdemo.restaurantinfo

import com.github.jirobird.mvp.MvpPresenter
import com.github.jirobird.mvp.MvpView

class IMainActivityContract {
    interface IMainActivityView: MvpView {
        fun restoreUIWithSavedState()
    }

    interface IMainActivityPresenter:MvpPresenter<IMainActivityView> {
        fun uiStateSelected(iState: MainActivity.MainUIState)
    }
}