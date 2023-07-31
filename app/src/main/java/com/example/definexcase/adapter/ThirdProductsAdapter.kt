package com.example.definexcase.adapter

import android.content.Context
import android.graphics.Paint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.definexcase.R
import com.example.definexcase.api.model.ListResponse
import com.example.definexcase.databinding.ThirdItemProductsBinding

class ThirdProductsAdapter(val productsList: ListResponse, val context: Context) :
    RecyclerView.Adapter<ThirdProductsAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(val binding: ThirdItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ThirdItemProductsBinding.inflate(inflater, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productsList.list.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.binding.tvTitle.text = productsList.list[position].description
        holder.binding.ratingBar.rating =
            getRating(productsList.list[position].ratePercentage.toString())
        if (holder.binding.ratingBar.rating == 0f) holder.binding.ratingBar.visibility = View.INVISIBLE
        Glide.with(context)
            .load(productsList.list[position].imageUrl) // image url
            .placeholder(R.drawable.ic_email) // any placeholder to load at start
            .error(R.drawable.ic_facebook)  // any image in case of error
            .centerCrop()
            .into(holder.binding.ivProduct)
        if (productsList.list[position].oldPrice == null) {
            val price =
                productsList.list[position].price.value.toString() + " " + productsList.list[position].price.currency + "US"
            holder.binding.tvOldPrice.typeface =
                ResourcesCompat.getFont(context, R.font.roboto_medium)
            holder.binding.tvOldPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            holder.binding.tvDot.visibility = View.GONE
            holder.binding.tvOldPrice.text = price
        } else {
            holder.binding.tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            val price =
                productsList.list[position].price.value.toString() + " " + productsList.list[position].price.currency + "US"
            val oldPrice =
                productsList.list[position].oldPrice?.value.toString() + " " + productsList.list[position].oldPrice?.currency + "US"
            holder.binding.tvOldPrice.text = oldPrice
            holder.binding.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.binding.tvDiscount.text = productsList.list[position].discount
            holder.binding.tvNewPrice.text = price
        }
    }

    fun getRating(rating: String): Float {
        when (rating) {
            "100" -> {
                return 5f
            }

            "80" -> {
                return 4f
            }

            "60" -> {
                return 3f
            }

            "40" -> {
                return 2f
            }

            "20" -> {
                return 1f
            }

            "0" -> {
                return 0f
            }
        }
        return 0f
    }


}