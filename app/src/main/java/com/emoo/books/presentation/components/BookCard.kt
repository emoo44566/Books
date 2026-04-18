package com.emoo.books.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emoo.books.presentation.BookVM

@Composable
fun BookCard(book: BookVM) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(24.dp)
                .width(8.dp)
                .background(book.bookType.backgroundColor, shape = RoundedCornerShape(12.dp))
        )
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(3.dp)
                .background(book.bookType.foregroundColor, shape = RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    book.title,
                    style = TextStyle(fontSize = 20.sp, color = Color.LightGray),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                if (book.read)
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = ""
                    )
            }
            Text(
                book.author,
                style = TextStyle(fontSize = 18.sp, color = Color.Gray)
            )
        }

    }
}