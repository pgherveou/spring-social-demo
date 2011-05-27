<head>
<meta name='layout' content='demo' />
<title>Login</title>
<style type='text/css' media='screen'>
#signup {
	margin:15px 0px; padding:0px;
	text-align:center;
}
#signup .inner {
	width:260px;
	margin:0px auto;
	text-align:left;
	padding:10px;
	border-top:1px dashed #499ede;
	border-bottom:1px dashed #499ede;
	background-color:#EEF;
}
#signup .inner .fheader {
	padding:4px;margin:3px 0px 3px 0;color:#2e3741;font-size:14px;font-weight:bold;
}
#signup .inner .cssform p {
	clear: left;
	margin: 0;
	padding: 5px 0 8px 0;
	padding-left: 105px;
	border-top: 1px dashed gray;
	margin-bottom: 10px;
	height: 1%;
}
#signup .inner .cssform input[type='text'] {
	width: 120px;
}
#signup .inner .cssform label {
	font-weight: bold;
	float: left;
	margin-left: -105px;
	width: 100px;
}
#signup .inner .signup_message {color:red;}
#signup .inner .text_ {width:120px;}
#signup .inner .chk {height:12px;}
</style>
</head>

<body>
	<div id='signup'>
		<div class='inner'>
			<g:if test='${flash.message}'>
			<div class='signup_message'>${flash.message}</div>
			</g:if>
			<div class='fheader'>Sign up..</div>
			<g:form action='signup'  class='cssform'>
				<p>
					<label for='firstName'>First Name</label>
					<g:textField  class='text_' name='firstName' value="${user?.firstName}" />
				</p>
                <p>
					<label for='lastName'>Last Name</label>
					<g:textField  class='text_' name='lastName' value="${user?.lastName}" />
				</p>
                <p>
					<label for='username'>Login ID</label>
					<g:textField class='text_' name='username' value="${user?.username}" />
				</p>
				<p>
					<label for='password'>Password</label>
					<g:passwordField name='password'/>
				</p>
				<p>
                    <input type='submit' value='Signup' />
				</p>
			</g:form>
		</div>
	</div>

</body>