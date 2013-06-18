package com.uol.seriousparachute

class StaffController {

    def springSecurityService

    def index() {
        Authority studentAuthority = Authority.findByAuthority("ROLE_USER")
        def cnt = PersonAuthority.executeQuery(
            "select count(*) from PersonAuthority as p where p.authority = :authority",
            [authority: studentAuthority])
        def entries = Person.findAll(
            "from Person as p where exists (from PersonAuthority as pa where pa.person = p and pa.authority = :authority)",
            [authority: studentAuthority])

        render(view: '/admin/index', model: [cnt: cnt, entries: entries])
    }
}
