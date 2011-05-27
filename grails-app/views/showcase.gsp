<html>
    <head>
        <title>Welcome to Grails</title>
        <meta name="layout" content="demo" />
    </head>

<body>
<p>Some test user/password pairs you may use are:</p>
<ul>
    <li>habuma/freebirds</li>
    <li>kdonald/melbourne</li>
    <li>rclarkson/atlanta</li>
</ul>

<p>Or you can <g:link controller="signup">signup</g:link> with a new account.</p>

<!-- TWITTER SIGNIN -->
<g:form controller="providerSignin" params="[providerId:'twitter']">
    <button type="submit">
        <img src="${resource(dir: 'images/social/twitter', file: 'sign-in-with-twitter-d.png')}"/>
    </button>
</g:form>

<!-- FACEBOOK SIGNIN -->
<g:form controller="providerSignin" params="[providerId:'facebook']">
    <button type="submit">
        <img src="${resource(dir: 'images/social/facebook', file: 'sign-in-with-facebook.png')}"/>
    </button>
</g:form>

</body>
</html>

