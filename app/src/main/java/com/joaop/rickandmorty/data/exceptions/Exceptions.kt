package com.joaop.rickandmorty.data.exceptions

class ErrorException(
    private val codeError: Int,
    errorMessage: String
) : Exception(errorMessage)


class ErrorCallException(
     errorMessage: String?
) : Exception(errorMessage)


class WithoutInternetException : Exception()
