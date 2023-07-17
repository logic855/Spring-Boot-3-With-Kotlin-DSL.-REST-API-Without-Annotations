package nl.mindtravel.springtime.model

data class UserDTO(
    val id: Long,
    val email: String,
    val name: String,
    val age: Int
) {
}