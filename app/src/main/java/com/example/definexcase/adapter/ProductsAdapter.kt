package com.example.definexcase.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.definexcase.api.model.listResponse.ListItems
import com.example.definexcase.databinding.ItemProductsBinding
import com.example.definexcase.util.downloadFromUrl

class ProductsAdapter(
    private val productsList: List<ListItems>,
    val context: Context,
    private val itemClickListener: () -> Unit
) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemProductsBinding.inflate(inflater, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.apply {
            binding.tvTitle.text = productsList[position].description
            binding.ivProduct.downloadFromUrl(productsList[position].photoUrl)
            if (productsList[position].discount == "") {
                val price =
                    productsList[position].oldPrice?.value.toString() + " " + productsList[position].oldPrice?.currency + "US"
                binding.tvOldPrice.text = price
            } else {
                val price =
                    productsList[position].price.value.toString() + " " + productsList[position].price.currency + "US"
                val oldPrice =
                    productsList[position].oldPrice?.value.toString() + " " + productsList[position].oldPrice?.currency + "US"
                binding.tvOldPrice.text = oldPrice
                binding.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.tvDiscount.text = productsList[position].discount
                binding.tvNewPrice.text = price
            }
            binding.root.setOnClickListener {
                itemClickListener.invoke()
            }
        }

    }


}