package com.joaop.rickandmorty.data

import com.joaop.rickandmorty.data.entities.response.BaseResponsePagination
import com.joaop.rickandmorty.data.entities.response.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("api/character/")
    fun getAllCharacters(
        @Query("page") page : Int = 1,
        @Query("name") name : String = ""
    ): Call<BaseResponsePagination<List<CharacterResponse>>>


}