package demo

import org.springframework.social.connect.web.ProviderSignInUtils
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
        if (User.countByUsername(params.username)) {
            flash.message = "Error: User already exists"
            redirect(action: "index")
        }

        def userInstance = new User(params)
        userInstance.password = springSecurityService.encodePassword(params.password)
        userInstance.enabled = true

        if (userInstance.save(flush: true)) {

            UserRole.create userInstance, Role.findByAuthority('ROLE_USER'), true
            springSecurityService.reauthenticate userInstance.username
            ProviderSignInUtils.handlePostSignUp(userInstance.username, RequestContextHolder.currentRequestAttributes())

            flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.username])
            redirect(url: ConfigurationHolder.config.grails.serverURL)

        }
        else {
            render(view: "create", model: [userInstance: userInstance])
        }



    }

}
