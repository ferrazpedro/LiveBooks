package dev.ferrazpedro.livebooks.api

import dev.ferrazpedro.livebooks.api.model.RespostaLivro
import retrofit2.http.GET
import retrofit2.Call

interface ApiLojaInterface {


    @GET("/Felcks/desafio-mobile-lemobs/master/products.json")
    fun pegarTodosOsLivros(): Call<MutableList<RespostaLivro>>
}