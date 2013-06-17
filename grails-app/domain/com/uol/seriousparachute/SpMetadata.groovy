package com.uol.seriousparachute

class SpMetadata {

    String name
    String value

    static mapping = {
        name index: 'metadata_name_idx'
    }

    static constraints = {
        name nullable: false, blank: false, maxSize: 50, unique: true
        value nullable: true
    }
}
