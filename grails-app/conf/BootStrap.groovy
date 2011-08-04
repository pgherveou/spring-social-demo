import demo.Role
import demo.User
import demo.UserRole

class BootStrap {

    def springSecurityService

    def init = { servletContext ->

        def userRole = new Role(authority: "ROLE_USER").save(flush: true)

        def user1 = new User(
                username: "pg",
                password: "pg",
                enabled: true
        ).save(flush: true)
        UserRole.create user1, userRole, true

        def user2 = new User(
                username: "habuma",
                password: "freebirds",
                enabled: true
        ).save(flush: true)
        UserRole.create user2, userRole, true

        def user3 = new User(
                username: "kdonald",
                password: "melbourne",
                enabled: true
        ).save(flush: true)
        UserRole.create user3, userRole, true

        def user4 = new User(
                username: "rclarkson",
                password: "atlanta",
                enabled: true
        ).save(flush: true)
        UserRole.create user4, userRole, true


    }
    def destroy = {
    }
}