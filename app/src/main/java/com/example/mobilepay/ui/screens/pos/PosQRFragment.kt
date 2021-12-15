package com.example.mobilepay.ui.screens.pos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mobilepay.R
import com.example.mobilepay.databinding.PosQrFragmentBinding
import com.example.mobilepay.ui.screens.pos.model.QRData
import com.example.mobilepay.ui.screens.pos.viewmodel.PosViewModel
import com.example.mobilepay.ui.utils.QRGenerator
import kotlinx.coroutines.launch

class PosQRFragment : Fragment() {

    companion object {
        fun newInstance() = PosQRFragment()
    }

    private val posViewModel: PosViewModel by activityViewModels()
    private lateinit var binding: PosQrFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PosQrFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        posViewModel.qrData.value?.let { qrData ->
            viewLifecycleOwner.lifecycleScope.launch {
                setQRBitmap(binding.qrImageview, qrData)
            }
        }

        binding.generateNewQrButton.setOnClickListener {
            posViewModel.clearQRData()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.pos_container, PosAmountFragment.newInstance()).commit()
        }
    }

    private fun setQRBitmap(imageView: ImageView, qrData: QRData) {
        val qrBitmap = QRGenerator.getQRBitmapByString(qrData.QRdata)
        imageView.setImageBitmap(qrBitmap)
    }

}