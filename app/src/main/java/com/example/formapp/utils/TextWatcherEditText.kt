package com.example.formapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.formapp.R
import com.google.android.material.textfield.TextInputLayout

/**
 * Clase que sobreescribe un TextWatcher, para quitar los errores en cada TextInputLayout
 * puesto que al agregar un error y el usuario introduce nuevos valoress, el error no desaparece
 * por lo que se optó por crear esta clase que al observar que el usuario trata de corregir el
 * error se quite el error y se muestre de nuevo los colores primarios.
 *
 * @param til TextInputLayout que alberga el error
 */
class TextWatcherEditText(private val til: TextInputLayout): TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        til.error = null
    }
}