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
import androidx.navigation.toRoute
import com.emoo.books.presentation.addedit.BookAddEditScreen
import com.emoo.books.presentation.addedit.BookAddEditViewModel
import com.emoo.books.presentation.list.BooksListScreen
import com.emoo.books.presentation.list.BooksListViewModel
import com.emoo.books.ui.theme.BooksTheme
import com.emoo.books.utils.BookAddEditScreen
import com.emoo.books.utils.BooksListScreen

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
                        startDestination = BooksListScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<BooksListScreen> {
                            val books = viewModel<BooksListViewModel>()
                            BooksListScreen(navController, books)
                        }
                        composable<BookAddEditScreen> { navBackStackEntry ->
                            val args = navBackStackEntry.toRoute<BookAddEditScreen>()
                            val book = viewModel<BookAddEditViewModel>() {
                                BookAddEditViewModel(args.bookId)
                            }
                            BookAddEditScreen(navController, book)
                        }
                    }
                }
            }
        }
    }
}