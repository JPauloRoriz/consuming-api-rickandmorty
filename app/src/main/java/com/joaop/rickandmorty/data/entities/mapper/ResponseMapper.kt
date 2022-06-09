package com.joaop.rickandmorty.data.entities.mapper

import com.joaop.rickandmorty.data.entities.response.CharacterLocationResponse
import com.joaop.rickandmorty.data.entities.response.CharacterResponse
import com.joaop.rickandmorty.domain.entity.Character
import com.joaop.rickandmorty.domain.entity.CharacterLocation


fun List<CharacterResponse>.toListCharcter(): List<Character> = map {
    it.toCharacter()
}

fun CharacterResponse.toCharacter() = Character(
    id = id,
    name = name,
    species = species,
    gender = gender,
    image = image,
    location = location.toCharacterLocation()
)

fun CharacterLocationResponse.toCharacterLocation() = CharacterLocation(
    name = name
)
