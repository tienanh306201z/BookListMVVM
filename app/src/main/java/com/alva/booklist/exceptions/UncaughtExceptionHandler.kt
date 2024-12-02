package com.alva.booklist.exceptions

import com.alva.booklist.MainActivity
import com.alva.booklist.utils.debugLog
import com.alva.booklist.utils.triggerRestart

class UncaughtExceptionHandler(private val mainActivity: MainActivity) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(p0: Thread, p1: Throwable) {
        debugLog("uncaught_exception_handler:" + p1.stackTraceToString())
        mainActivity.triggerRestart()
    }
}