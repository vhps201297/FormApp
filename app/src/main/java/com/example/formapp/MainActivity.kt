package com.example.formapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.formapp.databinding.ActivityMainBinding
import com.example.formapp.databinding.FragmentLawGravityBinding
import com.example.formapp.fragments.LawGravityFragment
import com.example.formapp.fragments.PentagonalPrismFragment

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // Agregando ViewBinding
    private lateinit var binding: ActivityMainBinding
    var fgGravityFragment = LawGravityFragment()
    var fgPentagonalPrism = PentagonalPrismFragment()
    var positionSpinner = MainActivity.POSITION_LAW_GRAVITY
    companion object{
        private const val POSITION_LAW_GRAVITY = 0
        private const val POSITION_PRISM_PENTA = 1
        private const val POSITION_X = 2
        private const val POSITION_NONE = 3
    }

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
                fgGravityFragment.getValues(object : ListenerFragments{
                    override fun isValidated(bundle: Bundle) {
                        sendValuesOnIntent("bundle_gravity", bundle)
                    }
                })

            POSITION_PRISM_PENTA ->
                fgPentagonalPrism.getValues(object :ListenerFragments{
                    override fun isValidated(bundle: Bundle) {
                        sendValuesOnIntent("bundle_penta", bundle)
                    }
                })
        }
    }

    fun sendValuesOnIntent(idBundle: String, bundle: Bundle){
        val intent = Intent(this@MainActivity, ResultActivity::class.java)
        intent.putExtra(idBundle, bundle)
        startActivity(intent)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        Toast.makeText(this, "Position: $position", Toast.LENGTH_SHORT).show()
        when(parent!!.selectedItemPosition){
            POSITION_LAW_GRAVITY-> {
                binding.imgForm.setImageResource(R.drawable.ley_grav)
                fragmentTransaction.replace(R.id.frame_container, fgGravityFragment).addToBackStack(null).commit()
            }
            POSITION_PRISM_PENTA-> {
                binding.imgForm.setImageResource(R.drawable.form_prism_pent)
                fragmentTransaction.replace(R.id.frame_container, fgPentagonalPrism).addToBackStack(null).commit()
            }
        }
        positionSpinner = position

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}