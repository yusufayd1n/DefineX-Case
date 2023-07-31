package com.example.definexcase.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.definexcase.api.model.listResponse.ListsResponse
import com.example.definexcase.databinding.SecondItemProductsBinding
import com.example.definexcase.util.downloadFromUrl

class SecondProductsAdapter(private val productsList: ListsResponse, val context: Context) :
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
        holder.binding.ivProduct.downloadFromUrl(productsList.list[position].imageUrl)
        val price =
            productsList.list[position].price.value.toString() + " " + productsList.list[position].price.currency + "US"
        val oldPrice =
            productsList.list[position].oldPrice?.value.toString() + " " + productsList.list[position].oldPrice?.currency + "US"
        holder.binding.tvOldPrice.text = oldPrice
        holder.binding.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.binding.tvNewPrice.text = price

    }


}