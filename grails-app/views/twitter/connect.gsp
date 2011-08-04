<html>
<head>
    <title>Spring Social Showcase: Connect to Twitter</title>
    <meta name="layout" content="demo"/>
</head>
<body>
<g:form controller="connect" params="[providerId:'twitter']">

    <div class="formInfo">
        <p>Click the button to connect Spring Social Showcase with your Twitter account.</p>
    </div>

    <p>
        <button type="submit">
            <img src="${createLinkTo(dir: 'images/social/twitter', file: 'connect-with-twitter.png')}"/>
        </button>
    </p>

    <label for="postToWall">
        <g:checkBox id="postToWall"
                    name="postToWallFlag"/> Tell your friends about Spring Social Showcase on your Twitter wall
    </label>

</g:form>

</body>
</html>
