package com.example.yandex_map_test

import com.yandex.mapkit.geometry.Point
import kotlin.random.Random

data class Luke(
    val id : Long = Random.nextLong(),
    val point: Point,
    val status: LukeStatus
)

enum class LukeStatus{
    OPENED, CLOSED, UNKNOWN
}
