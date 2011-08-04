package social

import org.springframework.social.connect.Connection
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.twitter.api.Twitter

class UserConnectionService {

    def springSecurityService

    def findAllConnections() {
        def user = springSecurityService.currentUser
        UserConnection.withCriteria {
            eq 'user', user
            order('providerId')
            order('rank')
        }.toList().inject([:]) {map, userConnection ->
            map[userConnection.providerId] = userConnection.connection()
            map
        }
    }

    def findConnections(String providerId) {
        def user = springSecurityService.currentUser
        UserConnection.createCriteria().list {
            and {
                eq 'user', user
                eq 'providerId', providerId
            }
            order('rank')
        }*.connection()
    }

    def findPrimaryConnection(String providerId) {
        def user = springSecurityService.currentUser
        UserConnection.withCriteria(uniqueResult: true) {
            and {
                eq 'user', user
                eq 'providerId', providerId
            }
            order('rank')
        }?.connection()
    }

    def removeConnections(String providerId) {

        def user = springSecurityService.currentUser

        UserConnection.createCriteria().list {
            and {
                eq 'user', user
                eq 'providerId', providerId
            }
        }*.delete()
    }

    def findUserWithConnection(Connection<?> connection) {
        UserConnection.findByProviderIdAndProviderUserId(connection.key.providerId, connection.key.providerUserId)?.user
    }

    def addConnection(Connection<?> connection) {
        def user = springSecurityService.currentUser
        def c = UserConnection.createCriteria()
        def maxRank = c.get {
            projections {
                max("rank")
            }
            and {
                eq "providerId", connection.key.providerId
                eq "user", user
            }
        }

        UserConnection uc = new UserConnection(connection.createData().properties)
        uc.user = user
        uc.rank = maxRank ? maxRank + 1 : 1
        uc.save()
    }

    def updateConnection(connection) {
        def userConnection = UserConnection.findByProviderIdAndProviderUserId(connection.key.providerId, connection.key.providerUserId)
        userConnection.properties = connection.properties
        userConnection.save()
    }

    Facebook facebook() {
        findPrimaryConnection("facebook")?.api
    }

    Twitter twitter() {
        findPrimaryConnection("twitter")?.api
    }

}
