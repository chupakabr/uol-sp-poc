<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Serious Parachute - Inbox</title>
</head>
<body>

    <h1>Compose</h1>
    <div class="message_form">
        <g:hasErrors bean="${msg}">
            <div class="errors">
                <g:renderErrors bean="${msg}" as="list" />
            </div>
        </g:hasErrors>
        <g:form controller="messages" action="compose" method="POST">
            <label>User</label> <g:select name="id" from="${userList}" optionKey="id" optionValue="fullname"/>
            <label>Subject</label> <g:textField name="subject" title="Subject"/>
            <br/><label>Text</label> <g:textArea cols="20" rows="4" name="text" title="Text"/>
            <g:submitButton name="Send" title="Send"/>
        </g:form>
    </div>

    <h1>Inbox</h1>
    <g:if test="${!entries || entries.isEmpty()}">
        <h3>No messages in your inbox</h3>
    </g:if>
    <g:else>
        <div class="entry_header">
            <div class="entry">
                <div class="msg_from">
                    From
                </div>
                <div class="msg_senton">
                    Sent on
                </div>
                <div class="msg_subject">
                    Subject
                </div>
                <div class="msg_text">
                    Message body
                </div>
                <div class="actions">
                    Actions
                </div>
            </div>
        </div>

        <div class="entry_list">
        <g:each in="${entries}">
            <div class="entry">
                <div class="msg_from">
                    ${it.sender.fullname}&nbsp;
                </div>
                <div class="msg_senton">
                    <g:formatDate date="${it.createdOn}" type="datetime" style="MEDIUM"/>&nbsp;
                </div>
                <div class="msg_subject">
                    ${it.subject.encodeAsHTML()}&nbsp;
                </div>
                <div class="msg_text">
                    ${it.message.encodeAsHTML()}&nbsp;
                </div>
                <div class="actions">
                    <g:link action="compose" id="${it.id}" title="Reply">Reply</g:link>
                    | <g:link action="delete" id="${it.id}" title="Delete">Delete</g:link>
                </div>
            </div>
        </g:each>
        </div>

        <div class="paginator">
            <g:paginate total="${cnt}" params="${params}"/>
        </div>

    </g:else>

</body>
</html>
