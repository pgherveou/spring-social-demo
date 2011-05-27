package demo

import org.springframework.social.connect.signin.web.ProviderSignInUtils
import org.springframework.web.context.request.RequestContextHolder
import demo.User
import demo.UserRole
import demo.Role

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class SignupController {

    def signinService
    def springSecurityService

    def index = {

        log.debug "starting sign up ..."
        def connection = ProviderSignInUtils.getConnection(RequestContextHolder.currentRequestAttributes())
        if (connection != null) {
            def profile = connection.fetchUserProfile()
            log.debug "using profile $profile  to pre-fill registration form"
            return [user: profile]
        }
    }

    def signup = {

        log.debug "processing sign up"

        def user = new User(
                username: params.username,
                password: springSecurityService.encodePassword(params.password),
                enabled: true
        ).save(flush: true)
        UserRole.create user, Role.findByAuthority('ROLE_USER') , true

        signinService.signin(user.username)
        ProviderSignInUtils.handlePostSignUp(RequestContextHolder.currentRequestAttributes())


        redirect(url: ConfigurationHolder.config.grails.serverURL)
    }

}
