package com.example.formapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.formapp.databinding.ActivityMainBinding
import com.example.formapp.databinding.FragmentLawGravityBinding
import com.example.formapp.fragments.LawGravityFragment
import com.example.formapp.fragments.PentagonalPrismFragment

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // Agregando ViewBinding
    private lateinit var binding: ActivityMainBinding;
    lateinit var fragmentLawGravityBinding: FragmentLawGravityBinding
    var positionSpinner = MainActivity.POSITION_NONE
    var currentFragment: Fragment? = null
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

        ArrayAdapter.createFromResource(
            this, R.array.formulas_array, android.R.layout.simple_spinner_item).also {
            arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerForm.adapter = arrayAdapter
        }



    }

    fun onCLickCalculate(view: View) {
        when(positionSpinner){

        }

    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        positionSpinner = position
        when(position){
            POSITION_LAW_GRAVITY->
                fragmentTransaction.add(R.id.frame_container, LawGravityFragment()).commit()
            POSITION_PRISM_PENTA-> fragmentTransaction.add(R.id.frame_container, PentagonalPrismFragment()).commit()
            else->null
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}