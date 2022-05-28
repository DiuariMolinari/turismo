package com.senac.turismo.repository

import android.app.Application
import com.senac.turismo.dao.AppDatabaseConnection
import com.senac.turismo.dao.PessoaDao
import com.senac.turismo.model.Pessoa

class PessoaRepository(app: Application) {

    private val pessoaDao: PessoaDao

    init {
        pessoaDao = AppDatabaseConnection
            .getDB(app).pessoaDao()
    }

    suspend fun save(pessoa: Pessoa) {
        if (pessoa.id == 0) {
            pessoaDao.insert(pessoa)
        }
        else {
            pessoaDao.update(pessoa)
        }
    }

    suspend fun findAll(): List<Pessoa> = pessoaDao.findAll()

    suspend fun findById(id: Int) = pessoaDao.findById(id)

    suspend fun delete(pessoa: Pessoa) = pessoaDao.delete(pessoa)



}