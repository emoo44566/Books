package com.emoo.books.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SortOptions(
    bookOrder: SortOrder = SortByAuthor,
    onSortOrderChange: (SortOrder) -> Unit
) {
    LazyRow(
        modifier =
            Modifier.fillMaxWidth()
    ) {
        item {
            BooksRadioButton(
                text = "Author",
                selected = bookOrder is SortByAuthor,
                onSelect = { onSortOrderChange(SortByAuthor) })

            Spacer(modifier = Modifier.width(8.dp))

            BooksRadioButton(
                text = "Title",
                selected = bookOrder is SortByTitle,
                onSelect = { onSortOrderChange(SortByTitle) })

            Spacer(modifier = Modifier.width(8.dp))

            BooksRadioButton(
                text = "Fictional",
                selected = bookOrder is SortByFictional,
                onSelect = { onSortOrderChange(SortByFictional) })

            Spacer(modifier = Modifier.width(8.dp))

            BooksRadioButton(
                text = "Read",
                selected = bookOrder is SortByRead,
                onSelect = { onSortOrderChange(SortByRead) })

        }
    }
}

sealed class SortOrder;
data object SortByAuthor : SortOrder()
data object SortByTitle : SortOrder()
data object SortByRead : SortOrder()
data object SortByFictional : SortOrder()

sealed class BookEvent {
    data class Order(val order: SortOrder)
}