<%@ page import="com.uol.seriousparachute.PersonalMessage; com.uol.seriousparachute.Person" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Serious Parachute"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <g:layoutHead/>
    <r:layoutResources />
</head>
<body>

<div id="top_header">
    <g:link uri="/">Home</g:link> |
    <sec:ifLoggedIn>
        <% def cabinetCtrl = null %>
        <sec:ifAnyGranted roles="ROLE_USER">
            <% cabinetCtrl = "student" %>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_STAFF">
            <% cabinetCtrl = "staff" %>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_ADMIN">
            <% cabinetCtrl = "admin" %>
        </sec:ifAnyGranted>
        <g:link controller="${cabinetCtrl}">Cabinet</g:link> |

        <%
            def cnt = 0
            Person curUser = Person.findByUsername(sec.username())
            if (curUser) {
                cnt = PersonalMessage.countByTarget(curUser)
            }
        %>
        <g:link controller="messages" title="Messages">inbox</g:link> (${cnt}) |

        <g:link controller="logout" title="Logout">Logout</g:link> (<sec:username/>)
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <g:link controller="login" title="Logout">Login</g:link>
    </sec:ifNotLoggedIn>
</div>

<div id="top_container">
    <g:if test="${flash?.message}">
        <div class="flash_message">
            ${flash.message}
        </div>
    </g:if>
    <g:layoutBody/>
</div>

<div id="top_footer">
    <p>
        2013 &copy; Serious Parachute, Admission Process Component
    </p>
</div>

<g:javascript library="application"/>
<r:layoutResources />
</body>
</html>
