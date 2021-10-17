package ev.aykhan.dto

import ev.aykhan.entity.Book
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BookDTO(
    val label: String,
    val description: String,
    val author: String,
    val genre: String,
) {

    fun to(): Book {
        return Book(
            id = UUID.randomUUID().toString(),
            label = label,
            description = description,
            author = author,
            genre = genre,
        )
    }

}