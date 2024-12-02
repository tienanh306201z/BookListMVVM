package com.alva.booklist.exceptions

import android.app.Activity
import com.alva.booklist.utils.debugLog
import com.alva.booklist.utils.jumpToLoadingActivity
import com.github.anrwatchdog.ANRError
import com.github.anrwatchdog.ANRWatchDog
import java.lang.ref.WeakReference

class ANRExceptionHandler(activity: Activity) : ANRWatchDog.ANRListener {
    private val mainActivityRef = WeakReference(activity)

    override fun onAppNotResponding(error: ANRError?) {
        debugLog("ANR in thread: " + error?.stackTraceToString())
        mainActivityRef.get()?.jumpToLoadingActivity()
    }
}