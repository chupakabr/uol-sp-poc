package com.uol.seriousparachute

class PersonalMessage {

    Person sender
    Person target

    String subject
    String message

    static constraints = {
        sender nullable: false
        sender nullable: false
        message nullable: false, blank: false
        subject nullable: true
    }
}
