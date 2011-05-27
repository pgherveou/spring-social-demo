<!DOCTYPE html>
<html>
<head>
    <title>Spring Social Showcase</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'page.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'form.css')}"/>
    <g:layoutHead/>
    <g:javascript library="application"/>
</head>

<body>
<div id="header">
    <h1><a href="/">Spring Social Showcase</a></h1>
</div>

<div id="leftNav">
    <g:render template="/layouts/menu"/>
</div>

<div id="content">

    <g:render template="/layouts/header"/>

    <g:layoutBody/>
</div>

</body>
</html>
