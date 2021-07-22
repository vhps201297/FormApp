package com.example.formapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formapp.utils.ListenerFragments
import com.example.formapp.databinding.FragmentPentagonalPrismBinding
import com.example.formapp.utils.TextWatcherEditText


class PentagonalPrismFragment : Fragment() {

    private var _binding: FragmentPentagonalPrismBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPentagonalPrismBinding.inflate(inflater, container, false)

        with(binding){
            edtxtA.addTextChangedListener(TextWatcherEditText(tilA))
            edtxtL.addTextChangedListener(TextWatcherEditText(tilL))
            edtxtH.addTextChangedListener(TextWatcherEditText(tilH))
        }
        return binding.root
    }

    fun getValues(listener: ListenerFragments){
        with(binding){
            if (edtxtA.text.toString().isEmpty()){
                tilA.error = "Se debe ingresar el lado A"
            } else if (edtxtL.text.toString().isEmpty()){
                tilL.error = "Se debe ingresar el lado L"
            } else if (edtxtH.text.toString().isEmpty()){
                tilH.error = "Se debe ingresar un valor de altura (h)"
            } else{
                var bundle = Bundle()
                bundle.putFloat("a", edtxtA.text.toString().toFloat())
                bundle.putFloat("l", edtxtL.text.toString().toFloat())
                bundle.putFloat("h", edtxtH.text.toString().toFloat())
                listener.isValidated(bundle)
            }
        }
    }

}