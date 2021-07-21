package com.example.formapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.formapp.databinding.ActivityMainBinding
import com.example.formapp.fragments.LawGravityFragment
import com.example.formapp.fragments.PentagonalPrismFragment
import com.example.formapp.utils.Constants.POSITION_LAW_GRAVITY
import com.example.formapp.utils.Constants.POSITION_PRISM_PENTA
import com.example.formapp.utils.Constants.STRING_KEY_LAW_GRAVITY
import com.example.formapp.utils.Constants.STRING_KEY_PENTA
import com.example.formapp.utils.ListenerFragments

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // Agregando ViewBinding
    private lateinit var binding: ActivityMainBinding
    var fgGravityFragment = LawGravityFragment()
    var fgPentagonalPrism = PentagonalPrismFragment()
    var positionSpinner = POSITION_LAW_GRAVITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinnerForm.onItemSelectedListener = this
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
                binding.imgForm.setImageResource(R.drawable.ley_grav)
                fragmentTransaction.replace(R.id.frame_container, fgGravityFragment)
                    .addToBackStack(null)
                    .commit()
            }
            POSITION_PRISM_PENTA-> {
                binding.imgForm.setImageResource(R.drawable.form_prism_pent)
                fragmentTransaction.replace(R.id.frame_container, fgPentagonalPrism)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}