package nl.mindtravel.springtime.repository

import nl.mindtravel.springtime.model.User

class UserCrudRepository(
    private val dataSource: MutableMap<Long, User>
): UserRepository {

    override fun create(user: User): User {
        val lastId = dataSource.keys.max()
        val incrementedId = lastId + 1
        val updatedUser = user.copy(id = incrementedId)
        dataSource[incrementedId] = updatedUser
        return user
    }

    override fun findAll(): List<User> = dataSource.values.toList()

    override fun findById(id: Long): User? = dataSource[id]

    override fun updateById(id: Long, user: User): User? =
        dataSource[id] ?.let {
            foundUser ->
            val updatedUser = user.copy(id = foundUser.id)
            dataSource[id] = updatedUser
            updatedUser
        }

    override fun deleteById(id: Long) {
        dataSource.remove(id)
    }

}