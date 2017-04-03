package com.mx

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured(['ROLE_USER'])
@Transactional(readOnly = true)
class ProductoController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Producto.list(params), model:[productoCount: Producto.count()]
    }

    def show(Producto producto) {
        respond producto
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
