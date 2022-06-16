package com.joaop.rickandmorty.viewmodel.listCharacter.model

import com.joaop.rickandmorty.domain.entity.Character

sealed class CharactersState {
    data class ShowLoadingScreen(val show : Boolean ) : CharactersState()
    data class ShowLoadingPagination(val show : Boolean) : CharactersState()
    data class OnSuccessList(val result : List<Character>) : CharactersState()
}