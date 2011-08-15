<%@ page import="org.springframework.social.facebook.api.ImageType" %>
<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>
<h3>Your Facebook Friends</h3>

<ul class="friends">

    <g:each in="${friends}" var="friend">
        <li>
            ${friend.name}
            <img src="http://graph.facebook.com/${friend.id}/picture" align="middle"/>
        </li>


    </g:each>
</ul>
</body>
</html>