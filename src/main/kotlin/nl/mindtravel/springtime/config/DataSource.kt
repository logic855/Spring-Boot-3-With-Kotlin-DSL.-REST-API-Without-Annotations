package nl.mindtravel.springtime.config

import nl.mindtravel.springtime.model.User

object DataSource {

    val devDataSource: MutableMap<Long, User> = mutableMapOf(
        1L to User(1, name = "name-1", email="email-1@gmail.com", 24),
        2L to User(2, name = "name-2", email="email-2@gmail.com", 43),
        3L to User(3, name = "name-3", email="email-3@gmail.com", 30),
        4L to User(4, name = "name-4", email="email-4@gmail.com", 23),
    )

    val prodDataSource: MutableMap<Long, User> = mutableMapOf(
        1L to User(1L, name = "prod-name-1", email="prod-email-1@gmail.com", 24),
        2L to User(2L, name = "prod-name-2", email="prod-email-2@gmail.com", 43),
        3L to User(3L, name = "prod-name-3", email="prod-email-3@gmail.com", 30),
        4L to User(4L, name = "prod-name-4", email="prod-email-4@gmail.com", 23),
    )
}