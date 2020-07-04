package com.anice.myapplication.service

import com.anice.myapplication.model.Goods
import retrofit2.Response
import retrofit2.http.GET

interface AppService {
    @GET("")
    suspend fun getGoods() : Response<Goods>
}