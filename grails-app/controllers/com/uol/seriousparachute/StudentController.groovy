package com.uol.seriousparachute

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.web.multipart.MultipartFile

class StudentController {

    def springSecurityService
    def userService
    def documentService

    def index() {
        // Display current user personal information
        Person u = springSecurityService.currentUser
        render(view: "view", model: [entry:u])
    }

    def view() {
        // Display selected user information
        Person u = getUserOrAccessDenied()
        [entry: u]
    }

    def documents() {
        // Display personal documents
        Person u
        if (params.id) {
            u = getUserOrAccessDenied()
        } else {
            u = springSecurityService.currentUser
        }

        if (!u) {
            throw new UserNotFoundException("User not found for ID=${params.id}")
        }

        def lstParams = params
        lstParams['sort'] = "createdOn"
        lstParams['order'] = "desc"
        [
                user: u,
                cnt: PersonalDocument.countByOwner(u),
                entries: PersonalDocument.findAllByOwner(u, lstParams)
        ]
    }

    def update() {
        // Update personal information
        Person u = springSecurityService.currentUser
        if (request.post) {
            userService.update(u, params.fullname, params.address, params.phone)
            if (!u.hasErrors()) {
                flash.message = "Your user profile has been updated."
            }
        }
        [entry: u]
    }

    def uploadDocument() {
        Person u = springSecurityService.currentUser
        PersonalDocument doc

        if (request.post) {
            MultipartFile tmpFile = request.getFile('file')
            if (!tmpFile || tmpFile.empty) {
                flash.message = "File must be specified and cannot be empty."
            } else if (tmpFile.size > SPConstants.MAX_FILE_SIZE) {
                flash.message = "File size is too long, maximum file size is ${SPConstants.MAX_FILE_SIZE_S}."
            } else {
                doc = documentService.upload(tmpFile, params.title)
                if (!doc.hasErrors()) {
                    flash.message = "Document has been successfully uploaded."
                    return redirect(action: 'documents')
                }
            }
        }

        [entry: doc, user: u]
    }

    def document() {
        // Download document
        PersonalDocument doc = getDocOrAccessDenied()
        def file = documentService.file(doc)
        if (!file || !file.exists()) {
            flash.message = "File not found in the system"
            return redirect(action: 'documents')
        } else {
            response.setContentType("application/octet-stream") // or or image/JPEG or text/xml or whatever type the file is
            response.setHeader("Content-disposition", "attachment;filename=${doc.origFilename}")
            response.outputStream << file.bytes
        }
    }

    def deleteDocument() {
        if (documentService.delete(params.id)) {
            flash.message = "Selected document has been removed."
        }
        redirect(action: 'documents')
    }

    private Person getUserOrAccessDenied() {
        Person curUser = springSecurityService.currentUser
        if (curUser.id == Long.valueOf(params.id) || SpringSecurityUtils.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
            Person u = Person.get(params.id)
            if (!u) {
                throw new UserNotFoundException("User not found for ID=${params.id}")
            }
            return u
        }
        flash.message = "Cannot access restricted resource."
        throw new HTTP403Exception()
    }

    private PersonalDocument getDocOrAccessDenied() {
        PersonalDocument doc = PersonalDocument.get(params.id)
        if (!doc) {
            flash.message = "Document not found in the system."
            throw new DocNotFoundException("Document not found for ID=${params.id}")
        }

        Person curUser = springSecurityService.currentUser
        if (curUser.id == doc.owner.id || SpringSecurityUtils.ifAnyGranted("ROLE_STAFF,ROLE_ADMIN")) {
            return doc
        }

        flash.message = "Cannot access restricted resource."
        throw new HTTP403Exception()
    }
}
