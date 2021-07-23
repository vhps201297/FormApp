package com.example.formapp.utils

import android.os.Bundle

/**
 * Interfaz creada para todos los fragments. Además de generar un método para el Spinner
 * que se implementó con la dccumentación de material design, donde devuelve el item seleccionado
 * dentro del fragment.
 */
interface ListenerFragments {
    fun isValidated(bundle: Bundle)

    interface Spinner{
        fun changeItemSelected(itemSelect: Int)
    }
}