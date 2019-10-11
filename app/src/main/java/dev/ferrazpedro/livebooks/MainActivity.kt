package dev.ferrazpedro.livebooks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ferrazpedro.livebooks.api.LivrosComprados
import dev.ferrazpedro.livebooks.ui.LojaView
import dev.ferrazpedro.livebooks.utils.AppSharedPreferences
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var pt_br : Locale = Locale("pt", "BR")
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checarPrimeiraVez()
        iniBtnLoja()
    }

    fun mostrarColecao(livros : LivrosComprados.Companion) {
        recyclerViewMain.visibility = View.VISIBLE
        colecaoVazia.visibility = View.GONE
        recyclerViewMain.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        this.adapter = MainAdapter(livros)
        recyclerViewMain.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        var primeiroSaldo = AppSharedPreferences.pegarSaldo(applicationContext)
        var saldoInicialReais = NumberFormat.getCurrencyInstance(pt_br).format(primeiroSaldo)
        saldoMenu.text = saldoInicialReais
        mostrarColecao(LivrosComprados.Companion)
    }

    fun checarPrimeiraVez() {

        if (!AppSharedPreferences.pegarEstado(applicationContext)) {
            AppSharedPreferences.darEstado(applicationContext, true)
            AppSharedPreferences.darSaldo(applicationContext, 100.00f)
            recyclerViewMain.visibility = View.GONE

        }else {var saldo = AppSharedPreferences.pegarSaldo((applicationContext))
            saldoMenu.text = NumberFormat.getCurrencyInstance(pt_br).format(saldo)
            mostrarColecao(LivrosComprados.Companion)
        }

    }
    fun iniBtnLoja() {

        botaoComprar.setOnClickListener {

            var moverParaLoja = Intent(applicationContext, LojaView::class.java)
            startActivity(moverParaLoja)
        }
    }
}
