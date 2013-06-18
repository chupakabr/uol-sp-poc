<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Serious Parachute - Admin Console</title>
</head>
<body>

    <h1>User management</h1>

    <div class="entry_header">
        <div class="entry">
            <div class="id">
                ID
            </div>
            <div class="email">
                Email (username)
            </div>
            <div class="fullname">
                Fullname
            </div>
            <div class="address">
                Address
            </div>
            <div class="phone">
                Phone
            </div>
            <div class="enabled_on">
                Enabled on
            </div>
            <div class="disabled_on">
                Disabled on
            </div>
            <div class="enabled">
                Enabled
            </div>
            <div class="actions">
                Actions
            </div>
        </div>
    </div>

    <div class="entry_list">
    <g:each in="${entries}">
        <div class="entry">
            <div class="id">
                <g:link controller="student" action="view" id="${it.id}">${it.id}</g:link>
            </div>
            <div class="email">
                <g:link controller="student" action="view" id="${it.id}">${it.username}</g:link>
            </div>
            <div class="fullname">
                ${it.fullname}&nbsp;
            </div>
            <div class="address">
                ${it.address}&nbsp;
            </div>
            <div class="phone">
                ${it.phone}&nbsp;
            </div>
            <div class="enabled_on">
                <g:formatDate date="${it.enabledOn}" type="date" style="MEDIUM"/>&nbsp;
            </div>
            <div class="disabled_on">
                <g:formatDate date="${it.disabledOn}" type="date" style="MEDIUM"/>&nbsp;
            </div>
            <div class="enabled">
                ${it.enabled}
            </div>
            <div class="actions">
                <g:link controller="messages" action="compose" id="${it.id}" title="Message">Message</g:link>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <g:if test="${it.enabled}">
                        | <g:link controller="admin" action="disableUser" id="${it.id}" title="Disable">Disable</g:link>
                    </g:if>
                    <g:else>
                        | <g:link controller="admin" action="enableUser" id="${it.id}" title="Enable">Enable</g:link>
                    </g:else>
                    | <g:link controller="admin" action="delete" id="${it.id}" title="Delete">Delete</g:link>
                </sec:ifAnyGranted>
            </div>
        </div>
    </g:each>
    </div>

    <div class="paginator">
        <g:paginate total="${cnt}" params="${params}"/>
    </div>

</body>
</html>
