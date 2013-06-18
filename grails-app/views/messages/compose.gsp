<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Serious Parachute - Compose message</title>
</head>
<body>

    <h1>Compose message</h1>
    <p>
        <g:link controller="messages" action="index">back to inbox</g:link>
    </p>

    <div class="message_form">
        <g:hasErrors bean="${msg}">
            <div class="errors">
                <g:renderErrors bean="${msg}" as="list" />
            </div>
        </g:hasErrors>
        <g:form controller="messages" action="compose" method="POST">
            <label>User</label> <g:select name="id" value="${id}" from="${userList}" optionKey="id" optionValue="fullname"/>
            <label>Subject</label> <g:textField name="subject" title="Subject"/>
            <br/><label>Text</label> <g:textArea cols="28" rows="6" name="text" title="Text"/>
            <g:submitButton name="Send" title="Send"/>
        </g:form>
    </div>

</body>
</html>
