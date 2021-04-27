package com.github.jirobird.techdemo.restaurantinfo.bll.repository.network

import android.content.Context
import com.github.jirobird.techdemo.restaurantinfo.R
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RestaurantRepository {
    data class Restaurant(
        @SerializedName("Name") var Name        :String? = null,
        @SerializedName("Logo") var Logo        :String? = null,
        @SerializedName("MinCost") var MinCost     :Int?    = 0,
        @SerializedName("DeliveryCost") var DeliveryCost:Int? = 0,
        @SerializedName("DeliveryTime") var DeliveryTime:Int? = 0,
        @SerializedName("PositiveReviews") var PositiveReviews:Int? = 0,
        @SerializedName("ReviewsCount") var ReviewsCount:Int? = 0,
        @SerializedName("Specializations") var Specializations:ArrayList<Specialization>? = null
    )

    data class Specialization(
        @SerializedName("Name") var Name: String? = null
    )

    companion object {
        fun getClient(context: Context): RestaurantRepositoryService? = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestaurantRepositoryService::class.java)
    }

    interface RestaurantRepositoryService {
        @GET("restaurants")
        fun getRestaurants():Call<MutableList<Restaurant>>
    }
}