package com.joaop.rickandmorty.data.callback

interface OnResultListener<T> {
    fun onSuccess(result : T)
    fun onError(exception : Exception)
}