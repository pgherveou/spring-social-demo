package social

class PostAfterConnectInterceptor {

    def actionName

    public void preConnect(def params, def session) {
        if (params[actionName]) {
            session[actionName] = params[actionName]
        }
    }

    public void postConnect(def connection, def session) {
        if (session[actionName]) {
            session.removeAttribute(actionName)
            connection.updateStatus("I've connected with the Grail Social Showcase!")
        }
    }
}
