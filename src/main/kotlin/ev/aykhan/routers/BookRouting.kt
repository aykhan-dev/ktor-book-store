package ev.aykhan.routers

import ev.aykhan.dto.BookDTO
import ev.aykhan.repositories.books.BooksRepositoryImpl
import ev.aykhan.response.BookResponse
import ev.aykhan.response.ErrorResponse
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.generateBookRoutes() {

    authenticate {

        route("/books") {

            get {
                val books = BooksRepositoryImpl.getAllBooks()
                val response = books.map { BookResponse.from(it) }
                call.respond(HttpStatusCode.OK, response)
            }

            get("/{id}") {
                val id = call.parameters["id"]
                if (id == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse("check parameters"))
                else {
                    val book = BooksRepositoryImpl.getBook(id)
                    call.respond(HttpStatusCode.OK, BookResponse.from(book))
                }
            }

            post {
                val book = call.receive<BookDTO>()
                val isAdded = BooksRepositoryImpl.add(book.to())
                if (isAdded) call.respond(HttpStatusCode.Created)
                else call.respond(HttpStatusCode.Forbidden, ErrorResponse("book with this name is already exist"))
            }

            delete("/{id}") {
                val id = call.parameters["id"]
                if (id == null) call.respond(HttpStatusCode.BadRequest, ErrorResponse("check parameters"))
                else {
                    val book = BooksRepositoryImpl.getBook(id)
                    call.respond(HttpStatusCode.OK, BookResponse.from(book))
                }
            }

        }

    }

}