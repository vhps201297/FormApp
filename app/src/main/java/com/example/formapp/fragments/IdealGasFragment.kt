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
import com.example.formapp.utils.Constants
import com.example.formapp.utils.Constants.FG_POSITION_N
import com.example.formapp.utils.Constants.FG_POSITION_P
import com.example.formapp.utils.Constants.FG_POSITION_T
import com.example.formapp.utils.Constants.FG_POSITION_V
import com.example.formapp.utils.Constants.POSITION_NONE
import com.example.formapp.utils.FormCalculator
import com.example.formapp.utils.ListenerFragments
import com.example.formapp.utils.TextWatcherEditText
import com.google.android.material.textfield.TextInputLayout

class IdealGasFragment : Fragment(), AdapterView.OnItemClickListener {

    private var _binding: FragmentIdealGasBinding? = null
    var listenerChangeItem: ListenerFragments.Spinner? = null
    private val binding get() = _binding!!
    private var itemSelected: Int = POSITION_NONE
    private var tilZero: TextInputLayout? = null
    private var key_calculate: String = ""
    private lateinit var errorEdtxt1: String
    private lateinit var errorEdtxt2: String
    private lateinit var errorEdtxt3: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIdealGasBinding.inflate(inflater, container, false)

        val items = listOf(getString(R.string.str_item_p), getString(R.string.str_item_v), getString(R.string.str_item_n), getString(R.string.str_item_t))
        val arrayAdapter = ArrayAdapter<String>(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        arrayAdapter.addAll(items)

        with(binding){
            (spinnerIdealGas.editText as AutoCompleteTextView).setAdapter(arrayAdapter)
            edtxt1.addTextChangedListener(TextWatcherEditText(tilIdeal1))
            edtxt2.addTextChangedListener(TextWatcherEditText(tilIdeal2))
            edtxt3.addTextChangedListener(TextWatcherEditText(tilIdeal3))
            spinnerIdealGas.editText!!.addTextChangedListener(TextWatcherEditText(spinnerIdealGas))
            (spinnerIdealGas.editText as? AutoCompleteTextView)?.setOnItemClickListener(this@IdealGasFragment)
            initViews()
        }
        return binding.root
    }

    /**
     * Funci??n encargada de devolver los par??metros de la ecuaci??n de gases ideales, validando
     * las variables del fragment, y llenando los par??metros para realizar os c??lculos y pasarlos
     * en un Bundle.
     *
     * @param listener interfaz encargada de devolver el bundle con los par??metros necesarios para
     * pintarlos en la vista de resultados.
     */
    fun getValues(listener: ListenerFragments){
        if (isDataValidate()){
            when(key_calculate){
                "P" ->{
                    val mapTmp = fillParams("V","n", "T")
                    FormCalculator.calculateIdealGas("P", mapTmp)
                }
                "V" ->{
                    val mapTmp = fillParams("P","n", "T")
                    FormCalculator.calculateIdealGas("V", mapTmp)
                }
                "n" ->{
                    val mapTmp = fillParams("P","V", "T")
                    FormCalculator.calculateIdealGas("n", mapTmp)
                }
                "T" ->{
                    val mapTmp = fillParams("P", "V", "n")
                    FormCalculator.calculateIdealGas("T", mapTmp)
                }
            }

            var bundle = Bundle()
            FormCalculator.params?.get("P")?.let { bundle.putFloat("P", it) }
            FormCalculator.params?.get("V")?.let { bundle.putFloat("V", it) }
            FormCalculator.params?.get("n")?.let { bundle.putFloat("n", it) }
            FormCalculator.params?.get("T")?.let { bundle.putFloat("T", it) }
            bundle.putInt("item_select",itemSelected)
            listener.isValidated(bundle)
        }
    }

    /**
     * Funci??n encargada de llenar los par??metros en un hashmap, el cual se utiliza como par??metro
     * dentro de la clase FormCalculator para realizar los c??lculos necesarios para cada despeje.
     */
    fun fillParams(key_edtxt1: String, key_edtxt2: String, key_edtxt3: String): HashMap<String,Float>{
        with(binding){
            val hashTmp = HashMap<String, Float>()
            hashTmp.put(key_edtxt1, edtxt1.text.toString().toFloat())
            hashTmp.put(key_edtxt2, edtxt2.text.toString().toFloat())
            hashTmp.put(key_edtxt3, edtxt3.text.toString().toFloat())
            return hashTmp
        }
    }

