package com.example.definexcase.adapter

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.definexcase.R
import com.example.definexcase.api.model.FirstListResponse
import com.example.definexcase.databinding.ItemProductsBinding
import com.example.definexcase.databinding.SecondItemProductsBinding

class SecondProductsAdapter(val productsList: FirstListResponse, val context: Context) :
    RecyclerView.Adapter<SecondProductsAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(val binding: SecondItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = SecondItemProductsBinding.inflate(inflater, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productsList.list.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.binding.tvTitle.text = productsList.list[position].description
        Glide.with(context)
            .load(productsList.list[position].imageUrl) // image url
            .placeholder(R.drawable.ic_email) // any placeholder to load at start
            .error(R.drawable.ic_facebook)  // any image in case of error
            .centerCrop()
            .into(holder.binding.ivProduct)
        val price =
            productsList.list[position].price.value.toString() + " " + productsList.list[position].price.currency + "US"
        val oldPrice =
            productsList.list[position].oldPrice?.value.toString() + " " + productsList.list[position].oldPrice?.currency + "US"
        holder.binding.tvOldPrice.text = oldPrice
        holder.binding.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.binding.tvNewPrice.text = price

    }


}