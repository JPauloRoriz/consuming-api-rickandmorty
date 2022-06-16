package com.joaop.rickandmorty.data.entities.response

data class BaseResponsePagination<T>(
    val info: InfoResponse,
    val results: T
)

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String,
)