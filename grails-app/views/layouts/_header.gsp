<sec:ifLoggedIn>
    Hello <sec:username/> ! - <g:link controller="logout">Logout</g:link>
</sec:ifLoggedIn>
<sec:ifNotLoggedIn>
    <g:link controller="login">Login</g:link>
    <g:link controller="login" action="auth">Sign In</g:link>
</sec:ifNotLoggedIn>