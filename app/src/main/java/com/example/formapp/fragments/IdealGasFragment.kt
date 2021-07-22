package com.example.formapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.formapp.R
import com.example.formapp.databinding.FragmentIdealGasBinding
import com.example.formapp.utils.Constants.FG_POSITION_N
import com.example.formapp.utils.Constants.FG_POSITION_P
import com.example.formapp.utils.Constants.FG_POSITION_T
import com.example.formapp.utils.Constants.FG_POSITION_V
import com.example.formapp.utils.ListenerFragments
import com.example.formapp.utils.TextWatcherEditText

class IdealGasFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentIdealGasBinding? = null
    private val binding get() = _binding!!
    var listenerChangeItem: ListenerFragments.Spinner? = null
    private var itemSelected: Int = FG_POSITION_P
    private var viewP: EditText? = null
    private var viewV: EditText? = null
    private var viewN: EditText? = null
    private var viewT: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIdealGasBinding.inflate(inflater, container, false)

        (binding.spinnerIdealGas.editText as AutoCompleteTextView).onItemSelectedListener = this
        val items = listOf("Presion (P)", "Volumen (V)", "n (Moles de gas)", "Temperatura (T)")
        val arrayAdapter = ArrayAdapter<String>(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        arrayAdapter.addAll(items)

        with(binding){
            (spinnerIdealGas.editText as AutoCompleteTextView).setAdapter(arrayAdapter)

            edtxt1.addTextChangedListener(TextWatcherEditText(tilIdeal1))
            edtxt2.addTextChangedListener(TextWatcherEditText(tilIdeal2))
            edtxt3.addTextChangedListener(TextWatcherEditText(tilIdeal3))
        }

        return binding.root
    }


    fun getValues(listener: ListenerFragments){
        with(binding){
            if (edtxt1.text.toString().isEmpty()){
                tilIdeal1.error = "ingrese el dato"
            } else if (edtxt2.text.toString().isEmpty()){
                tilIdeal2.error = "Ingrese el dato"
            } else if (edtxt3.text.toString().isEmpty()){
                tilIdeal3.error = "ingrese el dato"
            } else{
                var bundle = Bundle()
                bundle.putFloat("P", viewP!!.text.toString().toFloat())
                bundle.putFloat("V", viewV!!.text.toString().toFloat())
                bundle.putFloat("N", viewN!!.text.toString().toFloat())
                bundle.putFloat("T", viewT!!.text.toString().toFloat())
                bundle.putInt("item_select",itemSelected)
                listener.isValidated(bundle)
                Toast.makeText(requireContext(), "Se enviaron los datos", Toast.LENGTH_SHORT).show()

            }
        }

    }

    fun updateHints(hintForEdtxt1: String, hintForEdtxt2: String, hintForEdtxt3: String){
        with(binding){
            edtxt1.hint = getString(R.string.str_set_hint, hintForEdtxt1)
            edtxt2.hint = getString(R.string.str_set_hint, hintForEdtxt2)
            edtxt3.hint = getString(R.string.str_set_hint, hintForEdtxt3)
        }
    }

    fun updatePositionViews(viewP: EditText?, viewV:EditText?, viewN:EditText?, viewT:EditText?){
        this.viewP = viewP
        this.viewV = viewV
        this.viewN = viewN
        this.viewT = viewT
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.e("SELECTED_ITEM", "Position: $position")
        this.itemSelected = position
        when(parent!!.selectedItemPosition){
            FG_POSITION_P ->{
                updateHints(getString(R.string.str_hint_v),getString(R.string.str_hint_n),getString(R.string.str_hint_t))
                updatePositionViews(null, binding.edtxt1, binding.edtxt2, binding.edtxt3)
                listenerChangeItem!!.changeItemSelected(FG_POSITION_P)
            }
            FG_POSITION_V ->{
                updateHints(getString(R.string.str_hint_p),getString(R.string.str_hint_n),getString(R.string.str_hint_t))
                updatePositionViews(binding.edtxt1, null,binding.edtxt2, binding.edtxt3)
                listenerChangeItem!!.changeItemSelected(FG_POSITION_V)
            }

            FG_POSITION_N ->{
                updateHints(getString(R.string.str_hint_p),getString(R.string.str_hint_v),getString(R.string.str_hint_t))
                updatePositionViews(binding.edtxt1, binding.edtxt2,null, binding.edtxt3)
                listenerChangeItem!!.changeItemSelected(FG_POSITION_N)
            }

            FG_POSITION_T ->{
                updateHints(getString(R.string.str_hint_p),getString(R.string.str_hint_v),getString(R.string.str_hint_n))
                updatePositionViews(binding.edtxt1, binding.edtxt2,binding.edtxt3, null)
                listenerChangeItem!!.changeItemSelected(FG_POSITION_T)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}