package br.com.rrodovalho.data.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.rrodovalho.data.repository.local.db.dao.ComicsDetailDao
import br.com.rrodovalho.data.repository.local.db.entities.ComicsDetailEntity

@Database(entities = [ComicsDetailEntity::class], version = 1, exportSchema = true)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun comicsDetailDao(): ComicsDetailDao
}

