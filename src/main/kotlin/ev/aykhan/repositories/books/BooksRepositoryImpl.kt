package ev.aykhan.repositories.books

import ev.aykhan.db.FakeDB
import ev.aykhan.entity.Book

object BooksRepositoryImpl : BooksRepository {

    override fun getAllBooks(): List<Book> {
        return FakeDB.BooksTable.getAll()
    }

    override fun getBook(id: String): Book {
        return FakeDB.BooksTable.getBook(id)
    }

    override fun add(book: Book): Boolean {
        return FakeDB.BooksTable.add(book)
    }

    override fun delete(id: String) {
        FakeDB.BooksTable.delete(id)
    }

}