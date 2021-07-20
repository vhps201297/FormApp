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
    private lateinit var binding: ActivityMainBinding;
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
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fgGravityFragment).commit()



    }

    fun onCLickCalculate(view: View) {
        when(positionSpinner){
            POSITION_LAW_GRAVITY ->
                fgGravityFragment.getValues(object : ListenerFragments{
                    override fun isValidated(bundle: Bundle) {
                        val intent = Intent(this@MainActivity, ResultActivity::class.java)
                        intent.putExtra("bundle_gravity", bundle)
                        startActivity(intent)
                    }

                })
                POSITION_PRISM_PENTA -> Toast.makeText(this@MainActivity, "TOdavía no integradp", Toast.LENGTH_SHORT).show()
        }

    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()

        when(parent!!.id){
            R.id.spinner_form ->
                when(position){
                    POSITION_LAW_GRAVITY->
                        fragmentTransaction.replace(R.id.frame_container, fgGravityFragment).addToBackStack(null).commit()
                    POSITION_PRISM_PENTA-> fragmentTransaction.replace(R.id.frame_container, fgPentagonalPrism).commit()

                }



        }
        positionSpinner = position



    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}