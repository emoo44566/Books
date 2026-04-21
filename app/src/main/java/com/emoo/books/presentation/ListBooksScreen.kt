package com.emoo.books.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emoo.books.presentation.components.BookCard
import com.emoo.books.presentation.components.BookEvent
import com.emoo.books.presentation.components.SortOptions

@Composable
fun ListBooksScreen(booksViewModel: ListBooksViewModel, innerPadding: PaddingValues) {
    Column(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {

        SortOptions(booksViewModel.sortOrder.value) { order ->
            booksViewModel.onEvent(BookEvent.Order(order))
        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(booksViewModel.books.value) { book ->
                BookCard(book, onDeleteClick = {
                    booksViewModel.onEvent(BookEvent.Delete(book))
                })
            }
        }
    }
}