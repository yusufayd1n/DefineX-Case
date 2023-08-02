package com.example.definexcase.adapter

import android.content.Context
import android.graphics.Paint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.definexcase.R
import com.example.definexcase.api.model.listResponse.ListItems
import com.example.definexcase.api.model.listResponse.ListsResponse
import com.example.definexcase.databinding.ThirdItemProductsBinding
import com.example.definexcase.util.downloadFromUrl

class ThirdProductsAdapter(
    private val productsList: List<ListItems>,
    val context: Context,
    val itemClickListener: () -> Unit
) :
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
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.apply {
            binding.tvTitle.text = productsList[position].description
            binding.ratingBar.rating =
                getRating(productsList[position].ratePercentage.toString())
            if (binding.ratingBar.rating == 0f) binding.ratingBar.visibility =
                View.INVISIBLE
            binding.ivProduct.downloadFromUrl(productsList[position].photoUrl)
            if (productsList[position].oldPrice == null) {
                val price =
                    productsList[position].price.value.toString() + " " + productsList[position].price.currency + "US"

                binding.tvOldPrice.typeface =
                    ResourcesCompat.getFont(context, R.font.roboto_medium)
                binding.tvOldPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                binding.tvDot.visibility = View.GONE
                binding.tvOldPrice.text = price
            } else {
                val price =
                    productsList[position].price.value.toString() + " " + productsList[position].price.currency + "US"
                val oldPrice =
                    productsList[position].oldPrice?.value.toString() + " " + productsList[position].oldPrice?.currency + "US"

                binding.tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                binding.tvOldPrice.text = oldPrice
                binding.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.tvDiscount.text = productsList[position].discount
                binding.tvNewPrice.text = price

                binding.root.setOnClickListener {
                    itemClickListener.invoke()
                }


            }

        }


    }

    private fun getRating(rating: String): Float {
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