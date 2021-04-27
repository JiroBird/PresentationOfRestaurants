package com.github.jirobird.techdemo.restaurantinfo.ui.restaurant

import android.content.Context
import android.util.Log
import com.github.jirobird.mvp.BasePresenter
import com.github.jirobird.techdemo.restaurantinfo.bll.ConnectivityChecker
import com.github.jirobird.techdemo.restaurantinfo.bll.repository.network.RestaurantRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantPresenter(context: Context) : BasePresenter<IRestaurantUIContract.IRestaurantView>(context),
    IRestaurantUIContract.IRestaurantPresenter {

    private var isTestTrouble = false
    override fun viewIsReady() {
        if (isTestTrouble || !ConnectivityChecker.isNetworkConnected(context)) {
            view?.showConnectivityErrorScreen()
        } else {
            view?.showProgressLoaderState()
            loadRests()
        }
    }

    private fun loadRests() {
        val call = RestaurantRepository.getClient(context)?.getRestaurants()
        if (call == null) {

        } else {
            call.enqueue(object : Callback<MutableList<RestaurantRepository.Restaurant>> {
                override fun onResponse(
                    call: Call<MutableList<RestaurantRepository.Restaurant>>,
                    response: Response<MutableList<RestaurantRepository.Restaurant>>
                ) {
                    if(response.body() != null) {
                        view?.pushAndRefreshRestaurantInfo(response.body()!!)
                    } else {
                        view?.showEmptyResultState()
                    }
                }

                override fun onFailure(
                    call: Call<MutableList<RestaurantRepository.Restaurant>>,
                    t: Throwable
                ) {
                    Log.d("", "")
                }
            })
        }
    }
}