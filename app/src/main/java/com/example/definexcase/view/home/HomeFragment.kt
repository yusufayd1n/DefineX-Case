package com.example.definexcase.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.definexcase.base.BaseFragment
import com.example.definexcase.R
import com.example.definexcase.adapter.ProductsAdapter
import com.example.definexcase.adapter.SecondProductsAdapter
import com.example.definexcase.adapter.ThirdProductsAdapter
import com.example.definexcase.api.model.listResponse.ListItems
import com.example.definexcase.consts.Constants.Companion.TOKEN
import com.example.definexcase.databinding.FragmentHomeBinding
import com.example.definexcase.util.checkForInternet
import com.example.definexcase.util.loadData
import com.example.definexcase.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel


class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by stateViewModel()
    var token: String = ""
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        token = loadData(requireContext(), TOKEN).toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeAPIRequests()
        setListeners()
        setObservers()
        checkForInternetInFragment()
    }

    private fun checkForInternetInFragment() {
        if (!checkForInternet(requireContext())) {
            Toast.makeText(
                requireContext(),
                getString(R.string.internet_connection_error),
                Toast.LENGTH_LONG
            ).show()
        }
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
            checkForInternetInFragment()
        }
    }

    private fun setObservers() {
        viewModel.firstListLiveData.observe(viewLifecycleOwner) { response ->
            if (viewModel.firstListError.value == false) {
                setAdapter(response, binding.rvFirstProducts)
            } else {
                setAdapter(response, binding.rvFirstProducts)
                Log.d("FirstListError", viewModel.firstListError.toString())
            }
        }

        viewModel.secondListLiveData.observe(viewLifecycleOwner) { response ->
            if (viewModel.secondListError.value == false) {
                setSecondAdapter(response, binding.rvSecondProducts)
            } else {
                setSecondAdapter(response, binding.rvSecondProducts)
                Log.d("SecondListError", viewModel.secondListError.toString())
            }
        }

        viewModel.thirdListLiveData.observe(viewLifecycleOwner) { response ->
            if (viewModel.thirdListError.value == false) {
                setThirdAdapter(response, binding.rvThirdProducts)
            } else {
                setThirdAdapter(response, binding.rvThirdProducts)
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

        viewModel.thirdListLoading.observe(viewLifecycleOwner) { loading ->
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

    private fun setAdapter(response: List<ListItems>, rv: RecyclerView) {
        val productsAdapter = ProductsAdapter(response, requireContext()) {
            Toast.makeText(requireContext(), R.string.soon, Toast.LENGTH_SHORT).show()
        }
        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = productsAdapter
        binding.rvFirstProducts.visibility = View.VISIBLE
    }

    private fun setSecondAdapter(response: List<ListItems>, rv: RecyclerView) {
        val productsAdapter = SecondProductsAdapter(response, requireContext()) {
            Toast.makeText(requireContext(), R.string.soon, Toast.LENGTH_SHORT).show()
        }
        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = productsAdapter
        binding.rvSecondProducts.visibility = View.VISIBLE
    }

    private fun setThirdAdapter(response: List<ListItems>, rv: RecyclerView) {
        val productsAdapter = ThirdProductsAdapter(response, requireContext()) {
            Toast.makeText(requireContext(), R.string.soon, Toast.LENGTH_SHORT).show()
        }
        rv.layoutManager =
            GridLayoutManager(requireContext(), 2)
        rv.adapter = productsAdapter
        binding.rvThirdProducts.visibility = View.VISIBLE
    }


}