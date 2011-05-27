<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>
<h3>Your Facebook Friends</h3>

<ul class="friends">
    <g:each in="${friends}" var="friend">
        <li><img src="http://graph.facebook.com/${friend.id}/picture" align="middle"/>${friend.name}</li>
    </g:each>
</ul>
</body>
</html>