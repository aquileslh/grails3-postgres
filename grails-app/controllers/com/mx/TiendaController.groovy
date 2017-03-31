package com.mx

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TiendaController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tienda.list(params), model:[tiendaCount: Tienda.count()]
    }

    def show(Tienda tienda) {
        respond tienda
    }

    @Transactional
    def save(Tienda tienda) {
        if (tienda == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (tienda.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tienda.errors, view:'create'
            return
        }

        tienda.save flush:true

        respond tienda, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Tienda tienda) {
        if (tienda == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (tienda.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tienda.errors, view:'edit'
            return
        }

        tienda.save flush:true

        respond tienda, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Tienda tienda) {

        if (tienda == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        tienda.delete flush:true

        render status: NO_CONTENT
    }
}
