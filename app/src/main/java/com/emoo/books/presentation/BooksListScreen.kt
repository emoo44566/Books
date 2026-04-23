package com.emoo.books.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emoo.books.presentation.components.BookCard
import com.emoo.books.presentation.components.BookEvent
import com.emoo.books.presentation.components.SortOptions
import com.emoo.books.utils.Screen

@Composable
fun BooksListScreen(navController: NavController, booksViewModel: BooksListViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.BookAddEditScreen)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { contentPadding ->
        Column(Modifier
            .fillMaxSize()
            .padding(contentPadding)) {

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
}
