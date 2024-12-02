package com.alva.booklist.view_models

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class ToastState(val message: String = "")

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ToastState())
    val uiState = _uiState.asStateFlow()

    fun setToastMessage(message: String) {
        _uiState.value = _uiState.value.copy(message = message)
    }
}