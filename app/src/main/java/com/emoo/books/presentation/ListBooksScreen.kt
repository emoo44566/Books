package com.emoo.books.presentation

import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.emoo.books.presentation.components.BookCard

@Composable
fun ListBooksScreen(innerPadding: PaddingValues) {
    var localBooks by remember { mutableStateOf(books) }
    LazyColumn(
        Modifier
            .padding(innerPadding)
            .border(.2.dp, color = Color.Blue)
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