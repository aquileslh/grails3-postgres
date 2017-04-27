package com.mx

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import grails.rest.RestfulController


@Transactional(readOnly = true)
class ProductoController {

    def index(Integer max) {
        println "en controlador productos muestra"
        def res = Producto.list()
        println res
        render res as JSON
    }

    def show(Producto producto) {
        respond producto
    }

    def muestra() {
        println "en controlador productos muestra"
        def res = Producto.list()
        println res
        render res as JSON
    }

    @Transactional
    def save(Producto producto) {
        if (producto == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (producto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond producto.errors, view:'create'
            return
        }

        producto.save flush:true

        respond producto, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Producto producto) {
        if (producto == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (producto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond producto.errors, view:'edit'
            return
        }

        producto.save flush:true

        respond producto, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Producto producto) {

        if (producto == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        producto.delete flush:true

        render status: NO_CONTENT
    }
}
