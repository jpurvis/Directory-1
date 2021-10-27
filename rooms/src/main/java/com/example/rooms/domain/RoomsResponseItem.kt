package com.example.rooms.domain

data class RoomsResponseItem(
    val created_at: String,
    val id: String,
    val is_occupied: Boolean,
    val max_occupancy: Int,
    val name: String
)