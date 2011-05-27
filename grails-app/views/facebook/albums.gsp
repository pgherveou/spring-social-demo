<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>

<h3>Your Facebook Photo Albums</h3>

<ul class="albums">
    <g:each in="${albums}" var="album">
        <li>
            <g:link action="showAlbum" id="${album.id}">${album.name}</g:link>
        </li>
    </g:each>
</ul>
</body>
</html>