package com.example.formapp.utils

import android.util.Log
import com.example.formapp.utils.Constants.STRING_KEY_IDEAL_GAS
import com.example.formapp.utils.Constants.STRING_KEY_LAW_GRAVITY
import com.example.formapp.utils.Constants.STRING_KEY_PENTA

class FormCalculator() {
    companion object {
        var params: HashMap<String, Float>? = null
        const val R = 0.08205746F // Constante de los gases ideales

        fun calculateIdealGas(key_form: String, paramsExt: HashMap<String, Float>){ //F = G*((m1*m2)/r^2)
            this.params = paramsExt
            when(key_form){
                "P" -> params!!.put("P", calculateForP(paramsExt["V"],paramsExt["n"],paramsExt["T"]).toFloat())
                "V" -> params!!.put("V", calculateForV(paramsExt["P"],paramsExt["n"],paramsExt["T"]).toFloat())
                "n" -> params!!.put("n", calculateForN(paramsExt["V"],paramsExt["P"],paramsExt["T"]).toFloat())
                "T" -> params!!.put("T", calculateForT(paramsExt["V"],paramsExt["P"],paramsExt["n"]).toFloat())
            }
        }

        private fun calculateForP(V: Float?, n: Float?, T: Float?):Double = ((n!! * T!! * R)/V!!).toDouble()

        private fun calculateForV(P: Float?, n: Float?, T: Float?):Double = ((n!! * T!! * R)/P!!).toDouble()

        private fun calculateForN(V: Float?, P: Float?, T: Float?):Double = ((P!! * V!!)/(R * T!!)).toDouble()

        private fun calculateForT(V: Float?, P: Float?, n: Float?):Double = ((P!! * V!!)/(R * n!!)).toDouble()


        fun calculateLGU(m1: Float?, m2: Float?, r: Float?):Double{
            val G =  6.67384E-11
            return G * ((m1!!*m2!!)/(Math.pow(r!!.toDouble(),2.0)))
        }

        fun calculateVolPP(a: Float?, l: Float?, h: Float?):Double = (5*a!!*l!!*h!!)/2.0

    }

}