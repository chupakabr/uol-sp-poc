package com.uol.seriousparachute

class MessagesController {

    def springSecurityService
    def messageService

    def index() {
        Person u = springSecurityService.currentUser
        def lstParams = params
        lstParams['sort'] = "createdOn"
        lstParams['order'] = "desc"

        [
                cnt: PersonalMessage.countByTarget(u),
                entries: PersonalMessage.findAllByTarget(u, lstParams),
                userList: Person.findAll()
        ]
    }

    def compose() {
        PersonalMessage msg

        if (request.post) {
            msg = messageService.send(params.id, params.subject, params.text)
            if (!msg.hasErrors()) {
                flash.message = "Your message has been sent to ${msg?.target?.fullname?.encodeAsHTML()}."
                return redirect(action: 'index')
            }
        }

        [
                msg: msg,
                userList: Person.findAll(),
                id: params.id
        ]
    }

    def delete() {
        if (messageService.delete(params.id)) {
            flash.message = "Selected message has been removed from your inbox."
        }
        redirect(action: 'index')
    }
}
