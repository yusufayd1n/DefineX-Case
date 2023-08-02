package com.example.definexcase.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.definexcase.api.model.listResponse.ListItems
import com.example.definexcase.api.model.listResponse.ListsResponse
import com.example.definexcase.databinding.SecondItemProductsBinding
import com.example.definexcase.util.downloadFromUrl

class SecondProductsAdapter(
    private val productsList: List<ListItems>,
    val context: Context,
    val itemClickListener: () -> Unit
) :
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
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val price =
            productsList[position].price.value.toString() + " " + productsList[position].price.currency + "US"
        val oldPrice =
            productsList[position].oldPrice?.value.toString() + " " + productsList[position].oldPrice?.currency + "US"
        holder.apply {
            binding.tvTitle.text = productsList[position].description
            binding.ivProduct.downloadFromUrl(productsList[position].photoUrl)
            binding.tvOldPrice.text = oldPrice
            binding.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvNewPrice.text = price
            binding.root.setOnClickListener {
                itemClickListener.invoke()
            }
        }

    }


}