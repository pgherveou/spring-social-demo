package demo

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class TwitterController {

    def userConnectionService

    def api

    def beforeInterceptor = [action: this.&setApi, except: 'connect']
    private setApi() {
        log.debug "set twitter api"
        api = userConnectionService.twitter()
        if (!api) {
            log.debug "no twitter api redirect to connect"
            redirect action:"connect"
            return  false
        }
    }

    def connect = {}

    def index = {
        [profile: api.userOperations().userProfile]
    }

    def timeline = {
        [timeline: api.timelineOperations().userTimeline]
    }

    def friends = {
        [profiles: api.friendOperations().friends]
    }

    def followers = {
        [profiles: api.friendOperations().followers]
    }

    def trends = {
        [trends: api.searchOperations().currentTrends.trends]
    }
}
