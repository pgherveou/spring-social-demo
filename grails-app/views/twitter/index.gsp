<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>

<h3>Your Twitter Profile</h3>

<p>Hello, ${profile.name}!</p>
<img src="${profile.profileImageUrl}"/>
<dl>
    <dt>Twitter ID:</dt>
    <dd>${profile.id}</dd>
    <dt>Screen name:</dt>
    <dd><a href="${profile.profileUrl}" target="_blank">${profile.screenName}</a></dd>
    <dt>Description:</dt>
    <dd>${profile.description}</dd>
    <dt>Location:</dt>
    <dd>${profile.location}</dd>
    <dt>URL:</dt>
    <dd><a href="${profile.url}">${profile.url}</a></dd>
</dl>

<g:form action="disconnect">
    <button type="submit">Disconnect from Twitter</button>
</g:form>

</body>
</html>