package com.mx

class Producto {

	String nombre
	String descripcion
	Integer cantidadd
	BigDecimal precio

    static constraints = {
     nombre nullable : false
     cantidad nullable : false
     precio nullable : false
    }

}
