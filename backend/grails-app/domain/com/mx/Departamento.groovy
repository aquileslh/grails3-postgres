package com.mx

class Departamento {

	static hasMany = [productos : Producto]
	String nombre
	String descripcion

    static constraints = {
    	nombre nullable : false
    }
}
