package com.uol.seriousparachute

class AdminController {

    def userService

    def index() {
        indexParams()
    }
    private def indexParams() {
        [entries: Person.list(params), cnt: Person.count()]
    }

    def enableUser() {
        Person p = userService.activate(params.id)
        flash.message = "User ${p.username} has been enabled."
        redirect(action: "index")
    }

    def disableUser() {
        Person p = userService.deactivate(params.id)
        flash.message = "User ${p.username} has been disabled."
        redirect(action: "index")
    }

    def view() {
        // TODO
        throw new IllegalStateException("Not implemented")
    }

    def create() {
        // TODO
        throw new IllegalStateException("Not implemented")
    }

    def update() {
        // TODO
        throw new IllegalStateException("Not implemented")
    }

    def delete() {
        Person p = userService.delete(params.id)
        flash.message = "User ${p.username} has been deleted."
        redirect(action: "index")
    }
}
