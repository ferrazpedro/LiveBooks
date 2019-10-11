package dev.ferrazpedro.livebooks.utils

import android.content.Context
import android.content.SharedPreferences
import dev.ferrazpedro.livebooks.BuildConfig

class AppSharedPreferences {

    companion object{
        private val ESTADO = "${BuildConfig.APPLICATION_ID}.estado"
        private val SALDO = "${BuildConfig.APPLICATION_ID}.saldo"

        fun darEstado(context: Context, valor: Boolean) {
            val prefs : SharedPreferences = context.getSharedPreferences(ESTADO, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = prefs.edit()
            editor.putBoolean(ESTADO, valor)
            editor.apply()
        }

        fun pegarEstado(context: Context) : Boolean {
            val prefs : SharedPreferences = context.getSharedPreferences(ESTADO, Context.MODE_PRIVATE)
            return prefs.getBoolean(ESTADO, false)
        }

        fun darSaldo(context: Context, saldo: Float) {
            val prefs : SharedPreferences = context.getSharedPreferences(SALDO, Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = prefs.edit()
            editor.putFloat(SALDO, saldo)
            editor.apply()
        }

        fun pegarSaldo(context: Context) : Float {
            val prefs : SharedPreferences = context.getSharedPreferences(SALDO, Context.MODE_PRIVATE)
            return prefs.getFloat(SALDO, 0f)
        }

    }
}