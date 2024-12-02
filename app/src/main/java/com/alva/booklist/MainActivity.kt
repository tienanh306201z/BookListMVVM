@file:OptIn(ExperimentalComposeUiApi::class)

package com.alva.booklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.alva.booklist.constants.AppMaterialTheme
import com.alva.booklist.exceptions.ANRExceptionHandler
import com.alva.booklist.exceptions.UncaughtExceptionHandler
import com.alva.booklist.navigation.AppNavHost
import com.alva.booklist.utils.debugLog
import com.github.anrwatchdog.ANRWatchDog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeConfiguration()

        setContent { MyApp() }
    }

    private fun initializeConfiguration() {
        try {
            enableEdgeToEdge() // Set the window to draw behind the system bars

            Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler(this)) // Set the default uncaught exception handler
            ANRWatchDog(10000).setIgnoreDebugger(true).setANRListener(ANRExceptionHandler(this)).start() // Start the ANRWatchDog

        } catch (e: Exception) {
            debugLog("initializeConfiguration: $e")
        }
    }
}

@Composable
private fun MyApp() {
    AppMaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            AppNavHost()
        }
    }
}