package com.senac.turismo.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.senac.turismo.model.Pessoa

@Database(entities = arrayOf(Pessoa::class), version = 1 )
abstract class AppDatabaseConnection: RoomDatabase() {

    abstract fun pessoaDao(): PessoaDao
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
                    "my-db"
                ).build()
                connection = instance
                return instance
            }
        }

    }
}