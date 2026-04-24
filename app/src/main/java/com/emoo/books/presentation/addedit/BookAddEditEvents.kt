package com.emoo.books.presentation.addedit

import com.emoo.books.presentation.BookType

sealed interface BookAddEditEvent {
    data class AuthorEntered(val author: String) : BookAddEditEvent
    data class TitleEntered(val title: String) : BookAddEditEvent
    data object ReadStatusChanged : BookAddEditEvent
    data class TypeChanged(val bookType: BookType) : BookAddEditEvent
    data object SaveBook : BookAddEditEvent
}