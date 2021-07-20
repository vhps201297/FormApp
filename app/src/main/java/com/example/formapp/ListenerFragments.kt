package com.example.formapp

interface ListenerFragments {

    fun isValidated(isCorrect: Boolean)
    fun errors(msg: List<String>)
}