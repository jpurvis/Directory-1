package com.example.directory.suite

import com.example.directory.ColleaguesViewModelTest
import com.example.directory.RoomsViewModelTest
import com.example.directory.remote.PeopleDataSourceImplTest
import com.example.directory.remote.RoomsDataSourceImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    PeopleDataSourceImplTest::class,
    ColleaguesViewModelTest::class,
    RoomsDataSourceImplTest::class,
    RoomsViewModelTest::class
)
class AllSuite