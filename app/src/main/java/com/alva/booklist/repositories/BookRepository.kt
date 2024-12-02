package com.alva.booklist.repositories

import android.content.Context
import com.alva.booklist.R
import com.alva.booklist.models.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class BookRepository @Inject constructor(@ApplicationContext context: Context) : BookRepositoryInterface {
    private val books = mutableListOf<Book>()

    init {
        books.addAll(loadSampleData(context))
    }

    override fun getBooks(): List<Book> = books

    override fun getBook(index: Int): Book? {
        return books.find { it.index == index }
    }

    override fun deleteBook(index: Int) {
        try {
            books.removeIf { it.index == index }
        } catch (e: Exception) {
            throw Exception("Book not found")
        }
    }

    private fun loadSampleData(context: Context): List<Book> {
        val jsonString: String
        var inputStream: InputStream? = null
        try {
            inputStream = context.resources.openRawResource(R.raw.sample_data_list)
            jsonString = inputStream.bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        } finally {
            inputStream?.close()
        }

        val listType = object : TypeToken<List<Book>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}