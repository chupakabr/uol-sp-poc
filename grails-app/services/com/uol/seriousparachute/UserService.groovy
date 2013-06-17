package com.uol.seriousparachute

class UserService {

    /**
     * Delete user from the system
     * @param uid
     * @return Deleted user
     */
    Person delete(final uid) throws UserNotFoundException {
        Person p = Person.get(uid)
        if (!p) {
            throw new UserNotFoundException("User not found for ID=$uid")
        }

        p.delete()
        p
    }

    /**
     * Deactivate user, i.e. disable.
     * @param uid User ID
     * @return Person
     * @throws UserNotFoundException
     */
    Person deactivate(final uid) throws UserNotFoundException {
        Person p = Person.get(uid)
        if (!p) {
            throw new UserNotFoundException("User not found for ID=$uid")
        }

        if (p.enabled) {
            p.enabled = false
            p.disabledOn = new Date()
            p.save()
        }

        p
    }

    /**
     * Activate user after deactivation.
     * @param uid User ID
     * @return Person
     * @throws UserNotFoundException
     */
    Person activate(final uid) throws UserNotFoundException {
        Person p = Person.get(uid)
        if (!p) {
            throw new UserNotFoundException("User not found for ID=$uid")
        }

        if (!p.enabled) {
            p.enabled = true
            p.enabledOn = new Date()
            p.save()
        }

        p
    }
}
