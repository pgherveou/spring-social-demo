<html>
<head>
    <title>Spring Social Showcase: Connect to Facebook</title>
    <meta name="layout" content="demo"/>
</head>
<body>
<g:form controller="connect" params="[providerId:'facebook']">
    <g:hiddenField name="scope" value="publish_stream,offline_access"/>

    <div class="formInfo">
        <p>Click the button to connect Spring Social Showcase with your Facebook account.</p>
    </div>

    <p>
        <button type="submit">
            <img src="${createLinkTo(dir: 'images/social/facebook', file: 'connect_light_medium_short.gif')}"/>
        </button>
    </p>

    <label for="postToWall">
        <g:checkBox id="postToWall"
                    name="postToWallFlag"/> Tell your friends about Spring Social Showcase on your Facebook wall
    </label>

</g:form>

</body>
</html>
