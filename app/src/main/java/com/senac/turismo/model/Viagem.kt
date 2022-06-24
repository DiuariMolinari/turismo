package com.senac.turismo.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.senac.turismo.utils.LocalDateConverter
import java.time.LocalDate
import java.util.*

@Entity(foreignKeys = [
    ForeignKey(entity = Pessoa::class,
        parentColumns = ["id"],
        childColumns = ["pessoaId"],
        onDelete = CASCADE)])
data class Viagem (
    val destino: String?,
    val tipo: TipoViagem?,
    val dataChegada: LocalDate,
    val dataPartida: LocalDate,
    val orcamento: Float,
    val pessoaId: Int,

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

enum class TipoViagem() {
    LAZER,
    NEGOCIO
}