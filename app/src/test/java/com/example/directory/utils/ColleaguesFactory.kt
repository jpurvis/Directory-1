package com.example.directory.utils

import com.example.colleagues.domain.PeopleResponseItem

class ColleaguesFactory {

    companion object Factory {
      fun makeColleaguesResponse(): List<PeopleResponseItem> {
          return peopleResponseItem()
      }

        private fun peopleResponseItem(): List<PeopleResponseItem> {
           val item1 = PeopleResponseItem("https://randomuser.me/api/portraits/women/13.jpg", "2020-12-14T11:24:20.999Z",
               "Izaiah.Little@hotmail.com", "#122a33", "Maybell",  "1", "Customer Markets Architect",
               "Durgan", 35.6897, 139.6922, "(927) 840-0095 x2527")

            val item2 = PeopleResponseItem(
                "https://randomuser.me/api/portraits/women/4.jpg", "2020-12-13T16:29:22.176Z", "Candida.Bode4@yahoo.com",
                "#7b4254", "Mylene", "2", "National Creative Planner", "Brekke",  -6.2146, 106.8451,
                "1-131-406-0959"
            )
            return listOf(item1, item2)
        }

        fun makeResponseError() : Throwable {
            return Throwable()
        }
    }
}