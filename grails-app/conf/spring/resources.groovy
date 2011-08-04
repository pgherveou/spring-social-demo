import org.springframework.social.connect.support.ConnectionFactoryRegistry
import org.springframework.social.facebook.connect.FacebookConnectionFactory
import org.springframework.social.twitter.connect.TwitterConnectionFactory
import org.springframework.beans.factory.config.MapFactoryBean
import social.PostAfterConnectInterceptor

// Place your Spring DSL code here
beans = {
    connectionFactoryLocator(ConnectionFactoryRegistry) {
        connectionFactories = application.config.grails.plugins.springsocial.collect {provider ->

            switch (provider.key) {
                case "facebook":
                    log.debug "loading facebook config ..."
                    new FacebookConnectionFactory(provider.value.appId, provider.value.appSecret)
                    break
                case "twitter":
                    log.debug "loading twitter config ..."
                    new TwitterConnectionFactory(provider.value.consumerKey, provider.value.consumerSecret)
                    break
            }
        }
    }


    interceptors(MapFactoryBean) {
        sourceMap = [
                facebook: [new PostAfterConnectInterceptor(actionName: "facebookPostAfterConnect")],
                twitter: [new PostAfterConnectInterceptor(actionName: "twitterPostAfterConnect")],
        ]
    }

}
