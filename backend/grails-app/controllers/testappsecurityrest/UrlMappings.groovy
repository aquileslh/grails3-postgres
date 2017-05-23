package testappsecurityrest

class UrlMappings {

    static mappings = {

        "/api/tiendas"(resources : 'tienda')
        "/api/departamentos"(resources : 'departamento')
        "/api/productos"(resources : 'producto')

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
