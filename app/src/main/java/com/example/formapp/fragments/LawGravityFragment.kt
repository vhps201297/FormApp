package com.example.formapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.formapp.ListenerFragments
import com.example.formapp.R
import com.example.formapp.databinding.FragmentLawGravityBinding
import com.example.formapp.utils.TextWatcherEditText
import com.google.android.material.textfield.TextInputLayout

class LawGravityFragment : Fragment() {

    companion object {
        @JvmStatic
        fun getData(listener: ListenerFragments) =
            LawGravityFragment().apply {
                this.listener = listener
            }
    }
    private var listener: ListenerFragments? = null
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
                tilM1.error = "Se debe ingresar un valor para M"
            } else if (edtxtM2.text.toString().isEmpty()){
                tilM2.error = "Se debe ingresar un valor para m"
            } else if (edtxtR.text.toString().isEmpty()){
                tilR.error = "Se debe ingresar un valor para r"
            } else{
                var bundle = Bundle()
                bundle.putString("M", edtxtM1.text.toString())
                bundle.putString("m", edtxtM2.text.toString())
                bundle.putString("r", edtxtR.text.toString())
                listener.isValidated(bundle)
                //return bundle
            }
            //return null
        }
    }

    fun watchErrorsTil(){
        with(binding){
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}