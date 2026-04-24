package com.emoo.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emoo.books.presentation.addedit.BookAddEditScreen
import com.emoo.books.presentation.addedit.BookAddEditViewModel
import com.emoo.books.presentation.list.BooksListScreen
import com.emoo.books.presentation.list.BooksListViewModel
import com.emoo.books.ui.theme.BooksTheme
import com.emoo.books.utils.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.BooksListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.BooksListScreen.route) {
                            val books = viewModel<BooksListViewModel>()
                            BooksListScreen(navController, books)
                        }
                        composable(Screen.BookAddEditScreen.route) {
                            val book = viewModel<BookAddEditViewModel>()
                            BookAddEditScreen(navController, book)
                        }
                    }
                }
            }
        }
    }
}