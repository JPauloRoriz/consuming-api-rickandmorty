package com.joaop.rickandmorty.usecase

import com.joaop.rickandmorty.data.callback.OnResultListener
import com.joaop.rickandmorty.data.entities.response.BaseResponsePagination
import com.joaop.rickandmorty.domain.entity.Character
import com.joaop.rickandmorty.data.repository.CharacterRepository

class GetAllCharactersUseCase(
    private val repository: CharacterRepository
) {
   operator fun invoke(page : Int, name : String, listener: OnResultListener<BaseResponsePagination<List<Character>>?>) {
        repository.getCharacters(page, name, listener)
    }
}