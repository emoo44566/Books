package com.emoo.books.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emoo.books.presentation.components.BookCard
import com.emoo.books.presentation.components.BookEvent
import com.emoo.books.presentation.components.SortByAuthor
import com.emoo.books.presentation.components.SortOptions
import com.emoo.books.presentation.components.SortOrder

@Composable
fun ListBooksScreen(books: List<BookVM>, innerPadding: PaddingValues) {
    var localBooks by remember { mutableStateOf(sortBooks(books, BookEvent.Order(SortByAuthor))) }
    Column(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        var sortOrder: SortOrder by remember { mutableStateOf(SortByAuthor) }

        SortOptions(sortOrder) { order ->
            sortOrder = order
            localBooks = sortBooks(localBooks, BookEvent.Order(order))
        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            localBooks.forEach { book ->
                item {
                    BookCard(book) {
                        localBooks = localBooks.filter { it != book }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}