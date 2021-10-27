package com.example.directory.di

import com.example.colleagues.data.repository.PeopleRepository
import com.example.colleagues.domain.repository.IPeopleRepository
import com.example.directory.framework.remote.PeopleDataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<IPeopleRepository> { PeopleRepository(get()) }
}