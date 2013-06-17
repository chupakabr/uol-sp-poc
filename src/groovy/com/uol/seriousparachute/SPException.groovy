package com.uol.seriousparachute
/**
 * Created by myltik
 * Created on 6/17/13 6:45 AM
 */
class SPException extends Exception {

    SPException() {
    }

    SPException(String s) {
        super(s)
    }

    SPException(String s, Throwable throwable) {
        super(s, throwable)
    }

    SPException(Throwable throwable) {
        super(throwable)
    }
}
