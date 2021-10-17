package ev.aykhan.db

import ev.aykhan.entity.Book
import ev.aykhan.entity.User
import java.util.*

object FakeDB {

    object UsersTable {

        private val users = mutableListOf(
            User(
                id = UUID.randomUUID().toString(),
                username = "aykhan-dev",
                password = "123456",
            )
        )

        fun search(username: String): User {
            return users.first { it.username == username }
        }

        fun add(user: User): Boolean {
            val isUserExist = users.any { it.username == user.username }
            var isAdded = false
            if (!isUserExist) {
                users.add(user)
                isAdded = true
            }
            return isAdded
        }

    }

    object BooksTable {

        private val books = mutableListOf<Book>()

        fun getAll(): List<Book> {
            return books
        }

        fun getBook(id: String): Book {
            return books.first { it.id == id }
        }

        fun add(book: Book): Boolean {
            val isBookExist = books.any { it.label == book.label }
            var isAdded = false
            if (!isBookExist) {
                books.add(book)
                isAdded = true
            }
            return isAdded
        }

        fun delete(id: String) {
            books.removeIf { it.id == id }
        }

    }

}