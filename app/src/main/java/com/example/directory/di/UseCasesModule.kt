package com.example.directory.di


import com.example.colleagues.interactors.GetColleagues
import com.example.common.executor.JobExecutor
import com.example.directory.framework.executor.UiThread
import com.example.rooms.interactors.GetRooms
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetColleagues(get(), JobExecutor(), UiThread()) }
    factory { GetRooms(get(), JobExecutor(), UiThread()) }
}