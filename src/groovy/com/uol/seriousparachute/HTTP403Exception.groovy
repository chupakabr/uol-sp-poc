package com.uol.seriousparachute

/**
 * Created by myltik
 * Created on 6/18/13 10:47 PM
 */
class HTTP403Exception extends SPException {

    HTTP403Exception() {
        super("Unrestricted access to the resource")
    }

    HTTP403Exception(String s) {
        super(s)
    }

    HTTP403Exception(String s, Throwable throwable) {
        super(s, throwable)
    }

    HTTP403Exception(Throwable throwable) {
        super(throwable)
    }
}
