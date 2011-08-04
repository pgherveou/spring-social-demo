class UrlMappings {

    static mappings = {

        "/test/$testVal"(controller: "testMapping") {
            action = [GET: "fromGet", POST: "fromPost"]
        }

        name connect: "/connect/$providerId"(controller: "connect") {
            action = [GET: "oauthCallback", DELETE: "removeConnection", POST: "connect"]
        }

        name signin: "/signin/$providerId"(controller: "providerSignIn") {
            action = [GET: "oauthCallback", POST: "signIn"]
        }

        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }


        "/"(view: "/index")
        "500"(view: '/error')
    }
}
