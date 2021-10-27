package com.example.directory.di

import com.example.colleagues.data.datasource.IPeopleDataSource
import com.example.directory.framework.remote.PeopleDataSourceImpl
import io.reactivex.Single
import org.koin.dsl.module

val dataSourceModule = module {
    single<IPeopleDataSource> {PeopleDataSourceImpl(get())}
}