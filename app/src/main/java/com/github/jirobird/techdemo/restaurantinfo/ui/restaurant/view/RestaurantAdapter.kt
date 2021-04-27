package com.github.jirobird.techdemo.restaurantinfo.ui.restaurant.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.jirobird.techdemo.restaurantinfo.R
import com.github.jirobird.techdemo.restaurantinfo.bll.repository.network.RestaurantRepository
import com.squareup.picasso.Picasso
import kotlin.math.min

class RestaurantAdapter(private val iRestaurant: MutableList<RestaurantRepository.Restaurant>):
    RecyclerView.Adapter<RestaurantAdapter.RestaurantRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vh_restaurant, parent, false)
        return RestaurantRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantRecyclerViewHolder, position: Int) {
        holder.bindUIWithRestaurantModel(iRestaurant[position])
    }

    override fun getItemCount(): Int {
        return iRestaurant.size
    }

    class RestaurantRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconView: ImageView = itemView.findViewById(R.id.iv_icon_restaurant)
        private val tvName:TextView = itemView.findViewById(R.id.tv_restaurant_name)
        private val tvContent:TextView = itemView.findViewById(R.id.tv_restaurant_content)
        private val tvRatio:TextView = itemView.findViewById(R.id.tv_restaurant_ratio)

        fun bindUIWithRestaurantModel(iRestaurant: RestaurantRepository.Restaurant) {
            Picasso.get()
                .load(iRestaurant.Logo)
                .placeholder(R.drawable.ic_restaurant_black_24dp)
                .into(iconView)

            tvName.text = iRestaurant.Name ?: "Нет имени"
            var content = ""
            iRestaurant.Specializations?.forEach {
                val append = if(content.isNotEmpty()) {
                    " / ${it.Name}"
                } else {
                    it.Name
                }

                content = content.plus(append)
            }

            tvContent.text = content
            val percentage = min(100, ((iRestaurant.PositiveReviews ?: 0).toFloat() / (iRestaurant.ReviewsCount ?: 1).toFloat() * 100).toInt())
            tvRatio.text = "\uD83D\uDC4D ${percentage}%"
        }
    }
}