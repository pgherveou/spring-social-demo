package social

import org.springframework.dao.DataIntegrityViolationException

class UserConnectionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userConnectionInstanceList: UserConnection.list(params), userConnectionInstanceTotal: UserConnection.count()]
    }

    def delete() {
        def userConnectionInstance = UserConnection.get(params.id)
        if (!userConnectionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'userConnection.label', default: 'UserConnection'), params.id])
            redirect(action: "list")
            return
        }

        try {
            userConnectionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'userConnection.label', default: 'UserConnection'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userConnection.label', default: 'UserConnection'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
