package demo

import javax.inject.Inject
import javax.inject.Provider
import org.springframework.social.facebook.api.Facebook
import spring.social.SocialApiProviderService

class FacebookController {

    def socialApiProviderService

    private def getFacebookApi = {

        def api = socialApiProviderService.facebook()
        if (!api) {
            redirect(controller: "providerConnect", params: ["providerId": "facebook"])
        }
        return api;
    }

    def feed = {
        ["feed": getFacebookApi().feedOperations().feed]
    }

    def postUpdate = {
        getFacebookApi().feedOperations().updateStatus(params.message)
        redirect(action: "feef")
    }

    def friends = {
        ["friends": getFacebookApi().friendOperations().friendProfiles]
    }

    def albums = {
        ["albums": getFacebookApi().mediaOperations().albums]
    }

    def showAlbum = {
        ["album": getFacebookApi().mediaOperations().getAlbum(params.id),
                "photos": getFacebookApi().mediaOperations().getPhotos(params.id)]
    }

    def index = {
        ["profile": getFacebookApi().userOperations().userProfile]
    }


}
