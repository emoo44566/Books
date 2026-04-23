package com.emoo.books.utils

sealed class Screen(val route: String) {
    data object BooksListScreen : Screen("books_list_screen")
    data object BookAddEditScreen : Screen("book_add_edit_screen")
}