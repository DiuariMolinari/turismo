package com.senac.turismo.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.senac.turismo.model.Categoria
import com.senac.turismo.model.Despesa
import com.senac.turismo.model.Pessoa
import com.senac.turismo.model.Viagem
import com.senac.turismo.utils.LocalDateConverter

@Database(entities = arrayOf(Pessoa::class, Viagem::class, Categoria::class, Despesa::class), version = 1 )
@TypeConverters(LocalDateConverter :: class)
abstract class AppDatabaseConnection: RoomDatabase() {

    abstract fun pessoaDao(): PessoaDao
    abstract fun viagemDao(): ViagemDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun despesaDao(): DespesaDao
    //abstract fun outroDao(): OutroDao

    // Desing Pattern - Singleton
    companion object {
        var connection: AppDatabaseConnection? = null

        fun getDB(context: Context): AppDatabaseConnection {
            val temp = connection
            if (temp != null) {
                return temp
            }
            else {
                // conectar o banco
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabaseConnection::class.java,
                    "db3"
                ).build()
                connection = instance
                return instance
            }
        }

    }
}