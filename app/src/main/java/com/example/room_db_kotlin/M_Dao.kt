package com.example.room_db_kotlin

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface M_Dao {
    @Query("SELECT * FROM Room_db_table")
    fun getMemo() : LiveData<List<Entity_Main>>

    @Query("SELECT * FROM info_table WHERE sub_id = :id")
    fun getInformation(id: Int) : LiveData<List<Entity_Sub>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)   // Insert 작업 시 기본키 값이 같으면 덮어쓰기 한다
    fun insert(entity: Entity_Main)

    @Insert
    fun insert2(entity: Entity_Sub)

    @Delete
    fun delete(entity: Entity_Main)

    @Query("DELETE FROM Room_db_table")
    fun deleteAll()

    @Query("UPDATE Room_db_table SET name = :name WHERE id = :id ")
    fun update(id : Int, name : String)
}