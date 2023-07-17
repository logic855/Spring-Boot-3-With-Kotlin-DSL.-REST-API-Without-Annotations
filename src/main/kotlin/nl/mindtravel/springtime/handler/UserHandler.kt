package nl.mindtravel.springtime.handler

import nl.mindtravel.springtime.model.ErrorResponse
import nl.mindtravel.springtime.model.User
import nl.mindtravel.springtime.model.UserDTO
import nl.mindtravel.springtime.repository.UserRepository
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

class UserHandler(
    private val userRepository: UserRepository
) {
    fun createUser(
        request: ServerRequest
    ): ServerResponse{
        val userRequest = request.body(UserDTO::class.java)

        val createUserResponse = userRepository.create(
            user = userRequest.toModel()
        )
            .toDTO()
        return ServerResponse.ok()
            .body(createUserResponse)
    }

    fun findAllUsers(request: ServerRequest): ServerResponse {
        val usersResponse = userRepository.findAll()
            .map(User::toDTO)
                return ServerResponse.ok().body(usersResponse)
    }

    fun findUserById(
        request: ServerRequest
    ): ServerResponse {
        val id = request.pathVariable("id")
            .toLongOrNull()
            ?: return badRequestResponse("Invalid Id")

        val userResponse = userRepository.findById(id)?.toDTO()
        return userResponse
            ?.let { response ->
                ServerResponse.ok()
                    .body(response)
            }
            ?: notFoundResponse()
    }

    fun updateUserById(
        request: ServerRequest
    ): ServerResponse {
        val id = request.pathVariable("id")
            .toLongOrNull()
            ?: return badRequestResponse("Invalid Id")

        val userRequest = request.body(UserDTO::class.java)
        val updatedUserResponse = userRepository.updateById(
            id = id,
            user = userRequest.toModel()
        )
            ?.toDTO()
        return updatedUserResponse
            ?.let { response ->
                ServerResponse.ok()
                    .body(response)
            }
            ?: notFoundResponse()
    }

    fun deleteUserById(
        request: ServerRequest
    ): ServerResponse{
        val id = request.pathVariable("id")
            .toLongOrNull()
            ?: return badRequestResponse("Invalid Id")

        userRepository.deleteById(id)

        return ServerResponse.noContent()
            .build()

    }

    private fun notFoundResponse() = ServerResponse.notFound().build()

    private fun badRequestResponse(message: String): ServerResponse =
        ServerResponse.badRequest().body(ErrorResponse(message))

}

private fun UserDTO.toModel(): User =
    User(
        id = this.id,
        name = this.name,
        email = this.email,
        age = this.age
    )

private fun User.toDTO(): UserDTO =
    UserDTO(
        id = this.id,
        name = this.name,
        email = this.email,
        age = this.age
    )