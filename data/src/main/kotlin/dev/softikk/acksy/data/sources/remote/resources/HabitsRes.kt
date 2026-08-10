package dev.softikk.acksy.data.sources.remote.resources

import io.ktor.resources.*
import kotlinx.serialization.SerialName
import kotlin.uuid.Uuid

@Resource("habits")
class HabitsRes(val parent: Root = Root()) {
    @Resource("create")
    class Create(val parent: HabitsRes = HabitsRes())

    @Resource("{habit_id}")
    class HabitId(val parent: HabitsRes = HabitsRes(), @SerialName("habit_id") val habitId: Uuid) {
        @Resource("confirm")
        class Confirm(val parent: HabitId)

        @Resource("delete")
        class Delete(val parent: HabitId)

        @Resource("{confirm_id}")
        class ConfirmId(val parent: HabitId, @SerialName("confirm_id") val confirmId: Uuid) {
            @Resource("delete")
            class Delete(val parent: ConfirmId)
        }

        @Resource("details")
        class Details(val parent: HabitId)
    }
}