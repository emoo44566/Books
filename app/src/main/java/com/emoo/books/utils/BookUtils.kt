package com.emoo.books.utils

import com.emoo.books.presentation.BookVM
import com.emoo.books.presentation.Fiction
import com.emoo.books.presentation.NonFiction
import com.emoo.books.presentation.components.SortByAuthor
import com.emoo.books.presentation.components.SortByFictional
import com.emoo.books.presentation.components.SortByRead
import com.emoo.books.presentation.components.SortByTitle
import com.emoo.books.presentation.components.SortOrder

fun getBooks(sortOrder: SortOrder): List<BookVM> {
    val books = listOf(
        BookVM("Sapiens", "Yuval Noah Harari", true, NonFiction),
        BookVM("The Great Gatsby", "F. Scott Fitzgerald", false, Fiction),
        BookVM("Atomic Habits", "James Clear", true, NonFiction),
        BookVM("1984", "George Orwell", true, Fiction),
        BookVM("Becoming", "Michelle Obama", true, NonFiction),
        BookVM("The Hobbit", "J.R.R. Tolkien", true, Fiction),
        BookVM("Thinking, Fast and Slow", "Daniel Kahneman", false, NonFiction),
        BookVM("To Kill a Mockingbird", "Harper Lee", true, Fiction),
        BookVM("Educated", "Tara Westover", true, NonFiction),
        BookVM("Pride and Prejudice", "Jane Austen", false, Fiction),
        BookVM("The Power of Habit", "Charles Duhigg", false, NonFiction),
        BookVM("The Catcher in the Rye", "J.D. Salinger", false, Fiction),
        BookVM("Moby-Dick", "Herman Melville", false, Fiction),
        BookVM("The Diary of a Young Girl", "Anne Frank", true, NonFiction),
        BookVM("War and Peace", "Leo Tolstoy", false, Fiction),
        BookVM("Quiet", "Susan Cain", false, NonFiction),
        BookVM("The Alchemist", "Paulo Coelho", true, Fiction),
        BookVM("Born a Crime", "Trevor Noah", false, NonFiction),
        BookVM("The Da Vinci Code", "Dan Brown", true, Fiction),
        BookVM("Daring Greatly", "Brené Brown", false, NonFiction),
        BookVM("The Immortal Life of Henrietta Lacks", "Rebecca Skloot", true, NonFiction),
        BookVM("The Wright Brothers", "David McCullough", false, NonFiction),
        BookVM("The Art of War", "Sun Tzu", true, NonFiction),
        BookVM("The 7 Habits of Highly Effective People", "Stephen R. Covey", false, NonFiction)
    )

    return when (sortOrder) {
        is SortByAuthor -> books.sortedBy { it.author }
        is SortByTitle -> books.sortedBy { it.title }
        is SortByRead -> books.sortedBy { it.read }
        is SortByFictional -> books.sortedBy { it.bookType == Fiction }
    }

}