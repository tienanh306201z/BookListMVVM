package com.alva.booklist.repositories

import com.alva.booklist.models.Book

interface BookRepositoryInterface {
    fun getBooks(): List<Book>
    fun getBook(index: Int): Book?
    fun deleteBook(index: Int)
}