package com.joaop.rickandmorty.data.repository

import com.joaop.rickandmorty.data.CharacterApi
import com.joaop.rickandmorty.data.callback.OnResultListener
import com.joaop.rickandmorty.data.callback.makeCall
import com.joaop.rickandmorty.data.entities.mapper.toListCharcter
import com.joaop.rickandmorty.data.entities.response.BaseResponse
import com.joaop.rickandmorty.domain.entity.Character

class CharacterRepository(
    private val characterApi: CharacterApi
) {

    fun getCharacters(page: Int, listener: OnResultListener<BaseResponse<List<Character>>?>) {
        characterApi.getAllCharacters(page).makeCall(listener) {
            BaseResponse(it.info, it.results.toListCharcter())
        }
    }
}