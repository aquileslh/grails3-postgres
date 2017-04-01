package testappsecurityrest

import com.mx.*

class BootStrap {

    def init = { servletContext ->
    	def prod = new Producto(
    		nombre: 'Xbox One S',
    		descripcion : 'Obtén por adelantado el paquete Xbox One S y Fifa 17 de 1 TB. Experimenta una acción real y auténtica con el Fifa 17',
    		cantidad : 12,
    		precio : 7896.43).save(flush : true)
    	def prod1 = new Producto(
    		nombre: 'Xbox One S Paquete Halo Wars 2 Ultimate Edition',
    		descripcion : 'Obtén el paquete definitivo de Halo Wars 2 para Xbox One S 1 TB, que incluye la descarga completa de Halo Wars 2',
    		cantidad : 4,
    		precio : 9736.43).save(flush : true)

    	def depart = new Departamento(
    		nombre : 'VideoJuegos',
    		descripcion : 'Consolas de video')
    		.addToProductos(prod)
    		.addToProductos(prod1)
    		.save(flush : true)

    	def tienda = new Tienda(
    		nombre : 'La comer',
    		direccion : 'Calle Loreto, No. 34 Col. Molino',
    		descripcion : 'Tienda centrica grandes ventas')
    		.addToDepartamentos(depart)
    		.save(flush : true)

    	Role admin = new Role("ROLE_ADMIN").save()
			Usuario user = new Usuario("admin", "admin").save()
			UsuarioRole.create(user, admin, true)

			Role user1 = new Role("ROLE_USER").save()
			Usuario nombre = new Usuario("user", "user").save()
			UsuarioRole.create(nombre, user1, true)

    }
    def destroy = {
    }
}
