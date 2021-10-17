package ev.aykhan.response

import ev.aykhan.entity.Book
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val id: String,
    val label: String,
    val description: String,
    val author: String,
    val genre: String,
) {

    companion object {

        fun from(book: Book): BookResponse {
            return BookResponse(book.id, book.label, book.description, book.author, book.genre)
        }

    }

}