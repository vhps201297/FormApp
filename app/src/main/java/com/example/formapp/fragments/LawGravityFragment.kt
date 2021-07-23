package com.example.formapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formapp.R
import com.example.formapp.utils.ListenerFragments
import com.example.formapp.databinding.FragmentLawGravityBinding
import com.example.formapp.utils.TextWatcherEditText

class LawGravityFragment : Fragment() {

    private var _binding: FragmentLawGravityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLawGravityBinding.inflate(inflater,container, false)

        with(binding){
            edtxtM1.addTextChangedListener(TextWatcherEditText(tilM1))
            edtxtM2.addTextChangedListener(TextWatcherEditText(tilM2))
            edtxtR.addTextChangedListener(TextWatcherEditText(tilR))
        }
        return binding.root
    }

    fun getValues(listener: ListenerFragments){
        with(binding){
            if (edtxtM1.text.toString().isEmpty()){
                tilM1.error = getString(R.string.str_validation_void, "M")
            } else if (edtxtM2.text.toString().isEmpty()){
                tilM2.error = getString(R.string.str_validation_void, "m")
            } else if (edtxtR.text.toString().isEmpty()){
                tilR.error = getString(R.string.str_validation_void, "r")
            } else if (edtxtR.text.toString().toFloat() == 0F){
                tilR.error = getString(R.string.str_no_cero, "r")
            }
            else{
                var bundle = Bundle()
                bundle.putFloat("M", edtxtM1.text.toString().toFloat())
                bundle.putFloat("m", edtxtM2.text.toString().toFloat())
                bundle.putFloat("r", edtxtR.text.toString().toFloat())
                listener.isValidated(bundle)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}