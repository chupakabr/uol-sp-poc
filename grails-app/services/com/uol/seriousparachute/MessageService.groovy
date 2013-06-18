package com.uol.seriousparachute

class MessageService {

    def springSecurityService

    /**
     * Send personal message to target user from current logged in user.
     * @param to
     * @param subject
     * @param message
     * @return PersonalMessage
     */
    PersonalMessage send(def toId, String subject, String message) {
        Person curUser = springSecurityService.currentUser
        if (!curUser) {
            throw new UserNotFoundException("User is not logged in")
        }

        Person to = Person.get(toId)
        if (!to) {
            throw new UserNotFoundException("User not found for ID=$toId")
        }

        PersonalMessage msg = new PersonalMessage(sender: curUser, target: to, subject: subject, message: message)
        msg.save(failOnError: false)
        msg
    }

    /**
     * Delete user message.
     * @param id
     * @return PersonalMessage
     */
    boolean delete(def id) {
        Person curUser = springSecurityService.currentUser
        if (!curUser) {
            throw new UserNotFoundException("User is not logged in")
        }

        PersonalMessage msg = PersonalMessage.get(id)
        if (msg && (msg.target.id == curUser.id || msg.sender.id == curUser.id)) {
            msg.delete()
            return true
        }

        return false
    }
}
