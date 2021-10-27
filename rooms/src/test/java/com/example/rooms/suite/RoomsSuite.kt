package com.example.rooms.suite

import com.example.rooms.iteractors.GetRoomsTest
import com.example.rooms.repository.RoomsRepositoryTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    RoomsRepositoryTest::class,
    GetRoomsTest::class
)
class RoomsSuite