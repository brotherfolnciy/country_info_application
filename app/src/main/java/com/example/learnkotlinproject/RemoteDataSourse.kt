package com.example.learnkotlinproject

import retrofit2.Call
import retrofit2.http.GET

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

//https://countriesinfo21.herokuapp.com/name/{name}

interface RestCountriesApi {
    @GET("name/{cityName}")
    suspend fun getCountryByName(@Path("cityName") user: String): List<Country>
}

class RemoteDataSourse {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://countriesinfo21.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var restCountriesApi = retrofit.create(RestCountriesApi::class.java)
}