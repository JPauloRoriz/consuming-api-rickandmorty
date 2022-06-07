package com.joaop.rickandmorty

data class CharacterResponse(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: CharacterLocationResponse
)

data class CharacterLocationResponse(
    val name: String
)