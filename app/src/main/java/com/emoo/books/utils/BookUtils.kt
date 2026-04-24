package com.emoo.books.utils

import com.emoo.books.presentation.BookVM
import com.emoo.books.presentation.Fiction
import com.emoo.books.presentation.NonFiction
import com.emoo.books.presentation.components.SortByAuthor
import com.emoo.books.presentation.components.SortByFictional
import com.emoo.books.presentation.components.SortByRead
import com.emoo.books.presentation.components.SortByTitle
import com.emoo.books.presentation.components.SortOrder

val books = mutableListOf(
    BookVM(title = "Sapiens", author = "Yuval Noah Harari", read = true, bookType = NonFiction),
    BookVM(
        title = "The Great Gatsby",
        author = "F. Scott Fitzgerald",
        read = false,
        bookType = Fiction
    ),
    BookVM(title = "Atomic Habits", author = "James Clear", read = true, bookType = NonFiction),
    BookVM(title = "1984", author = "George Orwell", read = true, bookType = Fiction),
    BookVM(title = "Becoming", author = "Michelle Obama", read = true, bookType = NonFiction),
    BookVM(title = "The Hobbit", author = "J.R.R. Tolkien", read = true, bookType = Fiction),
    BookVM(
        title = "Thinking, Fast and Slow",
        author = "Daniel Kahneman",
        read = false,
        bookType = NonFiction
    ),
    BookVM(
        title = "To Kill a Mockingbird",
        author = "Harper Lee",
        read = true,
        bookType = Fiction
    ),
    BookVM(title = "Educated", author = "Tara Westover", read = true, bookType = NonFiction),
    BookVM(
        title = "Pride and Prejudice",
        author = "Jane Austen",
        read = false,
        bookType = Fiction
    ),
    BookVM(
        title = "The Power of Habit",
        author = "Charles Duhigg",
        read = false,
        bookType = NonFiction
    ),
    BookVM(
        title = "The Catcher in the Rye",
        author = "J.D. Salinger",
        read = false,
        bookType = Fiction
    ),
    BookVM(title = "Moby-Dick", author = "Herman Melville", read = false, bookType = Fiction),
    BookVM(
        title = "The Diary of a Young Girl",
        author = "Anne Frank",
        read = true,
        bookType = NonFiction
    ),
    BookVM(title = "War and Peace", author = "Leo Tolstoy", read = false, bookType = Fiction),
    BookVM(title = "Quiet", author = "Susan Cain", read = false, bookType = NonFiction),
    BookVM(title = "The Alchemist", author = "Paulo Coelho", read = true, bookType = Fiction),
    BookVM(title = "Born a Crime", author = "Trevor Noah", read = false, bookType = NonFiction),
    BookVM(title = "The Da Vinci Code", author = "Dan Brown", read = true, bookType = Fiction),
    BookVM(
        title = "Daring Greatly",
        author = "Brené Brown",
        read = false,
        bookType = NonFiction
    ),
    BookVM(
        title = "The Immortal Life of Henrietta Lacks",
        author = "Rebecca Skloot",
        read = true,
        bookType = NonFiction
    ),
    BookVM(
        title = "The Wright Brothers",
        author = "David McCullough",
        read = false,
        bookType = NonFiction
    ),
    BookVM(title = "The Art of War", author = "Sun Tzu", read = true, bookType = NonFiction),
    BookVM(
        title = "The 7 Habits of Highly Effective People",
        author = "Stephen R. Covey",
        read = false,
        bookType = NonFiction
    )
)

fun getBooks(sortOrder: SortOrder): List<BookVM> {
    return when (sortOrder) {
        is SortByAuthor -> books.sortedBy { it.author }
        is SortByTitle -> books.sortedBy { it.title }
        is SortByRead -> books.sortedBy { it.read }
        is SortByFictional -> books.sortedBy { it.bookType == Fiction }
    }
}

fun addOrUpdateBook(book: BookVM) {
    val b = books.find { it.id == book.id }
    b?.let { books.remove(it) }
    books.add(book)
}

fun deleteBook(book: BookVM) {
    books.remove(book)
}