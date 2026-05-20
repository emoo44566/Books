package com.emoo.books.presentation.addedit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emoo.books.presentation.BookVM
import com.emoo.books.utils.BookException
import com.emoo.books.utils.addOrUpdateBook
import com.emoo.books.utils.getBook
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BookAddEditViewModel(bookId: Int = -1) : ViewModel() {

    private val _book: MutableState<BookVM> = mutableStateOf(BookVM())
    var book: State<BookVM> = _book

    private val _eventFlow = MutableSharedFlow<BookAddEditUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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

            BookAddEditEvent.SaveBook -> {
                viewModelScope.launch {
                    try {
                        addOrUpdateBook(book.value)
                        _eventFlow.emit(BookAddEditUiEvent.SavedBook)
                    } catch (e: BookException) {
                        _eventFlow.emit(BookAddEditUiEvent.ShowMessage(e.message!!))
                    }
                }
            }
        }
    }

}

sealed interface BookAddEditUiEvent {
    data class ShowMessage(val message: String) : BookAddEditUiEvent
    data object SavedBook : BookAddEditUiEvent
}