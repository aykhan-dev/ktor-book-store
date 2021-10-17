package ev.aykhan.repositories.auth

import ev.aykhan.db.FakeDB
import ev.aykhan.dto.LoginCredentialsDTO
import ev.aykhan.dto.RegisterCredentialsDTO
import ev.aykhan.entity.User
import java.util.*

object AuthRepositoryImpl : AuthRepository {

    override fun login(credentialsDTO: LoginCredentialsDTO): User {
        val user = FakeDB.UsersTable.search(credentialsDTO.username)
        return if (user.password == credentialsDTO.password) user else throw NoSuchElementException()
    }

    override fun register(credentialsDTO: RegisterCredentialsDTO): User {
        val user = User(
            id = UUID.randomUUID().toString(),
            username = credentialsDTO.username,
            password = credentialsDTO.pass,
        )
        val isAdded = FakeDB.UsersTable.add(user)
        if (isAdded) return user else throw IllegalStateException("User already exist")
    }

}