package dev.ferrazpedro.livebooks.ui

import android.util.Log
import dev.ferrazpedro.livebooks.api.ApiLoja
import dev.ferrazpedro.livebooks.api.model.RespostaLivro
import io.reactivex.Observable
import io.reactivex.Observable.create
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LojaModel (val presenter: MVP.Presenter) : MVP.Model {

    val apiLoja : ApiLoja = ApiLoja()

    override fun getTodosLivros(): Observable<MutableList<RespostaLivro>> {
        return create { subscriber ->

            val callResponse = apiLoja.pegarTodosOsLivros()
            val response = callResponse.execute()

            if (response.isSuccessful) {

                val livros : MutableList<RespostaLivro> = response.body()!!

                subscriber.onNext(livros)
                subscriber.onComplete()
            }
            else subscriber.onError(Throwable(response.message()))
        }
    }

    override fun pegarTodosOsLivros() {

        apiLoja.pegarTodosOsLivros().enqueue(object : Callback<MutableList<RespostaLivro>> {
            override fun onFailure(call: Call<MutableList<RespostaLivro>>, t: Throwable) {
                Log.d("error", "onFailure")
            }

            override fun onResponse(
                call: Call<MutableList<RespostaLivro>>,
                response: Response<MutableList<RespostaLivro>>
            ) {
                (response.body()!!)
            }
        })
    }
}