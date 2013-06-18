package com.uol.seriousparachute

class PersonalDocument {

    Person owner

    String title
    String filename

    static constraints = {
        owner nullable: false
        title nullable: false, blank: false
        filename nullable: false, blank: false
    }
}
