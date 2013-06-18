package com.uol.seriousparachute

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class StudentController {

    def springSecurityService

    def index() {
        // Display current user personal information
        Person u = springSecurityService.currentUser
        render(view: "view", model: [entry:u])
    }

    def view() {
        // Display selected user information
        Person u = getUserOrAccessDenied()
        [entry: u]
    }

    def documents() {
        // Display personal documents
        Person u
        if (params.id) {
            u = getUserOrAccessDenied()
        } else {
            u = springSecurityService.currentUser
        }

        if (!u) {
            throw new UserNotFoundException("User not found for ID=${params.id}")
        }

        def lstParams = params
        lstParams['sort'] = "createdOn"
        lstParams['order'] = "desc"
        [
                cnt: PersonalDocument.countByOwner(u),
                entries: PersonalDocument.findAllByOwner(u, lstParams)
        ]
    }

    def update() {
        // TODO Update personal information
        Person u = springSecurityService.currentUser
    }

    def uploadDocument() {
        // TODO Upload document
        Person u = springSecurityService.currentUser
    }

    private Person getUserOrAccessDenied() {
        Person curUser = springSecurityService.currentUser
        if (curUser.id == params.id || SpringSecurityUtils.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
            Person u = Person.get(params.id)
            if (!u) {
                throw new UserNotFoundException("User not found for ID=${params.id}")
            }
            return u
        }
        throw new HTTP403Exception()
    }
}
