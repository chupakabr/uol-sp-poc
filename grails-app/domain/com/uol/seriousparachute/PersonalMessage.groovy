package com.uol.seriousparachute

class PersonalMessage {

    static enum MessageStatus {
        NEW, READ
    }

    Person sender
    Person target

    String subject
    String message

    Date createdOn = new Date()

    MessageStatus status = MessageStatus.NEW

    static mapping = {
        sender index: 'personal_message_sender_idx'
        target index: 'personal_message_target_idx'
        status index: 'personal_message_status_idx'
    }

    static constraints = {
        sender nullable: false
        target nullable: false
        message nullable: false, blank: false
        subject nullable: true
        status nullable: false
        createdOn nullable: false
    }
}
