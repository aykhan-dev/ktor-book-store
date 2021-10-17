package ev.aykhan.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
)