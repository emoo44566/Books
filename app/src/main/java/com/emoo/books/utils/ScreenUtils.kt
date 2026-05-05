package com.emoo.books.utils

import kotlinx.serialization.Serializable


@Serializable
object BooksListScreen

@Serializable
data class BookAddEditScreen(val bookId: Int)
