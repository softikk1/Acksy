package dev.softikk.acksy.data.sources.remote.resources

import io.ktor.resources.Resource

@Resource("auth")
class AuthRes(val parent: Root = Root()) {
    @Resource("send-code-email")
    class SendCodeEmailRes(val parent: AuthRes = AuthRes())

    @Resource("confirm-code-email")
    class ConfirmCodeEmailRes(val parent: AuthRes = AuthRes())

    @Resource("login")
    class LoginRes(val parent: AuthRes = AuthRes())

    @Resource("register")
    class RegisterRes(val parent: AuthRes = AuthRes())

    @Resource("refresh")
    class RefreshRes(val parent: AuthRes = AuthRes())

    @Resource("logout")
    class LogoutRes(val parent: AuthRes = AuthRes())
}