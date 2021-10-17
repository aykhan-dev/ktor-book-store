package ev.aykhan.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterCredentialsDTO(
    val username: String,
    val pass: String,
    val passConfirm: String,
) {

    fun passValid(): Boolean = (pass == passConfirm)

}