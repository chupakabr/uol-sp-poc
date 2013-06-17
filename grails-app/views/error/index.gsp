<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css"></g:if>
	</head>
	<body>
		<g:if env="development">
            <g:if test="${flash?.message}">
                <p>Error: ${flash.message}</p>
            </g:if>
			<g:renderException exception="${exception}" />
		</g:if>
		<g:else>
			<ul class="errors">
				<li>An error has occurred</li>
                <g:if test="${flash?.message}">
                    <li>Error: ${flash.message}</li>
                </g:if>
			</ul>
		</g:else>
	</body>
</html>
