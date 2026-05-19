package com.emoo.books.presentation.addedit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.emoo.books.presentation.BookVM
import com.emoo.books.utils.addOrUpdateBook
import com.emoo.books.utils.getBook

class BookAddEditViewModel(bookId: Int = -1) : ViewModel() {

    private val _book: MutableState<BookVM> = mutableStateOf(BookVM())
    var book: State<BookVM> = _book

    init {
        findBook(bookId)
    }

    private fun findBook(bookId: Int) {
        _book.value = getBook(bookId) ?: BookVM()
    }

    fun onEvent(event: BookAddEditEvent) {
        when (event) {
            is BookAddEditEvent.AuthorEntered -> _book.value =
                _book.value.copy(author = event.author)

            is BookAddEditEvent.TitleEntered -> _book.value = _book.value.copy(title = event.title)
            BookAddEditEvent.ReadStatusChanged -> _book.value =
                _book.value.copy(read = !_book.value.read)

            is BookAddEditEvent.TypeChanged -> _book.value =
                _book.value.copy(bookType = event.bookType)

            BookAddEditEvent.SaveBook -> addOrUpdateBook(book.value)
        }
    }

}