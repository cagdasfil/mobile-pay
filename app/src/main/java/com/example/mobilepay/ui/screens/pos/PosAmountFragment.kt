package com.example.mobilepay.ui.screens.pos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.mobilepay.R
import com.example.mobilepay.databinding.PosAmountFragmentBinding
import com.example.mobilepay.ui.screens.pos.viewmodel.PosViewModel

class PosAmountFragment : Fragment() {

    companion object {
        fun newInstance() = PosAmountFragment()
    }

    private val posViewModel: PosViewModel by activityViewModels()
    private lateinit var binding: PosAmountFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PosAmountFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.generateQrButton.setOnClickListener {
                val amountText = binding.amountTextInput.editText?.text.toString()
                if (amountText.isNotEmpty()) {
                    val amount = amountText.toInt()
                    showProgressIndicator()
                    posViewModel.getQR(amount = amount)
                } else {
                    binding.amountTextInput.error = "Please enter an amount!"
                }
            }

        posViewModel.qrData.observe(viewLifecycleOwner, { qrData ->
            qrData?.let {
                hideProgressIndicator()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.pos_container, PosQRFragment.newInstance()).commit()
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