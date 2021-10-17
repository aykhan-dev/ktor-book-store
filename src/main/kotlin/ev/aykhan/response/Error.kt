package ev.aykhan.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse<T>(val error: T)