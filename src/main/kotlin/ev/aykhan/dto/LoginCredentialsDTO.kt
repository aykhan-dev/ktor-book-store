package ev.aykhan.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginCredentialsDTO(
    val username: String,
    val password: String,
)