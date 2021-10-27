package com.example.colleagues.suite

import com.example.colleagues.interactors.GetColleaguesTest
import com.example.colleagues.repository.PeopleRepositoryTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GetColleaguesTest::class,
    PeopleRepositoryTest::class
)
class ColleaguesSuite
