<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Serious Parachute - Update personal information</title>
</head>
<body>

    <h1>Update personal information</h1>
    <p>
        <g:link controller="student" action="view" id="${entry.id}">back to profile</g:link>
    </p>

    <div class="profile_form">
        <g:hasErrors bean="${entry}">
            <div class="errors">
                <g:renderErrors bean="${entry}" as="list" />
            </div>
        </g:hasErrors>
        <g:form controller="student" action="update" method="POST">
            <label>Fullname</label> <g:textField name="fullname" title="Fullname" value="${entry.fullname}"/>
            <br/><label>Address</label> <g:textField name="address" title="Address" value="${entry.address}"/>
            <br/><label>Phone</label> <g:textField name="phone" title="Phone" value="${entry.phone}"/>
            <br/><g:submitButton name="Update" title="Update"/>
        </g:form>
    </div>

</body>
</html>
