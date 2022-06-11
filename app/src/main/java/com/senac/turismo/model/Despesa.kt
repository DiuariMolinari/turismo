package com.senac.turismo.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.senac.turismo.utils.LocalDateConverter
import java.time.LocalDate
import java.util.*

@Entity(foreignKeys = [
    ForeignKey(entity = Categoria::class,
        parentColumns = ["id"],
        childColumns = ["categoriaId"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(entity = Viagem::class,
        parentColumns = ["id"],
        childColumns = ["viagemId"],
        onDelete = ForeignKey.CASCADE
    )])
data class Despesa (
    val data: LocalDate?,//Converter Room. LocalDate to Long. Long to LocalDate.
    val valor: Double,
    val descricao: String,
    val local: String,
    val categoriaId: Int,
    val viagemId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}