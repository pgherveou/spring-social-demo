<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>
<h3>Current Twitter Trends</h3>

<g:each in="${trends}" var="trend">
    <g:link action="search" params="${[query:trend.query]}">${trend.name}</g:link>
    <br/>
</g:each>

</body>
</html>