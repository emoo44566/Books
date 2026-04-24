package com.emoo.books.presentation.addedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emoo.books.presentation.Fiction
import com.emoo.books.presentation.NonFiction
import com.emoo.books.presentation.components.HorizontalTextRadioButton
import com.emoo.books.utils.Screen

@Composable
fun BookAddEditScreen(navController: NavController, viewModel: BookAddEditViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(BookAddEditEvent.SaveBook)
                navController.navigate(Screen.BooksListScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) { contentPadding ->
        val book = viewModel.book.value
        Column(
            Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 24.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = book.author,
                label = {
                    Text("Author")
                },
                onValueChange = { viewModel.onEvent(BookAddEditEvent.AuthorEntered(it)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = book.title,
                label = {
                    Text("Title")
                },
                onValueChange = { viewModel.onEvent(BookAddEditEvent.TitleEntered(it)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    checked = book.read,
                    onCheckedChange = {
                        viewModel.onEvent(BookAddEditEvent.ReadStatusChanged)
                    }
                )
                Text(
                    text = "Read",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                HorizontalTextRadioButton(
                    selected = book.bookType::class == Fiction::class,
                    text = "Fiction",
                    color = book.bookType.foregroundColor,
                    onOptionSelected = {
                        viewModel.onEvent(BookAddEditEvent.TypeChanged(Fiction))
                    }
                )
                HorizontalTextRadioButton(
                    selected = book.bookType::class == NonFiction::class,
                    text = "Non-Fiction",
                    color = book.bookType.foregroundColor,
                    onOptionSelected = {
                        viewModel.onEvent(BookAddEditEvent.TypeChanged(NonFiction))
                    }
                )
            }
        }
    }
}
