package dev.ferrazpedro.livebooks.ui

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LojaPresenter (val view: MVP.View) : MVP.Presenter {

    private var disposables = CompositeDisposable()

    private val model: MVP.Model by lazy <MVP.Model> {
        LojaModel(this)
    }

    override fun carregarLivros(context: Context) {

        disposables.add(model.getTodosLivros()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result -> view.mostrarLista(result)}, {throwable -> view.mostrarErro(throwable?.message?: "")}))
    }
}