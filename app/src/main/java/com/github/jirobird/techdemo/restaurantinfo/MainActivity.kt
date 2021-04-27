package com.github.jirobird.techdemo.restaurantinfo

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.jirobird.techdemo.restaurantinfo.ui.favourits.FavouriteListFragment
import com.github.jirobird.techdemo.restaurantinfo.ui.restaurant.RestaurantListFragment
import com.github.jirobird.techdemo.restaurantinfo.ui.review.ReviewListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : FragmentActivity(), IMainActivityContract.IMainActivityView {

    enum class MainUIState {
        MainUIStateRest,
        MainUIStateFav,
        MainUIStateRev
    }

    private var state = MainUIState.MainUIStateRest
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mBottomNavigationListener)

        presenter = MainActivityPresenter(this)

        savedInstanceState?.let { bundle ->
            bundle.getSerializable("UI_STATE")?.let { serializable ->
                state = serializable as MainUIState
            }
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("UI_STATE", state)
    }

    private val mBottomNavigationListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_restaurants -> {
                    state = MainUIState.MainUIStateRest
                }

                R.id.navigation_favourites -> {
                    state = MainUIState.MainUIStateFav
                }

                R.id.navigation_reviews -> {
                    state = MainUIState.MainUIStateRev
                }
            }

            presenter.uiStateSelected(state)

            true
        }

    override fun restoreUIWithSavedState() {
        when(state) {
            MainUIState.MainUIStateRest -> {
                pushRestaurantFragmentToUI()
            }

            MainUIState.MainUIStateFav -> {
                pushFavouriteFragmentToUI()
            }

            MainUIState.MainUIStateRev -> {
                pushReviewFragmentToUI()
            }
        }
    }

    private fun pushRestaurantFragmentToUI() {
        val fragment = RestaurantListFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_container,
            fragment,
            fragment.javaClass.simpleName
        ).commit()
    }

    private fun pushFavouriteFragmentToUI() {
        val fragment = FavouriteListFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_container,
            fragment,
            fragment.javaClass.simpleName
        ).commit()
    }

    private fun pushReviewFragmentToUI() {
        val fragment = ReviewListFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_container,
            fragment,
            fragment.javaClass.simpleName
        ).commit()
    }
}