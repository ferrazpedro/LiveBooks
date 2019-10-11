package dev.ferrazpedro.livebooks.api

import dev.ferrazpedro.livebooks.api.model.RespostaLivro
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiLoja {

    private val api: ApiLojaInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiLojaInterface::class.java)
    }

    fun pegarTodosOsLivros(): Call<MutableList<RespostaLivro>> {
        return api.pegarTodosOsLivros()
    }
}