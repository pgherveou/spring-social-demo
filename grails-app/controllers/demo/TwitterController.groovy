package demo

import javax.inject.Inject
import javax.inject.Provider
import org.springframework.social.twitter.api.Twitter

class TwitterController {

    def socialApiProviderService

    private Twitter getTwitterApi() {

        def api = socialApiProviderService.twitter()
        if (!api) {
            redirect(controller: "providerConnect", params: ["providerId": "twitter"])
        }
        return api;
    }

    def index = {
        def api = getTwitterApi()
        [profile: api.userOperations().userProfile]
    }

    def timeline = {
        def api = getTwitterApi()
        [timeline: api.timelineOperations().userTimeline]
    }

    def friends = {
        def api = getTwitterApi()
        [profiles: api.friendOperations().friends]
    }

    def followers = {
        def api = getTwitterApi()
        [profiles: api.friendOperations().followers]
    }

    def trends = {
        def api = getTwitterApi()
        [trends: api.searchOperations().currentTrends.trends]
    }
}
