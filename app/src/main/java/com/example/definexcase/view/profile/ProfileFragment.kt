package com.example.definexcase.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.definexcase.R
import com.example.definexcase.consts.Constants
import com.example.definexcase.databinding.FragmentProfileBinding
import com.example.definexcase.util.saveData
import com.example.definexcase.view.authentication.AuthenticationActivity

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        setListeners()
    }

    private fun setListeners() {
        binding.btnLogOut.setOnClickListener {
            saveData(Constants.TOKEN, "", requireContext())
            val intent = Intent(requireContext(), AuthenticationActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

}