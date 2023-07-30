package com.example.definexcase.view.home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.definexcase.R
import com.example.definexcase.adapter.ProductsAdapter
import com.example.definexcase.api.model.FirstListResponse
import com.example.definexcase.consts.Constants.Companion.TOKEN
import com.example.definexcase.databinding.FragmentHomeBinding
import com.example.definexcase.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    var token: String = ""
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        token = loadData(requireContext(), TOKEN).toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.getFirstList(token)
        setObservers()
    }

    private fun setObservers() {
        viewModel.firstListLiveData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccess) {
                setAdapter(response)
            } else {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setAdapter(response: FirstListResponse) {
        val productsAdapter = ProductsAdapter(response, requireContext())
        binding.rvProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducts.adapter = productsAdapter
    }

    private fun loadData(context: Context, key: String): String? {
        val sharedPreferences =
            context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

}