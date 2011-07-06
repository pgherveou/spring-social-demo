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

<h3>Your Twitter Friends</h3>

<ul class="imagedList">
    <g:each in="${profiles}" var="profile">
        <li class="imagedItem">
            <div class="image">
                <g:if test="${profile.profileImageUrl}">
                    <img src="${profile.profileImageUrl}" width="48" height="48" align="left"/>
                </g:if>
            </div>

            <div class="content">
                <p>
                    <a href="http://twitter.com/${profile.screenName}">
                        ${profile.screenName}
                    </a>
                </p>
            </div>
        </li>
    </g:each>
</ul>

</body>
</html>