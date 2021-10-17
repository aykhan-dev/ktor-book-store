package ev.aykhan.entity

data class User(
    val id: String,
    val username: String,
    val password: String,
    var activeToken: String? = null,
)