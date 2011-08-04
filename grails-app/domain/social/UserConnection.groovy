package social

import org.springframework.social.connect.ConnectionData
import demo.User

@groovy.transform.EqualsAndHashCode
@groovy.transform.ToString(includeNames=true,includeFields=true,excludes="ConnectionFactoryLocator")
class UserConnection implements Serializable {

    transient def ConnectionFactoryLocator

    String providerId
    String providerUserId = ""
    int rank
    String displayName
    String profileUrl
    String imageUrl
    String accessToken
    String secret
    String refreshToken
    Long expireTime

    static belongsTo = [user: User]

    def connection() {
        def c =  new ConnectionData(providerId, providerUserId, displayName, profileUrl, imageUrl, accessToken, secret, refreshToken, expireTime)
        def connectionFactory = connectionFactoryLocator.getConnectionFactory(providerId)
		connectionFactory.createConnection(c)
    }

    static constraints = {

        user unique: ['providerId', 'rank'], display: false
        displayName nullable: true
        profileUrl nullable: true
        imageUrl nullable: true
        accessToken nullable: true, display: false
        secret display: false, nullable: true
        refreshToken display: false, nullable: true
        expireTime nullable: true
    }

    static namedQueries = {
        primaryConnection {userId, providerId ->
            eq "userId", userId
            eq "providerId", providerId
            eq "rank", 1
        }
    }


}

