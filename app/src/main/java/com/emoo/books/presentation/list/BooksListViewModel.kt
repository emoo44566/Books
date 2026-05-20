package com.emoo.books.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emoo.books.presentation.BookVM
import com.emoo.books.presentation.components.BookEvent
import com.emoo.books.presentation.components.SortByAuthor
import com.emoo.books.presentation.components.SortOrder
import com.emoo.books.utils.getBooks
import kotlinx.coroutines.launch

class BooksListViewModel : ViewModel() {

    private val _books: MutableState<List<BookVM>> = mutableStateOf(emptyList())
    var books: State<List<BookVM>> = _books

    private val _sortOrder: MutableState<SortOrder> = mutableStateOf(SortByAuthor)
    var sortOrder: State<SortOrder> = _sortOrder

    init {
        loadBooks(sortOrder.value)
    }

    private fun loadBooks(sortOrder: SortOrder) {
        viewModelScope.launch {
            getBooks(sortOrder).collect { books ->
                _books.value = books
            }
        }
    }

    fun onEvent(event: BookEvent) {
        when (event) {
            is BookEvent.Delete -> deleteBook(event.book)
            is BookEvent.Order -> {
                _sortOrder.value = event.order
                loadBooks(event.order)
            }
        }
    }

    private fun deleteBook(book: BookVM) {
        viewModelScope.launch {
            com.emoo.books.utils.deleteBook(book)
            loadBooks(sortOrder.value)
        }
    }

}