<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>
<h3>Your Facebook Feed</h3>

<g:form action="postUpdate">
    <p>Post to your Facebook wall:<p>
    <g:textArea name="message" rows="2" cols="60"/>
    <br/>
    <input type="submit" value="Post"/>
</g:form>

<div class="feed">
    <ul class="feedList">
        <g:each in="${feed}" var="post">
            <li class="post">
                <p>
                    <g:if test="${post.picture}">
                        <img src="${post.picture}" align="top"/>
                    </g:if>
                    ${post.message}"  - "${post.name}" />
                </p>
            </li>
        </g:each>
    </ul>
</div>
</body>
</html>