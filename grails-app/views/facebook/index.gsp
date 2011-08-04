<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>
<h3>Your Facebook Profile</h3>

<p>Hello, ${profile.firstName}!</p>
<dl>
    <dt>Facebook ID:</dt>
    <dd>${profile.id}</dd>
    <dt>Name:</dt>
    <dd>${profile.name}</dd>
    <dt>Email:</dt>
    <dd>${email}</dd>
</dl>


<g:form method="delete" controller="connect" action="disconnect" params="[providerId:'facebook']">
    <button type="submit">Disconnect from Facebook</button>
</g:form>
</body>
</html>