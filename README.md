# grails3-postgres-angular2
Desarrollo de ejemplo con grails 3 y postgres 9.4
## Tabla de contenido
  1. [Creación de proyecto](#crear-proyecto)
  2. [Configuración de base de datos](#Configuración-de-base-de-datos)
  3. [Creación de tablas para usuarios y roles con s2-quickstart](#Creación-de-tablas-para-usuarios-y-roles-con-s2-quickstart)

##Creación de proyecto
Se crea el proyecto grails rest y se agregan las dependencias en build.gradle
```bash
$ grails create-app myapp --profile=rest-api

build.gradle

repositories {
     mavenLocal()
    jcenter()
    ......
}

dependencies {
    ....
     compile "org.grails.plugins:spring-security-rest:2.0.0.M2"
     compile "org.postgresql:postgresql:9.4-1201-jdbc41"
    ....
}
```
##Configuración de base de datos
```bash
hibernate:
     cache:
         queries: false
         use_second_level_cache: true
         use_query_cache: false
         region.factory_class: org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory
 
 dataSource:
     pooled: true
     jmxExport: true
     driverClassName: org.postgresql.Driver   
     username: postgres   //Usuario para conectar a la base de datos
     password: postgres   //password para conectar a la base de datos
 
 environments:
     development:
         dataSource:
             dbCreate: update
            url: jdbc:postgresql://localhost:5432/security      //Puerto y nombre de la base de datos configurada
     test:
         dataSource:
             dbCreate: update
              url: jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE
              ......
              ......
```
##Creación de tablas para usuarios y roles con s2-quickstart
```bash
grails s2-quickstart org.example Usuario Role
```
Esto crea las tablas en la base de datos, donde se almacenan los usuarios y roles. Insertamos usuarios en la base de datos desde el archivo BootStrap.groovy
```bash
def init = { servletContext ->
      Role admin = new Role("ROLE_ADMIN").save()
			Usuario user = new Usuario("admin", "admin").save()
			UsuarioRole.create(user, admin, true)

			Role user1 = new Role("ROLE_USER").save()
			Usuario usuario1 = new Usuario("user", "user").save()
			UsuarioRole.create(usuario1, user1, true)
}
```

```bash
```
###Uso de cherry-pick
