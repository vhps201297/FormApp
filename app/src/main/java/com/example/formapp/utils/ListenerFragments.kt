package com.example.formapp.utils

import android.os.Bundle

interface ListenerFragments {
    fun isValidated(bundle: Bundle)

    interface Spinner{
        fun changeItemSelected(itemSelect: Int)
    }
}