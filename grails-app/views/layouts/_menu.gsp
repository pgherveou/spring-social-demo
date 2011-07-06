<h4><g:link controller="twitter">Twitter</g:link></h4>


<social:ifConnected providerId="twitter">
<ul class="menu">
	<li><g:link controller="twitter">User Profile</g:link></li>
	<li><g:link controller="twitter" action="timeline">Timeline</g:link></li>
	<li><g:link controller="twitter" action="friends">Friends</g:link></li>
	<li><g:link controller="twitter" action="followers">Followers</g:link></li>
	<li><g:link controller="twitter" action="trends">Current Trends</g:link></li>
</ul>
</social:ifConnected>

<h4><g:link controller="facebook">Facebook</g:link></h4>

<social:ifConnected providerId="facebook">
<ul class="menu">
	<li><g:link controller="facebook">User Profile</g:link></li>
	<li><g:link controller="facebook" action="feed">Feed</g:link></li>
	<li><g:link controller="facebook" action="friends">Friends</g:link></li>
	<li><g:link controller="facebook" action="albums">Albums</g:link></li>
</ul>
</social:ifConnected>