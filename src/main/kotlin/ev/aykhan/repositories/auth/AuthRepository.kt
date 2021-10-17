package ev.aykhan.repositories.auth

import ev.aykhan.dto.LoginCredentialsDTO
import ev.aykhan.dto.RegisterCredentialsDTO
import ev.aykhan.entity.User
import io.ktor.http.*

interface AuthRepository {

    fun login(credentialsDTO: LoginCredentialsDTO): User

    fun register(credentialsDTO: RegisterCredentialsDTO): User

}