package com.example.formapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.formapp.databinding.ActivityMainBinding
import com.example.formapp.fragments.IdealGasFragment
import com.example.formapp.fragments.LawGravityFragment
import com.example.formapp.fragments.PentagonalPrismFragment
import com.example.formapp.utils.Constants.FG_POSITION_N
import com.example.formapp.utils.Constants.FG_POSITION_P
import com.example.formapp.utils.Constants.FG_POSITION_T
import com.example.formapp.utils.Constants.FG_POSITION_V
import com.example.formapp.utils.Constants.POSITION_IDEAL_GAS
import com.example.formapp.utils.Constants.POSITION_LAW_GRAVITY
import com.example.formapp.utils.Constants.POSITION_PRISM_PENTA
import com.example.formapp.utils.Constants.STRING_KEY_IDEAL_GAS
import com.example.formapp.utils.Constants.STRING_KEY_LAW_GRAVITY
import com.example.formapp.utils.Constants.STRING_KEY_PENTA
import com.example.formapp.utils.ListenerFragments

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, ListenerFragments.Spinner {

    // Agregando ViewBinding
    private lateinit var binding: ActivityMainBinding
    private var fgGravityFragment = LawGravityFragment()
    private var fgPentagonalPrism = PentagonalPrismFragment()
    private var fgIdealGas = IdealGasFragment()
    private var positionSpinner = POSITION_LAW_GRAVITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinnerForm.onItemSelectedListener = this
        fgIdealGas.listenerChangeItem = this
        ArrayAdapter.createFromResource(
            this, R.array.formulas_array, android.R.layout.simple_spinner_item).also {
            arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerForm.adapter = arrayAdapter

        }

        //supportFragmentManager.beginTransaction().replace(binding.frameContainer.id, fgGravityFragment).commit()
    }

    fun onCLickCalculate(view: View) {
        when(positionSpinner){
            POSITION_LAW_GRAVITY ->
                fgGravityFragment.getValues(object : ListenerFragments {
                    override fun isValidated(bundle: Bundle) {
                        sendValuesOnIntent(STRING_KEY_LAW_GRAVITY, bundle)
                    }
                })

            POSITION_PRISM_PENTA ->
                fgPentagonalPrism.getValues(object : ListenerFragments {
                    override fun isValidated(bundle: Bundle) {
                        sendValuesOnIntent(STRING_KEY_PENTA, bundle)
                    }
                })
            POSITION_IDEAL_GAS ->
                fgIdealGas.getValues(object: ListenerFragments{
                    override fun isValidated(bundle: Bundle) {
                        sendValuesOnIntent(STRING_KEY_IDEAL_GAS, bundle)
                    }

                })
        }
    }

    fun sendValuesOnIntent(idBundle: String, bundle: Bundle){
        val intent = Intent(this@MainActivity, ResultActivity::class.java)
        intent.putExtra(idBundle, bundle)
        intent.putExtra("position", positionSpinner)
        startActivity(intent)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        positionSpinner = position
        when(parent!!.selectedItemPosition){
            POSITION_LAW_GRAVITY-> {
                binding.tvNameForm.text = getString(R.string.str_name_form, "Ley de gravitación universal)")
                binding.imgForm.setImageResource(R.drawable.ley_grav)
                fragmentTransaction.replace(R.id.frame_container, fgGravityFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
            }
            POSITION_PRISM_PENTA-> {
                binding.tvNameForm.text = getString(R.string.str_name_form, "Volumen de prisma pentagonal")
                binding.imgForm.setImageResource(R.drawable.form_prism_pent)
                fragmentTransaction.replace(R.id.frame_container, fgPentagonalPrism)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
            }
            POSITION_IDEAL_GAS-> {
                binding.tvNameForm.text = getString(R.string.str_name_form, "Ecuación de los gases ideales")
                binding.imgForm.setImageResource(R.drawable.img_ideal_gas)
                fragmentTransaction.replace(R.id.frame_container, fgIdealGas)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    /**
     * Listener para el cammbió del spinner que se encuentra dentro del fragment IdealFragment
     * @param itemSelect Variable que alberga la posición del spinner
     */
    override fun changeItemSelected(itemSelect: Int) {
        when(itemSelect){
            FG_POSITION_P-> binding.imgForm.setImageResource(R.drawable.form_p)
            FG_POSITION_V-> binding.imgForm.setImageResource(R.drawable.form_v)
            FG_POSITION_N-> binding.imgForm.setImageResource(R.drawable.form_n)
            FG_POSITION_T-> binding.imgForm.setImageResource(R.drawable.form_t)
        }
    }
}