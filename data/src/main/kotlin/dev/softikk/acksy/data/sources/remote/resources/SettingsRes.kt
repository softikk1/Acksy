package dev.softikk.acksy.data.sources.remote.resources

import io.ktor.resources.*

@Resource("settings")
class SettingsRes(val parent: Root = Root()) {
    @Resource("replace")
    class RefreshRes(val parent: SettingsRes = SettingsRes())
}