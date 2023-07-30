package com.example.definexcase.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.definexcase.R
import com.example.definexcase.consts.Constants.Companion.TOKEN
import com.example.definexcase.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    var token: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = loadData(requireContext(), TOKEN).toString()
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.getFirstList(token)
        setObservers()
    }

    private fun setObservers() {
        viewModel.firstListLiveData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccess) {
                Log.d("FIRSTLISTITEMS", response.toString())
            } else {
                Log.d("FIRSTLISTERROR", viewModel.firstListError.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun loadData(context: Context, key: String): String? {
        val sharedPreferences =
            context.getSharedPreferences(key, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

}