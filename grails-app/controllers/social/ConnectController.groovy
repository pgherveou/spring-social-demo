package social

import grails.plugins.springsecurity.Secured
import org.springframework.web.context.request.RequestContextHolder

@Secured(['ROLE_USER'])
class ConnectController {

    def connectionFactoryLocator
    def springSecurityService
    def userConnectionService
    def grailsLinkGenerator
    def interceptors

    //interceptors[params.providerId]*.preConnect(params, session)
    //interceptors[params.providerId]*.postConnect(connection, session)



    /**
     * Render the status of the connections to the service provider to the user as HTML in their web browser.
     */
    def connectionStatus() {
        [
                "providerIds": connectionFactoryLocator.registeredProviderIds(),
                "connections": userConnectionService.findAllConnections()
        ]
    }

    /**
     * Render the status of the connections to the service provider to the user as HTML in their web browser.
     */
    def status = {
        def connections = userConnectionService.findConnections(params.providerId)
        if (connections.isEmpty()) {
            log.debug "no connection for ${params.providerId}, redirecting"
            redirect(View: "${params.providerId}Connect")
            return
        }
        render view: "${params.providerId}Connect", model: [connections: connections]
    }

    /**
     * Process a connect form submission by commencing the process of establishing a connection to the provider on behalf of the member.
     * For OAuth1, fetches a new request token from the provider, temporarily stores it in the session, then redirects the member to the provider's site for authorization.
     * For OAuth2, redirects the user to the provider's site for authorization.
     */
    def connect = {
        def connectionFactory = connectionFactoryLocator.getConnectionFactory(params.providerId)
        def callbackUrl = grailsLinkGenerator.link(mapping: 'connect', action: "oauthCallback", params: [providerId: params.providerId], absolute: true)
        log.debug "callback url : ${callbackUrl}"
        def webSupport = new ConnectSupport(callbackUrl)

        redirect url: webSupport.buildOAuthUrl(connectionFactory, RequestContextHolder.currentRequestAttributes())
    }

    /**
     * Process the authorization callback from an OAuth service provider.
     */
    def oauthCallback = {
        def connectionFactory = connectionFactoryLocator.getConnectionFactory(params.providerId)
        def webSupport = new ConnectSupport(grailsLinkGenerator.link(mapping: 'connect', action: "oauthCallback", params: [providerId: params.providerId], absolute: true))
        def connection = webSupport.completeConnection(connectionFactory, RequestContextHolder.currentRequestAttributes())

        userConnectionService.addConnection(connection)
        redirect(controller: params.providerId)
    }

    /**
     * Remove all provider connections for a user account.
     */
    def removeConnections = {
        userConnectionService.removeConnections(params.providerId)
        redirect(controller: params.providerId)
    }

    /**
     * Remove a single provider connection associated with a user account.
     */
    def removeConnection = {
        userConnectionService.removeConnections(params.providerId, params.providerUserId)
        redirect(controller: params.providerId)
    }


}
