package com.uol.seriousparachute

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.springframework.web.multipart.MultipartFile

class DocumentService {

    private static final String UPLOAD_DIR = String.valueOf(ConfigurationHolder.config.sp.dir.upload)

    def springSecurityService

    /**
     * Upload user document.
     * @param tmpFile
     * @param title
     * @return PersonalDocument
     */
    PersonalDocument upload(MultipartFile tmpFile, String title) {
        Person curUser = springSecurityService.currentUser
        if (!curUser) {
            throw new UserNotFoundException("User is not logged in")
        }

        final extension = filenameExtension(tmpFile.originalFilename)
        final filename = generateFilename(curUser, extension)
        final targetPath = "${UPLOAD_DIR}/$filename"
        log.info "Uploading file (${tmpFile.originalFilename}) for user ${curUser.id}-${curUser.username} to $targetPath"
        tmpFile.transferTo(new File(targetPath))

        PersonalDocument doc = new PersonalDocument(
                title: title,
                filename: filename,
                origFilename: tmpFile.originalFilename,
                owner: curUser)
        if (!doc.save(failOnError: false)) {
            try {
                new File(targetPath).delete()
            } catch (Throwable t) {}
        }
        doc
    }

    /**
     * Delete user document.
     * @param id
     * @return true or false
     */
    boolean delete(def id) {
        Person curUser = springSecurityService.currentUser
        if (!curUser) {
            throw new UserNotFoundException("User is not logged in")
        }

        PersonalDocument doc = PersonalDocument.get(id)
        if (doc && doc.owner.id == curUser.id) {
            doc.delete()
            try {
                new File("${UPLOAD_DIR}/${doc.filename}").delete()
            } catch (Throwable t) {
                log.error "Cannot remove file from filesystem: '${UPLOAD_DIR}/${doc.filename}'"
            }
            return true
        }

        return false
    }

    /**
     * Get file instance from document object.
     * @param doc
     * @return File instance
     */
    def file(PersonalDocument doc) {
        return new File("${UPLOAD_DIR}/${doc.filename}")
    }

    /**
     * Get original file extension.
     * @param origName
     * @param defaultExtension
     * @return File extension or default value if the file has no extension
     */
    private String filenameExtension(String origName, String defaultExtension = "bin") {
        int idx = origName.lastIndexOf('.')
        if (idx == -1 || idx == origName.length()-1) {
            return defaultExtension
        }
        return origName.substring(idx+1)
    }

    /**
     * Generate file name.
     * @param user
     * @param extension
     * @return File name
     */
    private String generateFilename(Person user, String extension) {
        final Random rnd = new Random(System.currentTimeMillis())
        return "${user.id}_${System.currentTimeMillis()}_${rnd.nextInt(12345)}.${extension}"
    }
}
