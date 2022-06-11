package com.senac.turismo.repository

import android.app.Application
import com.senac.turismo.dao.AppDatabaseConnection
import com.senac.turismo.dao.PessoaDao
import com.senac.turismo.dao.ViagemDao
import com.senac.turismo.model.Pessoa
import com.senac.turismo.model.Viagem

class ViagemRepository(app: Application) {

    private val viagemDao: ViagemDao

    init {
        viagemDao = AppDatabaseConnection
            .getDB(app).viagemDao()
    }

    suspend fun save(viagem: Viagem) {
        if (viagem.id == 0) {
            viagemDao.insert(viagem)
        }
        else {
            viagemDao.update(viagem)
        }
    }

    suspend fun findAll(): List<Viagem> = viagemDao.findAll()

    suspend fun findById(id: Int) = viagemDao.findById(id)

    suspend fun delete(viagem: Viagem) = viagemDao.delete(viagem)



}