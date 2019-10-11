package dev.ferrazpedro.livebooks.ui

import android.content.Context
import dev.ferrazpedro.livebooks.api.model.RespostaLivro
import io.reactivex.Observable

interface MVP {

    interface View {
        fun mostrarErro (erro : String)
        fun mostrarLista (livros : MutableList<RespostaLivro>)
    }

    interface Presenter {
        fun carregarLivros(context: Context)
    }

    interface Model {
        fun getTodosLivros() : Observable<MutableList<RespostaLivro>>
        fun pegarTodosOsLivros()
    }

    interface btnClickListener {
        fun onBtnClick(position: Int)
    }
}