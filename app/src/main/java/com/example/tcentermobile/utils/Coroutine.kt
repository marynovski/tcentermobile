package com.example.tcentermobile.utils

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Coroutine {

    fun test()
    {
        Log.d("AAA", "Routine1")
        GlobalScope.launch {
            Log.d("AAA", "Routine")
        }
    }

}