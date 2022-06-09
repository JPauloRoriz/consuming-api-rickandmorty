package com.joaop.rickandmorty.data.exceptions

class ErrorException(
    private val codeError: Int,
    private val errorMessage: String
) : Exception(errorMessage)


class ErrorCallException(
    private val errorMessage: String?
) : Exception(errorMessage)