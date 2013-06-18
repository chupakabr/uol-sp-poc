<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Serious Parachute - Upload document</title>
</head>
<body>

    <h1>Upload document</h1>
    <p>
        <g:link controller="student" action="documents">back to documents</g:link>
    </p>

    <div class="document_form">
        <g:hasErrors bean="${entry}">
            <div class="errors">
                <g:renderErrors bean="${entry}" as="list" />
            </div>
        </g:hasErrors>
        <g:uploadForm controller="student" action="uploadDocument" method="POST">
            <label>Title</label> <g:textField name="title" title="Title" value="${entry?.title}"/>
            <br/><label>File</label> <input type="file" name="file" title="File"/>
            <br/><g:submitButton name="Upload" title="Upload"/>
        </g:uploadForm>
    </div>

</body>
</html>
