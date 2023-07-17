package nl.mindtravel.springtime.config

import nl.mindtravel.springtime.handler.UserHandler
import org.springframework.web.servlet.function.router

fun appRouter(
    userHandler: UserHandler
) = router {
    "/api".nest{
        "/users".nest{
            POST("", userHandler::createUser)
            GET("", userHandler::findAllUsers)
            GET("/{id}", userHandler::findUserById)
            PUT("/{id}", userHandler::updateUserById)
            DELETE("/{id}", userHandler::deleteUserById)
        }
    }
}