package social

import org.springframework.web.context.request.RequestContextHolder

class ProviderSignInController {

    def connectionFactoryLocator
    def springSecurityService
    def userConnectionService
    def grailsLinkGenerator



	/**
	 * Process a sign-in form submission by commencing the process of establishing a connection to the provider on behalf of the user.
	 */
	def signIn = {
        def connectionFactory = connectionFactoryLocator.getConnectionFactory(params.providerId)
        def webSupport = new ConnectSupport(grailsLinkGenerator.link(mapping:'signin', action:"oauthCallback", params:[providerId: params.providerId],  absolute: true))

        redirect url: webSupport.buildOAuthUrl(connectionFactory, RequestContextHolder.currentRequestAttributes())
    }

    /**
     * Process the authentication callback from an OAuth 1 service provider
     */
    def oauthCallback = {
        def connectionFactory = connectionFactoryLocator.getConnectionFactory(params.providerId)
        def webSupport = new ConnectSupport(grailsLinkGenerator.link(mapping:'signin', action:"oauthCallback", params:[providerId: params.providerId],  absolute: true))
        def connection = webSupport.completeConnection(connectionFactory, RequestContextHolder.currentRequestAttributes())
		def user = userConnectionService.findUserWithConnection(connection)

        if (!user) {

			session.connection = connection.createData()
			redirect(controller: "signup")
		} else  {
            springSecurityService.reauthenticate user.username
			userConnectionService.updateConnection(connection)
			redirect(uri: "")
		}
    }
}
