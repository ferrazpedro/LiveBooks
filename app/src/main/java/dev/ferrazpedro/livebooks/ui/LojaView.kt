package dev.ferrazpedro.livebooks.ui

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ferrazpedro.livebooks.R
import dev.ferrazpedro.livebooks.api.LivrosComprados
import dev.ferrazpedro.livebooks.api.model.RespostaLivro
import dev.ferrazpedro.livebooks.utils.AppSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class LojaView : AppCompatActivity(), MVP.View, MVP.btnClickListener {

    var pt_br : Locale = Locale("pt", "BR")

    private val presenter: MVP.Presenter by lazy<MVP.Presenter> {
        LojaPresenter(this)
    }

    private lateinit var adapter: LojaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loja)

        this.presenter.carregarLivros(applicationContext)
        var saldo = AppSharedPreferences.pegarSaldo(applicationContext)
        saldoMenu.text = NumberFormat.getCurrencyInstance(pt_br).format(saldo)

    }

    override fun mostrarLista(livros: MutableList<RespostaLivro>) {
        recyclerViewMain.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        this.adapter = LojaAdapter(livros, this)
        recyclerViewMain.adapter = adapter
    }

    override fun mostrarErro(erro: String) {
        Toast.makeText(LojaView(), "Aconteceu algum problema!", Toast.LENGTH_LONG).show()
    }

    override fun onBtnClick(position: Int) {
        var livro = adapter.homeFeed.get(position)
        var valorLivro = livro.price
        var saldo = AppSharedPreferences.pegarSaldo(applicationContext)

        if (valorLivro <= saldo && saldo != null) {
            var novoSaldo = (saldo - valorLivro)
            AppSharedPreferences.darSaldo(applicationContext, novoSaldo)
            AppSharedPreferences.pegarSaldo(applicationContext)
            saldoMenu.text = NumberFormat.getCurrencyInstance(pt_br).format(saldo)
            adapter.homeFeed.remove(livro)
            adapter.notifyDataSetChanged()
            LivrosComprados.livrosComprados.add(livro)
        }
        else {
            alertDialog()
        }

        this.finish()
    }
    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sem Saldo!")
        builder.setMessage("Infelizmente você não possui saldo para realizar essa compra.")
        builder.setPositiveButton("OK") {dialog: DialogInterface, which: Int ->}
        builder.show()
    }
}