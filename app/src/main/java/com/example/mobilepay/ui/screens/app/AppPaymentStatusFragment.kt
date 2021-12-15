package com.example.mobilepay.ui.screens.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mobilepay.R
import com.example.mobilepay.databinding.AppPaymentStatusFragmentBinding
import com.example.mobilepay.ui.screens.app.viewmodel.AppViewModel


class AppPaymentStatusFragment : Fragment() {

    companion object {
        fun newInstance() = AppPaymentStatusFragment()
    }

    private val appViewModel: AppViewModel by activityViewModels()
    private lateinit var binding: AppPaymentStatusFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AppPaymentStatusFragmentBinding.inflate(inflater, container, false)
        appViewModel.isPaymentSuccessful.value?.let { isPaymentSuccessful ->
            if (isPaymentSuccessful) {
                binding.paymentStatusImageview.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.check_mark_icon
                    )
                )
            } else {
                binding.paymentStatusImageview.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.cross_mark_icon
                    )
                )
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appViewModel.clearStatus()
        binding.returnToHomeButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.app_container, AppHomeFragment.newInstance()).commit()
        }
    }

}