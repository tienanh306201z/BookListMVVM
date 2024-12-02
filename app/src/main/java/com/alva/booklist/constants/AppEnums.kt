package com.alva.booklist.constants

/**
 * Represents the status of a loading UI.
 *
 * @property description A description of the load status.
 */
sealed class LoadStatus(val description: String = "") {
    /**
     * Initial state before loading starts.
     */
    data object Init : LoadStatus()

    /**
     * State when loading is in progress.
     */
    data object Loading : LoadStatus()

    /**
     * State when loading is successful.
     */
    data object Success : LoadStatus()

    /**
     * State when an error occurs during loading.
     *
     * @property error The error message.
     */
    data class Error(val error: String) : LoadStatus(error)
}

enum class BookSortMode {
    Index, Title, Date
}