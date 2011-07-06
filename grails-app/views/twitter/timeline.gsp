<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>
<script src="http://platform.twitter.com/anywhere.js?id=7yWLgCOuQhIpPyffm0o2Vg&v=1" type="text/javascript"></script>
<script type="text/javascript">
    twttr.anywhere(function (T) {
        T(".feed").linkifyUsers();
    });
</script>

<h3>Your Twitter ${timelineName} Timeline</h3>

<h4>Post a tweet</h4>
<g:form action="tweet">
    <g:textArea name="message" rows="2" cols="80"/><br/>
    <input type="submit" value="Post Tweet"/>
</g:form>



<div class="feed">
    <ul class="imagedList">
        <g:each in="${timeline}" var="tweet">
            <li class="imagedItem">
                <div class="image">
                    <g:if test="${tweet.profileImageUrl}">
                        <img src="${tweet.profileImageUrl}" align="left"/>
                    </g:if>
                </div>

                <div class="content">
                    <strong><a href="http://twitter.com/${tweet.fromUser}">
                        ${tweet.fromUser}
                    </a></strong><br/>
                    ${tweet.text}
                    <br/>
                    <span class="postTime">${tweet.createdAt}</span>
                </div>
            </li>
        </g:each>
    </ul>
</div>
</body>
</html>