    /**
     * Funci??n encargada de validar que los campos del fragment no est??n vac??os y que la variable
     * que le corresponda estar en el denominador no deba ser cero.
     */
    fun isDataValidate():Boolean{
        with(binding){
            if (spinnerIdealGas.editText!!.text.isEmpty()){
                spinnerIdealGas.error = getString(R.string.str_select_var)
                return false
            } else if(edtxt1.text.toString().isEmpty()){
                tilIdeal1.error = errorEdtxt1
                return false
            } else if (edtxt2.text.toString().isEmpty()){
                tilIdeal2.error = errorEdtxt2
                return false
            } else if (edtxt3.text.toString().isEmpty()){
                tilIdeal3.error = errorEdtxt3
                return false
            } else if (tilZero!!.editText!!.text.toString().toFloat() == 0F){
                tilZero!!.error = getString(R.string.str_no_cero, tilZero!!.hint)
                return false
            } else{
                return true
            }
        }
    }

    /**
     * Funci??n encargada de establecer los hints de inicio, ya que el AutoCompleteTextView no acepta
     * valores por defecto, sino fueron seleccionados con un click.
     */
    fun initViews(){
        with(binding){
            tilIdeal1.hint = getString(R.string.str_indefinido)
            tilIdeal2.hint = getString(R.string.str_indefinido)
            tilIdeal3.hint = getString(R.string.str_indefinido)
        }
    }


    /**
     * M??todo encargado de actualizar los hints de cada TextInputLayout despu??s de que se seleccione
     * que variable se quiere despejar en el AutoCompleteTextView.
     */
    fun updateHintsAndErrors(hintForEdtxt1: String, hintForEdtxt2: String, hintForEdtxt3: String){
        with(binding){
            tilIdeal1.hint = getString(R.string.str_set_hint, hintForEdtxt1)
            tilIdeal2.hint = getString(R.string.str_set_hint, hintForEdtxt2)
            tilIdeal3.hint = getString(R.string.str_set_hint, hintForEdtxt3)
            errorEdtxt1 = getString(R.string.str_set_error, hintForEdtxt1)
            errorEdtxt2 = getString(R.string.str_set_error, hintForEdtxt2)
            errorEdtxt3 = getString(R.string.str_set_error, hintForEdtxt3)
        }
    }


    /**
     * M??todo que se tiene que implementar de la clase AdapterView.OnItemClickListener el cual se
     * encarga de observar cada vez que se da un click en el AutoCompleteTextView.
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        this.itemSelected = position
        when(position){
            FG_POSITION_P ->{
                updateHintsAndErrors(getString(R.string.str_hint_v),getString(R.string.str_hint_n),getString(R.string.str_hint_t))
                tilZero = binding.tilIdeal1 // Se actualiza el view que no puede ser cero, ya que causar??a una indeterminaci??n
                key_calculate = "P"
                listenerChangeItem!!.changeItemSelected(FG_POSITION_P)
            }
            FG_POSITION_V ->{
                updateHintsAndErrors(getString(R.string.str_hint_p),getString(R.string.str_hint_n),getString(R.string.str_hint_t))
                key_calculate = "V"
                tilZero = binding.tilIdeal1
                listenerChangeItem!!.changeItemSelected(FG_POSITION_V)
            }

            FG_POSITION_N ->{
                updateHintsAndErrors(getString(R.string.str_hint_p),getString(R.string.str_hint_v),getString(R.string.str_hint_t))
                key_calculate = "n"
                tilZero = binding.tilIdeal3
                listenerChangeItem!!.changeItemSelected(FG_POSITION_N)
            }

            FG_POSITION_T ->{
                updateHintsAndErrors(getString(R.string.str_hint_p),getString(R.string.str_hint_v),getString(R.string.str_hint_n))
                key_calculate = "T"
                tilZero = binding.tilIdeal3
                listenerChangeItem!!.changeItemSelected(FG_POSITION_T)
            }
        }
    }

}