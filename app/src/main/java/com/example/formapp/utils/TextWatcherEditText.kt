package com.example.formapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.formapp.R
import com.google.android.material.textfield.TextInputLayout

class TextWatcherEditText(private val til: TextInputLayout): TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        til.error = ""
    }
}