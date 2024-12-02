package com.alva.booklist.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alva.booklist.constants.BookSortMode
import com.alva.booklist.constants.LoadStatus
import com.alva.booklist.models.Book
import com.alva.booklist.repositories.BookRepository
import com.alva.booklist.utils.debugLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUIState(
    val books: List<Book> = emptyList(),
    val status: LoadStatus = LoadStatus.Init,
    val showDropDown: Boolean = false,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository?,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    fun loadBooks() {
        _uiState.value = _uiState.value.copy(status = LoadStatus.Loading)
        viewModelScope.launch {
            try {
                if (bookRepository != null) {
                    val books = bookRepository.getBooks()
                    _uiState.value = _uiState.value.copy(books = books, status = LoadStatus.Success)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(status = LoadStatus.Error(e.message.toString()))
            }
        }
    }

    fun deleteBook(index: Int) {
        _uiState.value = _uiState.value.copy(status = LoadStatus.Loading)
        viewModelScope.launch {
            try {
                if (bookRepository != null) {
                    bookRepository.deleteBook(index)
                    val updatedBooks = _uiState.value.books.filter { it.index != index }
                    _uiState.value = _uiState.value.copy(books = updatedBooks)
                }
            } catch (e: Exception) {
                throw e
            } finally {
                _uiState.value = _uiState.value.copy(status = LoadStatus.Success)
            }
        }
    }

    fun getBook(index: Int): Book? {
        return bookRepository?.getBook(index)
    }

    fun sortBooks(bookSortMode: BookSortMode) {
        val sortedBooks = when (bookSortMode) {
            BookSortMode.Index -> _uiState.value.books.sortedBy { it.index }
            BookSortMode.Title -> _uiState.value.books.sortedBy { it.title }
            BookSortMode.Date -> _uiState.value.books.sortedBy { it.date }
        }
        _uiState.value = _uiState.value.copy(books = sortedBooks)
    }

    fun toggleDropDown(value: Boolean) {
        _uiState.value = _uiState.value.copy(showDropDown = value)
    }

    fun resetUIStatus() {
        _uiState.value = _uiState.value.copy(status = LoadStatus.Init)
    }
}
