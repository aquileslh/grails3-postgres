package com.mx

class Producto {

	String nombre
	String descripcion
	Integer cantidad
	BigDecimal precio

    static constraints = {
     nombre nullable : false
     cantidad nullable : false
     precio nullable : false
    }

}
