/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package social;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuth1Version;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;


public class ConnectSupport {

	private String callbackUrl;
    private boolean useAuthenticateUrl = false;

    ConnectSupport(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
/**
	 * Builds the provider URL to redirect the user to for connection authorization.
	 * @param connectionFactory the service provider's connection factory e.g. FacebookConnectionFactory
	 * @param request the current web request
	 * @return the URL to redirect the user to for authorization
	 * @throws IllegalArgumentException if the connection factory is not OAuth1 based.
	 */
	public String buildOAuthUrl(ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		if (connectionFactory instanceof OAuth1ConnectionFactory) {
			return buildOAuth1Url((OAuth1ConnectionFactory<?>) connectionFactory, request);
		} else if (connectionFactory instanceof OAuth2ConnectionFactory) {
			return buildOAuth2Url((OAuth2ConnectionFactory<?>) connectionFactory, request);
		} else {
			throw new IllegalArgumentException("ConnectionFactory not supported");
		}		
	}

	/**
	 * Complete the connection to the OAuth1 provider.
	 * @param connectionFactory the service provider's connection factory e.g. FacebookConnectionFactory
	 * @param request the current web request
	 * @return a new connection to the service provider
	 */
	public Connection<?> completeConnection(OAuth1ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		String verifier = request.getParameter("oauth_verifier"); 
		AuthorizedRequestToken requestToken = new AuthorizedRequestToken(extractCachedRequestToken(request), verifier);
		OAuthToken accessToken = connectionFactory.getOAuthOperations().exchangeForAccessToken(requestToken, null);
		return connectionFactory.createConnection(accessToken);
	}

	/**
	 * Complete the connection to the OAuth2 provider.
	 * @param connectionFactory the service provider's connection factory e.g. FacebookConnectionFactory
	 * @param request the current web request
	 * @return a new connection to the service provider
	 */
	public Connection<?> completeConnection(OAuth2ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		String code = request.getParameter("code");
		AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, callbackUrl, null);
		return connectionFactory.createConnection(accessGrant);		
	}

	// internal helpers
	private String buildOAuth1Url(OAuth1ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken requestToken;
		String authorizeUrl;
		if (oauthOperations.getVersion() == OAuth1Version.CORE_10_REVISION_A) {
			requestToken = oauthOperations.fetchRequestToken(callbackUrl, null);
			authorizeUrl = buildOAuth1Url(oauthOperations, requestToken.getValue(), OAuth1Parameters.NONE);
		} else {
			requestToken = oauthOperations.fetchRequestToken(null, null);				
			authorizeUrl = buildOAuth1Url(oauthOperations, requestToken.getValue(), new OAuth1Parameters(callbackUrl));
		}
		request.setAttribute(OAUTH_TOKEN_ATTRIBUTE, requestToken, RequestAttributes.SCOPE_SESSION);
		return authorizeUrl;
	}

	private String buildOAuth2Url(OAuth2ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuth2Parameters parameters = new OAuth2Parameters(callbackUrl, request.getParameter("scope"));
		if (useAuthenticateUrl) { 
			return oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, parameters);						
		} else {
			return oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, parameters);			
		}
	}

	private String buildOAuth1Url(OAuth1Operations oauthOperations, String requestToken, OAuth1Parameters parameters) {
		if (useAuthenticateUrl) {
			return oauthOperations.buildAuthenticateUrl(requestToken, parameters);			
		} else {
			return oauthOperations.buildAuthorizeUrl(requestToken, parameters);
		}
	}

	private OAuthToken extractCachedRequestToken(WebRequest request) {
		OAuthToken requestToken = (OAuthToken) request.getAttribute(OAUTH_TOKEN_ATTRIBUTE, RequestAttributes.SCOPE_SESSION);
		request.removeAttribute(OAUTH_TOKEN_ATTRIBUTE, RequestAttributes.SCOPE_SESSION);
		return requestToken;
	}
	
	private static final String OAUTH_TOKEN_ATTRIBUTE = "oauthToken";

}