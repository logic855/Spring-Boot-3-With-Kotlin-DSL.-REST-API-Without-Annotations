package nl.mindtravel.springtime.config

import nl.mindtravel.springtime.handler.UserHandler
import nl.mindtravel.springtime.repository.UserCrudRepository
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val beans = beans {
    bean(name="myHandlerName", scope = BeanDefinitionDsl.Scope.PROTOTYPE){
        UserHandler(ref())
    }
    bean {
        appRouter(
            ref("myHandlerName")
        )
    }

    profile("dev"){
        bean {
            UserCrudRepository(DataSource.devDataSource)
        }
    }

    profile("prod"){
        bean {
            UserCrudRepository(DataSource.prodDataSource)
        }
    }
}

class BeansConfig: ApplicationContextInitializer<GenericApplicationContext>{
    override fun initialize(applicationContext: GenericApplicationContext) {
        beans.initialize(applicationContext)
    }
}