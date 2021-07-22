package com.example.formapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formapp.utils.ListenerFragments
import com.example.formapp.databinding.FragmentLawGravityBinding
import com.example.formapp.utils.TextWatcherEditText

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
            } else if (edtxtR.text.toString().toFloat() == 0F){
                tilR.error = "El valor de r no puede ser cero"
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

    fun watchErrorsTil(){
        with(binding){
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}