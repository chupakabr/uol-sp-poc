package com.uol.seriousparachute

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

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
                userList: getUserList()
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
                userList: getUserList(),
                id: params.id
        ]
    }

    def delete() {
        if (messageService.delete(params.id)) {
            flash.message = "Selected message has been removed from your inbox."
        }
        redirect(action: 'index')
    }

    private def getUserList() {
        def lst
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN,ROLE_STAFF')) {
            lst = Person.findAll()
        } else {
            Authority staffAuthority = Authority.findByAuthority("ROLE_STAFF")
            lst = Person.findAll(
                    "from Person as p where p.enabled = true and exists (from PersonAuthority as pa where pa.person = p and pa.authority = :authority)",
                    [authority: staffAuthority])
        }

        lst
    }
}
