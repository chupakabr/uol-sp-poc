package com.uol.seriousparachute

/**
 * Created by myltik
 * Created on 6/17/13 6:46 AM
 */
class UserNotFoundException extends SPException {

    UserNotFoundException() {
    }

    UserNotFoundException(String s) {
        super(s)
    }

    UserNotFoundException(String s, Throwable throwable) {
        super(s, throwable)
    }

    UserNotFoundException(Throwable throwable) {
        super(throwable)
    }
}
