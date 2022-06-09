package com.joaop.rickandmorty.data.callback

import com.joaop.rickandmorty.data.exceptions.ErrorCallException
import com.joaop.rickandmorty.data.exceptions.ErrorException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <TResponse, T> Call<TResponse>.makeCall(
    listener: OnResultListener<T>,
    mapper: (TResponse) -> T,
) {
    enqueue(object : Callback<TResponse> {
        override fun onResponse(
            call: Call<TResponse>,
            response: Response<TResponse>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    listener.onSuccess(mapper.invoke(it))
                }
            } else {
                listener.onError(ErrorException(response.code(), response.message()))
            }
        }

        override fun onFailure(
            call: Call<TResponse>,
            t: Throwable
        ) {
            listener.onError(ErrorCallException(t.message))
        }
    })
}