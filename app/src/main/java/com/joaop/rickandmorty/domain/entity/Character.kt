package com.joaop.rickandmorty.domain.entity

data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: CharacterLocation
)

data class CharacterLocation(
    val name: String
)