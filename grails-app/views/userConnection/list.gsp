
<%@ page import="social.UserConnection" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userConnection.label', default: 'UserConnection')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-userConnection" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-userConnection" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="displayName" title="${message(code: 'userConnection.displayName.label', default: 'Display Name')}" />
					
						<g:sortableColumn property="profileUrl" title="${message(code: 'userConnection.profileUrl.label', default: 'Profile Url')}" />
					
						<g:sortableColumn property="imageUrl" title="${message(code: 'userConnection.imageUrl.label', default: 'Image Url')}" />
										
					</tr>
				</thead>
				<tbody>
				<g:each in="${userConnectionInstanceList}" status="i" var="userConnectionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
					
						<td>${fieldValue(bean: userConnectionInstance, field: "displayName")}</td>
					
						<td><a href="${fieldValue(bean: userConnectionInstance, field: "profileUrl")}">profile</a></td>
					
						<td><img src="${fieldValue(bean: userConnectionInstance, field: "imageUrl")}" /></td>
					
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userConnectionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
