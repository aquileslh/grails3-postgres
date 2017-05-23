package com.mx

class Tienda {

	static hasMany = [departamentos : Departamento]

	String nombre
	String direccion
	String descripcion

    static constraints = {
    	nombre nullable : false
    	direccion nullable : false
    }
}
