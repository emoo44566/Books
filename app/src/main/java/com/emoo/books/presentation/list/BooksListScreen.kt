package com.emoo.books.presentation.list

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emoo.books.presentation.components.BookCard
import com.emoo.books.presentation.components.BookEvent
import com.emoo.books.presentation.components.SortOptions
import com.emoo.books.utils.BookAddEditScreen
import kotlinx.coroutines.launch

@Composable
fun BooksListScreen(navController: NavController, booksViewModel: BooksListViewModel) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(BookAddEditScreen(-1))
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { contentPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)
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
                    BookCard(
                        book,
                        onDeleteClick = {
                            booksViewModel.onEvent(BookEvent.Delete(book))
                            scope.launch {
                                snackbarHostState.showSnackbar("Book deleted successfully")
                            }
                        },
                        modifier = Modifier.clickable {
                            navController.navigate(BookAddEditScreen(book.id))
                        }
                    )
                }
            }
        }
    }
}