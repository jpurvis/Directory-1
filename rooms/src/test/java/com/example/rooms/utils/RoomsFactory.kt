package com.example.rooms.utils

import com.example.rooms.domain.RoomsResponseItem

class RoomsFactory {

    companion object {
        fun makeRoomsResponse(): List<RoomsResponseItem> {
            return roomsResponseItem()
        }

        private fun roomsResponseItem(): List<RoomsResponseItem> {
            val item1 = RoomsResponseItem(
                "2020-12-14T12:12:29.677Z", "1",
                true, 10, "Megaman"
            )

            val item2 = RoomsResponseItem(
                "2020-12-14T12:48:49.100Z", "2",
                true, 15, "Ms Pacman's Palace"
            )

            return listOf(item1, item2)
        }

        fun makeResponseError(): Throwable {
            return Throwable()
        }
    }


}