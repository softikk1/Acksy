package dev.softikk.acksy.data.sources.remote.resources

import io.ktor.resources.Resource

@Resource("statistics")
class StatisticsRes(val parent: Root = Root())