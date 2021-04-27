package com.github.jirobird.techdemo.restaurantinfo.bll.repository.network

import android.content.Context
import com.github.jirobird.techdemo.restaurantinfo.R
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ReviewsRepository {
    data class Review(
        @SerializedName("IsPositive") var IsPositive:Boolean? = false,
        @SerializedName("Message") var Message:String? = null,
        @SerializedName("DateAdded") var DateAdded:String? = null,
        @SerializedName("UserFIO") var UserFIO:String? = null,
        @SerializedName("RestaurantName") var RestaurantName:String? = null
    )

    companion object {
        fun getClient(context: Context): ReviewsRepositoryService? = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReviewsRepositoryService::class.java)
    }

    interface ReviewsRepositoryService {
        @GET("reviews")
        fun getReviews(): Call<MutableList<Review>>
    }
}