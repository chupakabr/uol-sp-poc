package com.uol.seriousparachute

class Person {

    transient springSecurityService

    // User information
    String username
    String password

    // Flags
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    // Timestamps
    Date enabledOn
    Date disabledOn

    static hasMany = [
            messages: PersonalMessage,
            documents: PersonalDocument
    ]

    static constraints = {
        username blank: false, unique: true, email: true
        password blank: false

        enabled nullable: false
        accountExpired nullable: false
        accountLocked nullable: false
        passwordExpired nullable: false

        enabledOn nullable: true
        disabledOn nullable: true
    }

    static mapping = {
        password column: '`password`'

        username index: 'person_username_idx'
        enabled index: 'person_enabled_idx'
    }

    Set<Authority> getAuthorities() {
        PersonAuthority.findAllByPerson(this).collect { it.authority } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
