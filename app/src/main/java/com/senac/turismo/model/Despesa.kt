package com.senac.turismo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*



@Entity
data class Despesa (
    val data: LocalDate?,//Converter Room. LocalDate to Long. Long to LocalDate.
    val valor: Double?,
    val descricao: String,
    val local: String,
    val categoria: Categoria,
    val viagem: Viagem
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}