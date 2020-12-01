package com.example.room_db_kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room

class M_ViewModel (application: Application) : AndroidViewModel(application) {
    // AndroidViewModel은 application을 받도록 되어있음

    private val db = Room.databaseBuilder(
        application,
        AppDatabases::class.java, "table_db"
    )/*
    .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_2_3)
        .addMigrations(MIGRATION_3_4)
        */
        .build()

    fun getMemo(): LiveData<List<Entity_Main>> {
        return db.m_Dao().getMemo()
    }

    fun getInformation(id: Int): LiveData<List<Entity_Sub>> {
        return db.m_Dao().getInformation(id)
    }

    // suspend가 붙은 함수들은 무조건 코루틴 Scope 안에서만 실행할 수 있음, 메인액티비티에서 insert를 코루틴 밖에서해도 문제가 없기 때문에 이렇게 처리함
    suspend fun insert(entity: Entity_Main) {
        db.m_Dao().insert(entity)
    }
    suspend fun infor(entity: Entity_Sub) {
        db.m_Dao().insert2(entity)
    }
    // 추상화 해주었음

    suspend fun delete(entity: Entity_Main) {
        db.m_Dao().delete(entity)
    }

    suspend fun deleteAll() {
        db.m_Dao().deleteAll()
    }

    suspend fun update(id: Int, name: String) {
        db.m_Dao().update(id, name)
    }
}