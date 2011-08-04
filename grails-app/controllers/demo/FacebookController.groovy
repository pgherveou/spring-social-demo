package demo

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class FacebookController {

    def userConnectionService
    def api

    def beforeInterceptor = [action: this.&setApi, except: 'connect']
    private setApi() {
        log.debug "set facebook api"
        api = userConnectionService.facebook()
        if (!api) {
            log.debug "no facebook api redirect to connect"
            redirect action:"connect"
            return  false
        }
    }

    def connect = {}

    def feed = {
        ["feed": api.feedOperations().feed]
    }

    def postUpdate = {
        redirect(action: "feed")
    }

    def friends = {
        ["friends": api.friendOperations().friendProfiles]
    }

    def albums = {
        ["albums": api.mediaOperations().albums]
    }

    def showAlbum = {
        ["album": api.mediaOperations().getAlbum(params.id),
                "photos": api.mediaOperations().getPhotos(params.id)]
    }

    def index = {
        ["profile": api.userOperations().userProfile]
    }

}
