package com.example.definexcase.ui.authentication

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.definexcase.R
import com.example.definexcase.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paintTextView()
        setListeners()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = binding.root
        return view

    }

    private fun paintTextView() {
        val paint = binding.tvCase.paint
        val width = paint.measureText(binding.tvCase.text.toString())
        val textShader: Shader = LinearGradient(
            0f, 0f, width, binding.tvCase.textSize, intArrayOf(
                Color.parseColor("#1A73E9"),
                Color.parseColor("#6C92F4")
            ), null, Shader.TileMode.REPEAT
        )
        binding.tvCase.paint.shader = textShader
    }

    private fun setListeners() {
        binding.etLoginEmail.setOnFocusChangeListener { _, focus ->
            if (focus) {
                binding.tilLoginEmail.boxStrokeColor =
                    ContextCompat.getColor(requireContext(), R.color.light_blue)
                binding.tilLoginEmail.setEndIconTintList(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.active_end_icon
                        )
                    )
                )
            } else {
                binding.tilLoginEmail.setEndIconTintList(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.passive_end_icon
                        )
                    )
                )
            }
        }

        binding.etLoginEmail.addTextChangedListener {
            if (!isEmailValid(it.toString().trim())) {
                binding.tilLoginEmail.boxStrokeColor =
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.error_red
                    )
                binding.tilLoginEmail.hintTextColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.error_red
                    )
                )
                binding.tilLoginEmail.hint = getString(R.string.email_error)
            } else {
                binding.tilLoginEmail.boxStrokeColor =
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.light_green
                    )
                binding.tilLoginEmail.hintTextColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tilLoginEmail.hint = getString(R.string.email)
            }

        }

        binding.etLoginPassword.setOnFocusChangeListener { _, focus ->
            if (focus) {
                binding.tilLoginPassword.boxStrokeColor =
                    ContextCompat.getColor(requireContext(), R.color.light_blue)
                binding.tilLoginPassword.setEndIconTintList(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.black
                        )
                    )
                )
            } else {
                binding.tilLoginPassword.setEndIconTintList(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.passive_end_icon
                        )
                    )
                )
            }
        }

        binding.etLoginPassword.addTextChangedListener {
            if (it?.length!! < 6) {
                binding.tilLoginPassword.boxStrokeColor =
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.error_red
                    )
                binding.tilLoginPassword.hintTextColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.error_red
                    )
                )
                binding.tilLoginPassword.hint = getString(R.string.password_error)
            } else {
                binding.tilLoginPassword.boxStrokeColor =
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.light_green
                    )
                binding.tilLoginPassword.hintTextColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )
                binding.tilLoginPassword.hint = getString(R.string.password)
            }

        }
    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()
    }

}