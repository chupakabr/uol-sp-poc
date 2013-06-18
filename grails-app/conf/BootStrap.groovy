import com.uol.seriousparachute.Authority
import com.uol.seriousparachute.Person
import com.uol.seriousparachute.PersonAuthority
import com.uol.seriousparachute.SPConstants
import com.uol.seriousparachute.SpMetadata

class BootStrap {

    def init = { servletContext ->

        SpMetadata dbVersion = SpMetadata.findByName(SPConstants.METADATA_DB_VERSION)
        if (!dbVersion) {
            SpMetadata.withTransaction {
                dbVersion = new SpMetadata(name: SPConstants.METADATA_DB_VERSION, value: SPConstants.DB_VERSION)
                dbVersion.save()

                // Init authorities
                def adminRole = new Authority(authority: "ROLE_ADMIN")
                adminRole.save()

                def staffRole = new Authority(authority: "ROLE_STAFF")
                staffRole.save()

                def userRole = new Authority(authority: "ROLE_USER")
                userRole.save()

                // Init default users
                def gail = new Person(
                        username: 'gail@seriousparachute.com',
                        password: 'qweqweqwe',
                        fullname: 'Gail Miles',
                        address: 'New York',
                        phone: null,
                        enabled: true,
                        accountExpired: false,
                        accountLocked: false,
                        passwordExpired: false,
                        enabledOn: new Date(),
                        disabledOn: null
                )
                gail.save()

                def val = new Person(
                        username: 'val@seriousparachute.com',
                        password: 'qweqweqwe',
                        fullname: 'Val Che',
                        address: 'DPKR',
                        phone: "+79217854433",
                        enabled: true,
                        accountExpired: false,
                        accountLocked: false,
                        passwordExpired: false,
                        enabledOn: new Date(),
                        disabledOn: null
                )
                val.save()

                def tosin = new Person(
                        username: 'tosin@seriousparachute.com',
                        password: 'qweqweqwe',
                        fullname: 'Oluwatosin',
                        address: 'Liverpool',
                        phone: null,
                        enabled: true,
                        accountExpired: false,
                        accountLocked: false,
                        passwordExpired: false,
                        enabledOn: new Date(),
                        disabledOn: null
                )
                tosin.save()

                def chang = new Person(
                        username: 'chang@seriousparachute.com',
                        password: 'qweqweqwe',
                        fullname: 'Changseung',
                        address: 'Liverpool',
                        phone: null,
                        enabled: true,
                        accountExpired: false,
                        accountLocked: false,
                        passwordExpired: false,
                        enabledOn: new Date(),
                        disabledOn: null
                )
                chang.save()

                def gbenga = new Person(
                        username: 'gbenga@seriousparachute.com',
                        password: 'qweqweqwe',
                        fullname: 'Olugbengda',
                        address: 'Liverpool',
                        phone: null,
                        enabled: true,
                        accountExpired: false,
                        accountLocked: false,
                        passwordExpired: false,
                        enabledOn: new Date(),
                        disabledOn: null
                )
                gbenga.save()

                def advisor = new Person(
                        username: 'advisor@seriousparachute.com',
                        password: 'qweqweqwe',
                        fullname: 'Ip Man',
                        address: 'Liverpool',
                        phone: null,
                        enabled: true,
                        accountExpired: false,
                        accountLocked: false,
                        passwordExpired: false,
                        enabledOn: new Date(),
                        disabledOn: null
                )
                advisor.save()

                def student1 = new Person(
                        username: 'student1@seriousparachute.com',
                        password: 'qweqweqwe',
                        fullname: 'Bruce Lee',
                        address: 'Hong Kong',
                        phone: null,
                        enabled: true,
                        accountExpired: false,
                        accountLocked: false,
                        passwordExpired: false,
                        enabledOn: new Date(),
                        disabledOn: null
                )
                student1.save()

                def student2 = new Person(
                        username: 'student2@seriousparachute.com',
                        password: 'qweqweqwe',
                        fullname: 'Jackie Chan',
                        address: 'Hong Kong',
                        phone: "+85227940388",
                        enabled: true,
                        accountExpired: false,
                        accountLocked: false,
                        passwordExpired: false,
                        enabledOn: new Date(),
                        disabledOn: null
                )
                student2.save()

                // Add roles per each user
                PersonAuthority.create(gail, adminRole)
                PersonAuthority.create(val, adminRole)
                PersonAuthority.create(tosin, adminRole)
                PersonAuthority.create(chang, adminRole)
                PersonAuthority.create(gbenga, adminRole)
                PersonAuthority.create(advisor, staffRole)
                PersonAuthority.create(student1, userRole)
                PersonAuthority.create(student2, userRole)
            }
        }
    }

    def destroy = {
    }
}
