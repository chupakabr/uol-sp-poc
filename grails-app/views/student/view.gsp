<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Serious Parachute - User</title>
</head>
<body>

    <h1>User profile: ${entry.username?.encodeAsHTML()}</h1>

    <p>
    <g:if test="${entry.username != String.valueOf(sec.username())}">
        <g:link controller="messages" action="compose" id="${entry.id}">Send message</g:link>
    </g:if>
    <g:else>
            <g:link controller="student" action="update">Update profile</g:link>
            <sec:ifNotGranted roles="ROLE_ADMIN,ROLE_STAFF">
                | <g:link controller="student" action="uploadDocument">Upload document</g:link>
            </sec:ifNotGranted>
    </g:else>
    | <g:link controller="student" action="documents" id="${entry.id}">Documents</g:link>
    </p>
    <br/>

    <g:if test="${entry.enabled}">
        <p>Enabled on: <g:formatDate date="${entry.enabledOn}" type="date" style="MEDIUM"/></p>
    </g:if>
    <g:else>
        <p>Disabled on: <g:formatDate date="${entry.disabledOn}" type="date" style="MEDIUM"/></p>
    </g:else>
    <br/>

    <p>Fullname: ${entry.fullname?.encodeAsHTML() ?: 'none'}</p>
    <p>Address: ${entry.address?.encodeAsHTML() ?: 'none'}</p>
    <p>Phone: ${entry.phone?.encodeAsHTML() ?: 'none'}</p>

</body>
</html>
