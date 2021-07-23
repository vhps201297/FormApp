package com.example.formapp.utils

import android.util.Log
import com.example.formapp.utils.Constants.STRING_KEY_IDEAL_GAS
import com.example.formapp.utils.Constants.STRING_KEY_LAW_GRAVITY
import com.example.formapp.utils.Constants.STRING_KEY_PENTA

class FormCalculator() {
    companion object {

        var params: HashMap<String, Float>? = null
        const val R = 0.08205746F // Constante de los gases ideales

        /**
         * Función encargada de calcular una de las variables dentro de la ecuación de los gases
         * ideales
         * @param key_form Variable de tipo estring que especifica la varible que se quiere despejar
         * @param paramsExt Hashmap que alberga los parámetros de las variables necesarias para
         * realizar dicho calculo
         */
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

        /**
         * Función para calcular la fuerza entre dos cuerpos, con la ley de gracitación universal
         * @param m1 Masa del cuerpo uno
         * @param m2 Masa del cuerpo dos
         * @param r Distancia de separación entra los dos cuerpos
         */

        fun calculateLGU(m1: Float?, m2: Float?, r: Float?):Double{
            val G =  6.67384E-11
            return G * ((m1!!*m2!!)/(Math.pow(r!!.toDouble(),2.0)))
        }

        /**
         * Función encargada de calcular el volumen de un prisma pentagonal
         * @param a Apotema de la base pentagonal
         * @param l Lado de la base pentagonal
         * @param h Altura del prisma
         */
        fun calculateVolPP(a: Float?, l: Float?, h: Float?):Double = (5*a!!*l!!*h!!)/2.0

    }

}