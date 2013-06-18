<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Serious Parachute - Student Documents</title>
</head>
<body>

    <h1>Student Documents</h1>

    <p>
        Student:
        <g:link controller="student" action="view" id="${user.id}">${user.fullname?.encodeAsHTML()}</g:link>
    </p>

    <g:if test="${!entries || entries.isEmpty()}">
        <h3>No documents</h3>
    </g:if>
    <g:else>
        <div class="entry_header">
            <div class="entry">
                <div class="id">
                    ID
                </div>
                <div class="doc_title">
                    Title
                </div>
                <div class="doc_origname">
                    Original filename
                </div>
                <div class="doc_created_on">
                    Uploaded on
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
                        ${it.id}
                    </div>
                    <div class="doc_title">
                        <g:link controller="student" action="document" id="${it.id}">${it.title}</g:link>
                    </div>
                    <div class="doc_origname">
                        ${it.origFilename}&nbsp;
                    </div>
                    <div class="doc_created_on">
                        <g:formatDate date="${it.createdOn}" type="datetime" style="MEDIUM"/>&nbsp;
                    </div>
                    <div class="actions">
                        <g:link action="deleteDocument" id="${it.id}" title="Delete">Delete</g:link>
                    </div>
                </div>
            </g:each>
            <div class="paginator">
                <g:paginate total="${cnt}" params="${params}"/>
            </div>
        </div>
    </g:else>

</body>
</html>
