package com.example.definexcase.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.definexcase.base.BaseFragment
import com.example.definexcase.R
import com.example.definexcase.adapter.ProductsAdapter
import com.example.definexcase.adapter.SecondProductsAdapter
import com.example.definexcase.adapter.ThirdProductsAdapter
import com.example.definexcase.api.model.ListResponse
import com.example.definexcase.consts.Constants.Companion.TOKEN
import com.example.definexcase.databinding.FragmentHomeBinding
import com.example.definexcase.viewmodel.HomeViewModel


class HomeFragment : BaseFragment(R.layout.fragment_home) {
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
        makeAPIRequests()
        setListeners()
        setObservers()
    }

    private fun makeAPIRequests() {
        viewModel.getFirstList(token)
        viewModel.getSecondList(token)
        viewModel.getThirdList(token)
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.rvFirstProducts.visibility = View.GONE
            binding.rvSecondProducts.visibility = View.GONE
            binding.rvThirdProducts.visibility = View.GONE
            binding.listLoading.visibility = View.VISIBLE
            makeAPIRequests()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setObservers() {
        viewModel.firstListLiveData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccess) {
                setAdapter(response, binding.rvFirstProducts)
            } else {
                Log.d("FirstListError", viewModel.firstListError.toString())
            }
        }

        viewModel.secondListLiveData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccess) {
                setSecondAdapter(response, binding.rvSecondProducts)
            } else {
                Log.d("SecondListError", viewModel.secondListError.toString())
            }
        }

        viewModel.thirdListLiveData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccess) {
                setThirdAdapter(response, binding.rvThirdProducts)
            } else {
                Log.d("ThirdListError", viewModel.secondListError.toString())
            }
        }

        viewModel.firstListLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    binding.listLoading.visibility = View.VISIBLE
                } else {
                    binding.rvFirstProducts.visibility = View.VISIBLE
                    binding.listLoading.visibility = View.GONE
                }
            }
        }

        viewModel.secondListLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    binding.listLoading.visibility = View.VISIBLE
                } else {
                    binding.rvSecondProducts.visibility = View.VISIBLE
                    binding.listLoading.visibility = View.GONE
                }
            }
        }

        viewModel.secondListLoading.observe(viewLifecycleOwner) { loading ->
            loading?.let {
                if (it) {
                    binding.listLoading.visibility = View.VISIBLE
                } else {
                    binding.rvThirdProducts.visibility = View.VISIBLE
                    binding.listLoading.visibility = View.GONE
                }
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

    private fun setAdapter(response: ListResponse, rv: RecyclerView) {
        val productsAdapter = ProductsAdapter(response, requireContext())
        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = productsAdapter
    }

    private fun setSecondAdapter(response: ListResponse, rv: RecyclerView) {
        val productsAdapter = SecondProductsAdapter(response, requireContext())
        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = productsAdapter
    }

    private fun setThirdAdapter(response: ListResponse, rv: RecyclerView) {
        val productsAdapter = ThirdProductsAdapter(response, requireContext())
        rv.layoutManager =
            GridLayoutManager(requireContext(), 2)
        rv.adapter = productsAdapter
    }

    private fun loadData(context: Context, key: String): String? {
        val sharedPreferences =
            context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

}