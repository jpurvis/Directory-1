package com.example.directory.di

import com.example.colleagues.data.datasource.IPeopleDataSource
import com.example.directory.framework.remote.PeopleDataSourceImpl
import com.example.directory.framework.remote.RoomsDataSourceImpl
import com.example.rooms.data.datasource.IRoomsDataSource
import io.reactivex.Single
import org.koin.dsl.module

val dataSourceModule = module {
    single<IPeopleDataSource> {PeopleDataSourceImpl(get())}
    single<IRoomsDataSource> { RoomsDataSourceImpl(get())}
}