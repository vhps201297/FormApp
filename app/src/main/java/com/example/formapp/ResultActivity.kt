package com.example.formapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.formapp.databinding.ActivityResultBinding
import com.example.formapp.utils.Constants.POSITION_LAW_GRAVITY
import com.example.formapp.utils.Constants.POSITION_PRISM_PENTA
import com.example.formapp.utils.Constants.STRING_KEY_LAW_GRAVITY
import com.example.formapp.utils.Constants.STRING_KEY_PENTA

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pos = intent.extras!!.getInt("position")

        when(pos){
            POSITION_LAW_GRAVITY -> setDetailsGravity()
            POSITION_PRISM_PENTA -> setDetailsPenta()
        }

    }

    private fun setDetailsGravity(){
        bundle = intent.getBundleExtra(STRING_KEY_LAW_GRAVITY)
        val m1 = bundle!!.getString("M")
        val m2 = bundle!!.getString("m")
        val r = bundle!!.getString("r")
        with(binding){
            layout.imgCardResult.setImageResource(R.drawable.ley_grav)
            layout.tvNameForm.text = getString(R.string.str_name_form, "Fuerza entre dos cuerpos (Ley de Gravitación universal)")
            layout.tvFirstParam.text = m1
            layout.tvSecondParam.text = m2
            layout.tvThirdParam.text = r
            layout.tvResult.text = calculateLGU(m1!!.toFloat(), m2!!.toFloat(),r!!.toFloat()).toString()
        }
    }

    fun calculateLGU(m1: Float, m2: Float, r: Float):Double{
        val G =  6.67384E-11
        return G * ((m1*m2)/(Math.pow(r.toDouble(),2.0)))
    }

    fun calculateVolPP(a: Float, l: Float, h: Float):Double{
        return (5*a*l*h)/2.0
    }

    private fun setDetailsPenta(){
        bundle = intent.getBundleExtra(STRING_KEY_PENTA)
        val a = bundle!!.getString("a")
        val l = bundle!!.getString("l")
        val h = bundle!!.getString("h")
        val res = calculateLGU(a!!.toFloat(), l!!.toFloat(),h!!.toFloat()).toString()
        with(binding){
            layout.imgCardResult.setImageResource(R.drawable.ley_grav)
            layout.tvNameForm.text = getString(R.string.str_name_form, "Volumen de un prisma pentagonal")
            layout.tvFirstParam.text = getString(R.string.str_parametros, "a",a)
            layout.tvSecondParam.text = getString(R.string.str_name_form, "l",l)
            layout.tvThirdParam.text = getString(R.string.str_parametros, "h",h)
            layout.tvResult.text = getString(R.string.str_resultado, "Volumen", res)
        }
    }

    fun onClickBack(view: View) {
        finish()
    }
}