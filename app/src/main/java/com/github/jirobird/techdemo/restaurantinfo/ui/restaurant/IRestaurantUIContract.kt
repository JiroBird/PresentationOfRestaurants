package com.github.jirobird.techdemo.restaurantinfo.ui.restaurant

import com.github.jirobird.mvp.MvpPresenter
import com.github.jirobird.mvp.MvpView
import com.github.jirobird.techdemo.restaurantinfo.bll.repository.network.RestaurantRepository

interface IRestaurantUIContract {
    interface IRestaurantView:MvpView {
        fun showConnectivityErrorScreen()
        fun showProgressLoaderState()
        fun pushAndRefreshRestaurantInfo(oRestaurant: MutableList<RestaurantRepository.Restaurant>)
        fun showEmptyResultState()
    }

    interface IRestaurantPresenter: MvpPresenter<IRestaurantView> {

    }
}