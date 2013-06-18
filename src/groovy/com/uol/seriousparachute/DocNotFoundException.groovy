package com.uol.seriousparachute

/**
 * Created by myltik
 * Created on 6/19/13 12:56 AM
 */
class DocNotFoundException extends Exception{

    DocNotFoundException(Throwable throwable) {
        super(throwable)
    }

    DocNotFoundException(String s, Throwable throwable) {
        super(s, throwable)
    }

    DocNotFoundException(String s) {
        super(s)
    }

    DocNotFoundException() {
    }
}
