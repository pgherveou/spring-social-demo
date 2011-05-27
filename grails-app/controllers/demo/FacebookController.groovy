package demo

import org.springframework.social.facebook.api.FacebookApi
import javax.inject.Provider
import javax.inject.Inject

class FacebookController {

    @Inject Provider<FacebookApi> facebookApiProvider

    def getFacebookApi = {facebookApiProvider.get()}

    def feed = {
        ["feed" : getFacebookApi().feedOperations().feed]
    }

    def postUpdate = {
        getFacebookApi().feedOperations().updateStatus(params.message)
        redirect(action: "feef")
    }

    def friends = {
        ["friends" : getFacebookApi().friendOperations().friendProfiles]
    }

    def albums = {
        ["albums" : getFacebookApi().mediaOperations().albums]
    }

    def showAlbum =  {
        ["album" : getFacebookApi().mediaOperations().getAlbum(params.id),
         "photos": getFacebookApi().mediaOperations().getPhotos(params.id)]
    }

    def index = {
        if (getFacebookApi() == null) {
            redirect(controller: "providerConnect", params: ["providerId" : "facebook"])
        }
        ["profile": getFacebookApi().userOperations().userProfile]
    }


}
