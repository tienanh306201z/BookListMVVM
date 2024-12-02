package com.alva.booklist.utils

import android.app.Activity
import android.content.Intent
import com.alva.booklist.MainActivity
import kotlin.system.exitProcess
import android.os.Process
import com.alva.booklist.LoadingActivity

fun Activity.triggerRestart() {
    Intent(this, MainActivity::class.java).also {
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        this.startActivity(it)
    }
    Process.killProcess(Process.myPid())
    exitProcess(1)

}

fun Activity.jumpToLoadingActivity() {
    Intent(this, LoadingActivity::class.java).also {
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        this.startActivity(it)
    }
    Process.killProcess(Process.myPid())
    exitProcess(1)
}