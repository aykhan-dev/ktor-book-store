package ev.aykhan.repositories.books

import ev.aykhan.entity.Book

interface BooksRepository {

    fun getAllBooks(): List<Book>

    fun getBook(id: String): Book

    fun add(book: Book): Boolean

    fun delete(id: String)

}