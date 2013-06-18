package com.uol.seriousparachute

class PersonalDocument {

    static belongsTo = [owner: Person]

    String title
    String filename
    String origFilename

    Date createdOn = new Date()

    static constraints = {
        owner nullable: false
        title nullable: false, blank: false, maxSize: 250
        filename nullable: false, blank: false, maxSize: 250
        origFilename nullable: false, blank: false, maxSize: 250
        createdOn nullable: false
    }
}
