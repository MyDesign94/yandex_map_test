package com.example.yandex_map_test.data

import com.example.yandex_map_test.Luke
import com.example.yandex_map_test.LukeStatus
import com.yandex.mapkit.geometry.Point

class MainRepository {



    fun getPoints(): List<Luke> {
    return listOf(
        Luke(
            point = Point(59.940082, 30.312814),
            status = LukeStatus.CLOSED
        ),
        Luke(
            point = Point(59.972810, 30.221456),
            status = LukeStatus.OPENED
        ),
        Luke(
            point = Point(59.924984, 30.362311),
            status = LukeStatus.OPENED
        ),
        Luke(
            point = Point(60.037674, 30.250677),
            status = LukeStatus.CLOSED
        ),
        Luke(
            point = Point(59.996726, 30.203004),
            status = LukeStatus.UNKNOWN
        )
    )
    }


}