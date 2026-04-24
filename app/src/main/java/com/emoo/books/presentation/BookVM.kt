package com.emoo.books.presentation

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class BookVM(
    val id: Int = Random.nextInt(),
    val title: String = "",
    val author: String = "",
    val read: Boolean = false,
    val bookType: BookType = Fiction
)

sealed class BookType(val backgroundColor: Color, val foregroundColor: Color)
data object Fiction : BookType(Color.Red, Color.LightGray)
data object NonFiction : BookType(Color.Blue, Color.White)