package demo

class SignupController {

    def springSecurityService
    def connectionFactoryLocator


    def index = {
        log.debug "starting sign up ..."
        if (session.connection) {
            def profile =  connectionFactoryLocator.getConnectionFactory(session.connection.providerId).createConnection(session.connection).fetchUserProfile()
            log.debug "using profile ${profile.properties}  to pre-fill registration form"
            return [user: profile]
        }
    }

    def signup = {

        log.debug "processing sign up"
        if (User.countByUsername(params.username)) {
            flash.message = "Error: User already exists"
            redirect(action: "index")
        } else {

            def userInstance = new User(params)
            userInstance.enabled = true
            log.debug("saving user : ${userInstance}")

            if (session.connection) {
                userInstance.addToUserConnections(session.connection.properties)
                log.debug("adding userConnections : ${userInstance.userConnections.join("\n")}")
            }

            if (userInstance.validate()) {
                userInstance.save(flush: true)
                UserRole.create userInstance, Role.findByAuthority('ROLE_USER'), true

                if (session.connection) {
                    session.removeAttribute("connection")
                    // handle post-signup
                }

                springSecurityService.reauthenticate userInstance.username

                flash.message = "${userInstance.username} created"
                redirect(uri: "/showcase.gsp")
            }
            else {
                log.error userInstance.errors.allErrors.join("\n ${it.toString()}")
                render(view: "create", model: [userInstance: userInstance])
            }
        }
    }

}
