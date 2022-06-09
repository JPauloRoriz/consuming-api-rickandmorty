package com.joaop.rickandmorty.viewmodel.listCharacter.model

import com.joaop.rickandmorty.domain.entity.Character

sealed class CharactersEvent {
    data class GoToDetail(val character : Character) : CharactersEvent()
    data class ShowAlertMessage(val message: String) : CharactersEvent()
}