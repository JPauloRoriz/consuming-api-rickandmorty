package com.joaop.rickandmorty.data.repository

import com.joaop.rickandmorty.data.CharacterApi
import com.joaop.rickandmorty.data.callback.OnResultListener
import com.joaop.rickandmorty.data.callback.makeCall
import com.joaop.rickandmorty.data.entities.mapper.toListCharcter
import com.joaop.rickandmorty.data.entities.response.BaseResponsePagination
import com.joaop.rickandmorty.data.repository.contract.CharacterRepository
import com.joaop.rickandmorty.domain.entity.Character

class CharacterRepositoryImpl(
    private val characterApi: CharacterApi
): CharacterRepository {

    override fun getAllCharacters(
        page : Int,
        name : String?,
        listener: OnResultListener<BaseResponsePagination<List<Character>>?>
    ) {
        characterApi.getAllCharacters(page, name).makeCall(listener) {
            BaseResponsePagination(it.info, it.results.toListCharcter())
        }
    }
}