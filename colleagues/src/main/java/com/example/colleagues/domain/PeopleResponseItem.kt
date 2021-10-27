package com.example.colleagues.domain

data class PeopleResponseItem(
    val avatar: String,
    val createdAt: String,
    val email: String,
    val favouriteColor: String,
    val firstName: String,
    val id: String,
    val jobTitle: String,
    val lastName: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String
)