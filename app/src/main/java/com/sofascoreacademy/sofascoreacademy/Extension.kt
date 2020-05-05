package com.sofascoreacademy.sofascoreacademy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.toImmutableLiveData(): LiveData<T> = this