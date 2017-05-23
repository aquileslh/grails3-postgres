# grails3-postgres-angular2
Desarrollo de ejemplo con grails 3 y postgres 9.4
## Tabla de contenido
  1. [Creación de proyecto](#creación-de-proyecto)
  2. [Configuración de base de datos](#configuración-de-base-de-datos)
  3. [Creación de tablas para usuarios y roles con s2-quickstart](#creación-de-tablas-para-usuarios-y-roles-con-s2-quickstart)
  4. [Configuracion basica de filtros en application groovy](#configuracion-basica-de-filtros-en-application-groovy)
  5. [Integración de angular2](#integración-de-angular2)
  6. [Peticion Get inicial](#peticion-Get-inicial)

## Creación de proyecto
Versiones:
 Grails Version: 3.2.1
 Groovy Version: 2.4.7
 JVM Version: 1.8.0_111
 angular-cli: 1.0.0-beta.25.5

Se crea el proyecto grails rest y se agregan las dependencias en build.gradle
```bash
$ grails create-app myapp --profile=rest-api

Agregar en build.gradle

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
**[⬆ Ir al inicio](#tabla-de-contenido)**

## Configuración de base de datos
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
**[⬆ Ir al inicio](#tabla-de-contenido)**

## Creación de tablas para usuarios y roles con s2-quickstart
```bash
$ grails s2-quickstart org.example Usuario Role
```
Esto crea las tablas en la base de datos, donde se almacenan los usuarios y roles, tambien se crea el archivo [application.groovy](http://alvarosanchez.github.io/grails-spring-security-rest/latest/docs/#_plugin_configuration) con los filtros configurables para las urls de la aplicacion. Insertamos usuarios en la base de datos desde el archivo BootStrap.groovy
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
**[⬆ Ir al inicio](#tabla-de-contenido)**

## Configuracion basica de filtros en application groovy
```bash
grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/api/productos/**', 		 filters: 'none'], // Los filtros se aplican a las urls de forma decendente, ha esta url no se le aplica ningun filtro
	[
    	pattern: '/api/**',
    	filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
  	],
 	[pattern: '/assets/**',      filters: 'none'],
 	......
	]
```
La configuración espesifica a todas las urls que inicien con /api/.... aplica todos los filtros menos los indicados.
Para el uso de permisos en los controladores se necesitan los siguinetes import y la anotacion en las clases o metodos segun el caso.
```bash
 import grails.plugin.springsecurity.annotation.Secured
 import grails.rest.RestfulController

 @Secured(['ROLE_ADMIN'])
 @Transactional(readOnly = true)
 class DepartamentoController {
    ......
    .....
 }

@Secured(['ROLE_USER'])
@Transactional(readOnly = true)
def newUser(){
    ...
    ..
}
```
Modificación de archivo UrlMappings.groovy para aplicación de filtros
**Los dominios (tienda, departamento, producto) y controladores (tiendas, departamentos, productos ) fueron creados de forma normal, y se insertaron datos en las tablas**
```bash
class UrlMappings {
  static mappings = {
	  "/api/tiendas"(resources : 'tienda')
    "/api/departamentos"(resources : 'departamento')
    "/api/productos"(resources : 'producto')

    "/"(controller: 'application', action:'index')
    "500"(view: '/error')
	}
}
```
**[⬆ Ir al inicio](#tabla-de-contenido)**

## Integración de angular2
Creación de proxy para eliminar problemas de CORS. creamos el archivo proxy.config.json con el siguiente contenido.
```bash
{
  "/api/*":{
	"target":"http://localhost:8080",
	"secure":false,
	"logLevel":"debug"
  }
}

Modificar el archivo package.json en la linea "start":
......
"scripts": {
    "ng": "ng",
    "start": "ng serve --proxy-config proxy.config.json",  
    "lint": "tslint \"src/**/*.ts\" --project src/tsconfig.json --type-check && tslint \"e2e/**/*.ts\" --project e2e/tsconfig.json --type-check",
    "test": "ng test",
    .....
```
El servidor angular se puede iniciar con
```bash
$ npm start
```
**[⬆ Ir al inicio](#tabla-de-contenido)**

## Peticion Get inicial
La petición get se realiza desde un componente angular.
[app.module.ts](https://github.com/aquileslh/grails3-postgres/blob/master/frontend/src/app/app.module.ts) Import necesarios para el funcionamiento.
[app.component.ts](https://github.com/aquileslh/grails3-postgres/blob/master/frontend/src/app/app.component.ts) Import y petición get a servicio rest de grails.
**[⬆ Ir al inicio](#tabla-de-contenido)**

```bash
```
