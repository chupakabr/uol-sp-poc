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
                <g:link action="view" id="${it.id}">${it.id}</g:link>
            </div>
            <div class="email">
                <g:link action="view" id="${it.id}">${it.username}</g:link>
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
                <g:if test="${it.enabled}">
                    <g:link action="disable" id="${it.id}" title="Disable">Disable</g:link>
                </g:if>
                <g:else>
                    <g:link action="enable" id="${it.id}" title="Enable">Enable</g:link>
                </g:else>
                | <g:link action="delete" id="${it.id}" title="Delete">Delete</g:link>
            </div>
        </div>
    </g:each>
    </div>

    <div class="paginator">
        <g:paginate total="${cnt}" params="${params}"/>
    </div>

</body>
</html>
