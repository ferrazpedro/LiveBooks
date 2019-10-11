package dev.ferrazpedro.livebooks.api.model

data class RespostaLivro(
    val title: String,
    val price: Float,
    val writer: String,
    val thumbnailHd: String
)