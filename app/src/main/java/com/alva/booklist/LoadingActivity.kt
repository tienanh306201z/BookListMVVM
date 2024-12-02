package com.alva.booklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alva.booklist.utils.triggerRestart

class LoadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val hostState = remember { SnackbarHostState() }

            LaunchedEffect(key1 = Unit) {
                val result = hostState.showSnackbar(
                    message = "Something wrong happened! Please restart the app!",
                    duration = SnackbarDuration.Indefinite,
                    actionLabel = "Restart",
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> this@LoadingActivity.triggerRestart()
                    else -> {}
                }
            }

            Scaffold(snackbarHost = { SnackbarHost(hostState = hostState) }) {
                Box(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Surface {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }
                }
            }
        }
    }
}