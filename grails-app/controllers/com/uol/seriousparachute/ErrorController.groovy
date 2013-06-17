package com.uol.seriousparachute

import org.codehaus.groovy.grails.web.errors.GrailsWrappedRuntimeException

class ErrorController {

    def index() {
        Throwable t = request.exception
        if (t instanceof GrailsWrappedRuntimeException && t.getCause() != null) {
            t = t.getCause()
        }

        if (t instanceof SPException) {
            flash.message = t.getMessage()
        }

        [exception: t]
    }

    def e404() {
        render view: 'index'
    }
}
