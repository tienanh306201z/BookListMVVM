package com.alva.booklist.di

import com.alva.booklist.repositories.BookRepository
import com.alva.booklist.repositories.BookRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindBookRepository(bookRepository: BookRepository): BookRepositoryInterface
}