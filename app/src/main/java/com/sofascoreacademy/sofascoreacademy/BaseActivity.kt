package com.sofascoreacademy.sofascoreacademy

import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    private val networkCalls: MutableList<Call<*>> = ArrayList()
    protected fun <T> execute(call: Call<T>, callback: Callback<T>?) {
        networkCalls.add(call)
        call.enqueue(callback)
    }

    override fun onStop() {
        super.onStop()
        for (networkCall in networkCalls) {
            networkCall.cancel()
        }
    }
}