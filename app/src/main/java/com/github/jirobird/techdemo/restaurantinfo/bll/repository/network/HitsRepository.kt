package com.github.jirobird.techdemo.restaurantinfo.bll.repository.network

import android.content.Context
import com.github.jirobird.techdemo.restaurantinfo.R
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class HitsRepository {
    data class Hit (
        @SerializedName("ProductName") var ProductName:String? = null,
        @SerializedName("ProductImage") var ProductImage:String? = null,
        @SerializedName("ProductPrice") var ProductPrice:Int? = 0,
        @SerializedName("ProductDescription") var ProductDescription:String? = null,
        @SerializedName("RestaurantId") var RestaurantId:Int? = 0,
        @SerializedName("RestaurantName") var RestaurantName:String? = null,
        @SerializedName("RestaurantLogo") var RestaurantLogo:String? = null
    )

    companion object {
        fun getClient(context: Context): HitsRepositoryService? = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HitsRepositoryService::class.java)
    }

    interface HitsRepositoryService {
        @GET("hits")
        fun getHits(): Call<MutableList<Hit>>
    }
}