package ows.kotlinstudy.movierankapp

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.default(initialValue : T) = apply { value = initialValue }