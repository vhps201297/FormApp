package com.example.formapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.formapp.databinding.ActivityResultBinding
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
import com.example.formapp.utils.FormCalculator

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
            POSITION_IDEAL_GAS -> setDetailsIdealGas()
        }
    }

    private fun setDetailsIdealGas(){
        bundle = intent.getBundleExtra(STRING_KEY_IDEAL_GAS)
        val P = bundle!!.getFloat("P")
        val V = bundle!!.getFloat("V")
        val n = bundle!!.getFloat("n")
        val T = bundle!!.getFloat("T")

        when(bundle!!.getInt("item_select")){
            FG_POSITION_P -> {
                with(binding){
                    layout.imgCardResult.setImageResource(R.drawable.form_p)
                    layout.tvNameForm.text = getString(R.string.str_name_form, getString(R.string.str_title_ideal_gas))
                    layout.tvFirstParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_v),V)
                    layout.tvSecondParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_n),n)
                    layout.tvThirdParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_t),T)
                    layout.tvResult.text = getString(R.string.str_resultado, "P",P)
                }
            }

            FG_POSITION_V -> {
                with(binding){
                    layout.imgCardResult.setImageResource(R.drawable.form_v)
                    layout.tvNameForm.text = getString(R.string.str_name_form, getString(R.string.str_title_ideal_gas))
                    layout.tvFirstParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_p),P)
                    layout.tvSecondParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_n),n)
                    layout.tvThirdParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_t),T)
                    layout.tvResult.text = getString(R.string.str_resultado, "V",V)
                }
            }

            FG_POSITION_N -> {
                with(binding){
                    layout.imgCardResult.setImageResource(R.drawable.form_n)
                    layout.tvNameForm.text = getString(R.string.str_name_form, getString(R.string.str_title_ideal_gas))
                    layout.tvFirstParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_p),P)
                    layout.tvSecondParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_v),V)
                    layout.tvThirdParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_t),T)
                    layout.tvResult.text = getString(R.string.str_resultado, "n",n)
                }
            }
            FG_POSITION_T -> {
                with(binding){
                    layout.imgCardResult.setImageResource(R.drawable.form_t)
                    layout.tvNameForm.text = getString(R.string.str_name_form, getString(R.string.str_title_ideal_gas))
                    layout.tvFirstParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_p),P)
                    layout.tvSecondParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_v),V)
                    layout.tvThirdParam.text = getString(R.string.str_parametros, getString(R.string.str_hint_n),n)
                    layout.tvResult.text = getString(R.string.str_resultado, "T",T)
                }
            }
        }
    }


    private fun setDetailsGravity(){
        bundle = intent.getBundleExtra(STRING_KEY_LAW_GRAVITY)
        val m1 = bundle!!.getFloat("M")
        val m2 = bundle!!.getFloat("m")
        val r = bundle!!.getFloat("r")
        val res = FormCalculator.calculateLGU(m1, m2, r).toFloat()
        with(binding){
            layout.imgCardResult.setImageResource(R.drawable.ley_grav)
            layout.tvNameForm.text = getString(R.string.str_name_form, "Fuerza entre dos cuerpos (Ley de Gravitación universal)")
            layout.tvFirstParam.text = getString(R.string.str_parametros, "m1",m1)
            layout.tvSecondParam.text = getString(R.string.str_parametros, "m2",m2)
            layout.tvThirdParam.text = getString(R.string.str_parametros, "r",r)
            layout.tvResult.text = getString(R.string.str_resultado, "F",res)
        }
    }

    private fun setDetailsPenta(){
        bundle = intent.getBundleExtra(STRING_KEY_PENTA)
        val a = bundle!!.getFloat("a")
        val l = bundle!!.getFloat("l")
        val h = bundle!!.getFloat("h")
        val res = FormCalculator.calculateVolPP(a, l, h).toFloat()
        with(binding){
            layout.imgCardResult.setImageResource(R.drawable.form_prism_pent)
            layout.tvNameForm.text = getString(R.string.str_name_form, "Volumen de un prisma pentagonal")
            layout.tvFirstParam.text = getString(R.string.str_parametros, "apotema",a)
            layout.tvSecondParam.text = getString(R.string.str_parametros, "l",l)
            layout.tvThirdParam.text = getString(R.string.str_parametros, "h",h)
            layout.tvResult.text = getString(R.string.str_resultado, "Volumen", res)
        }
    }

    fun onClickBack(view: View) {
        finish()
    }
}