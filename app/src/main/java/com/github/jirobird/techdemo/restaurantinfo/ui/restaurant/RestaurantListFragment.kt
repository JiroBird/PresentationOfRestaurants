package com.github.jirobird.techdemo.restaurantinfo.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jirobird.techdemo.restaurantinfo.R
import com.github.jirobird.techdemo.restaurantinfo.bll.repository.network.RestaurantRepository
import com.github.jirobird.techdemo.restaurantinfo.ui.restaurant.view.RestaurantAdapter
import com.github.jirobird.techdemo.restaurantinfo.views.ConnectivityErrorView
import com.github.jirobird.techdemo.restaurantinfo.views.NothingToShowErrorView

class RestaurantListFragment : Fragment(), IRestaurantUIContract.IRestaurantView {
    private lateinit var presenter: RestaurantPresenter
    private lateinit var progressBar:ProgressBar
    private lateinit var rvRestaurantListView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = RestaurantPresenter(view.context)
        progressBar = view.findViewById(R.id.pb_loading_progress)
        rvRestaurantListView = view.findViewById(R.id.rv_restaurant_list)
        rvRestaurantListView.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onPause() {
        presenter.deattachView()
        super.onPause()
    }

    override fun showConnectivityErrorScreen() {
        showClearState()
        view?.findViewById<FrameLayout>(R.id.fl_error_screen_container)?.addView(
            ConnectivityErrorView(context),
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        )
    }

    private fun hideConnectivityErrorScreen() {
        view?.findViewById<FrameLayout>(R.id.fl_error_screen_container)?.removeAllViews()
    }

    override fun showProgressLoaderState() {
        showClearState()
        showProgressLoader()
    }

    private fun showProgressLoader() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressLoader() {
        progressBar.visibility = View.GONE
    }

    override fun pushAndRefreshRestaurantInfo(oRestaurant: MutableList<RestaurantRepository.Restaurant>) {
        showClearState()
        rvRestaurantListView.adapter = RestaurantAdapter(oRestaurant)
    }

    override fun showEmptyResultState() {
        showClearState()
        view?.findViewById<FrameLayout>(R.id.fl_error_screen_container)?.addView(
            NothingToShowErrorView(context),
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        )
    }

    private fun showClearState() {
        hideConnectivityErrorScreen()
        hideEmptyResultState()
        hideProgressLoader()
    }

    private fun hideEmptyResultState() {
        view?.findViewById<FrameLayout>(R.id.fl_error_screen_container)?.removeAllViews()
    }
}

//        ReviewsRepository.getClient(this)?.getReviews()?.enqueue(
//            object : Callback<MutableList<ReviewsRepository.Review>> {
//                override fun onResponse(
//                    call: Call<MutableList<ReviewsRepository.Review>>,
//                    response: Response<MutableList<ReviewsRepository.Review>>
//                ) {
//
//                }
//
//                override fun onFailure(
//                    call: Call<MutableList<ReviewsRepository.Review>>,
//                    t: Throwable
//                ) {
//
//                }
//
//            }
//        )
//
//        HitsRepository.getClient(this)?.getHits()
//            ?.enqueue(object : Callback<MutableList<HitsRepository.Hit>> {
//                override fun onResponse(
//                    call: Call<MutableList<HitsRepository.Hit>>,
//                    response: Response<MutableList<HitsRepository.Hit>>
//                ) {
//
//                }
//
//                override fun onFailure(call: Call<MutableList<HitsRepository.Hit>>, t: Throwable) {
//
//                }
//            })