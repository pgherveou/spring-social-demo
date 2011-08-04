<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="demo"/>
</head>

<body>

<script src="http://platform.twitter.com/anywhere.js?id=7yWLgCOuQhIpPyffm0o2Vg&v=1" type="text/javascript"></script>
<script type="text/javascript" >
            twttr.anywhere(function (T) {
                T(".feed").linkifyUsers();
            });
</script>

    <h3>Your Twitter ${dmListType} Messages</h3>


    <g:form action="messages">
        <p>Send a message:</p>
        <label for="to">To:</label><g:textField name="to"/>
        <br/>
        <g:textArea name="text" rows="2" cols="80"/>
        <br/>
        <input type="submit" value="Send Message"/>
    </g:form>

    <ul class="choices">
        <li><g:link action="messages">Inbox</g:link></li>
        <li><g:link action="messages" params="[type:'sent']">Sent</g:link></li>
    </ul>


    <div class="feed">
        <ul class="imagedList">
            <g:each in="${directMessages}" var="dm">
                <li class="imagedItem">
                    <div class="image">
                        <g:if test="${dm.sender.profileImageUrl}">
                            <img src="${dm.sender.profileImageUrl}" align="left"/>
                        </g:if>
                    </div>

                    <div class="content">
                        <strong>
                            <a href="http://twitter.com/${dm.sender.screenName}">
                                ${dm.sender.screenName}
                            </a>
                        </strong>
                        <br/>
                        <span class="dmRecipient">to ${dm.recipient.screenName}</span>
                        <br/>
                        ${dm.text}
                        <br/>
                        <span class="postTime">${dm.createdAt}</span>
                    </div>
                </li>
            </g:each>
        </ul>
    </div>

</body>
</html>



