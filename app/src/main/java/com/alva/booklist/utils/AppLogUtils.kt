package com.alva.booklist.utils

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LogConditional")
fun debugLog(message: Any?) {
    Log.d("Hello", message.toString())
}