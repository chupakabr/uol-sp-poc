package com.uol.seriousparachute

class PersonalDocument {

    static belongsTo = [owner: Person]

    String title
    String filename

    Date createdOn = new Date()

    static constraints = {
        owner nullable: false
        title nullable: false, blank: false
        filename nullable: false, blank: false
        createdOn nullable: false
    }
}
