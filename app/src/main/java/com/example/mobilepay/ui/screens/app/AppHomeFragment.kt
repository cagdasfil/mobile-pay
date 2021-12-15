package com.example.mobilepay.ui.screens.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.mobilepay.R
import com.example.mobilepay.databinding.AppHomeFragmentBinding
import com.example.mobilepay.ui.screens.app.viewmodel.AppViewModel
import com.example.mobilepay.ui.screens.pos.viewmodel.PosViewModel
import com.example.mobilepay.ui.utils.extensions.orElse

class AppHomeFragment : Fragment() {

    companion object {
        fun newInstance() = AppHomeFragment()
    }

    private val posViewModel: PosViewModel by activityViewModels()
    private val appViewModel: AppViewModel by activityViewModels()
    private lateinit var binding: AppHomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AppHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.scanButton.setOnClickListener {
            posViewModel.qrData.value?.let { qrData ->
                showProgressIndicator()
                appViewModel.startPayment(qrData)
            }.orElse {
                Toast.makeText(context, getString(R.string.toast_message), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.seeTransactionsButton.setOnClickListener {

        }

        appViewModel.isPaymentSuccessful.observe(viewLifecycleOwner, { isPaymentSuccessful ->
            isPaymentSuccessful?.let {
                hideProgressIndicator()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.app_container, AppPaymentStatusFragment.newInstance()).commit()
            }
        })

    }

    private fun showProgressIndicator() {
        binding.progressCircular.isVisible = true
    }

    private fun hideProgressIndicator() {
        binding.progressCircular.isVisible = false
    }

}