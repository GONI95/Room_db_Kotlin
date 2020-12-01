package com.example.room_db_kotlin

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity_Main::class, Entity_Sub::class], version = 1)   // 작업할 테이블 지정
abstract class AppDatabases : RoomDatabase() {
    abstract fun m_Dao() : M_Dao  // MyDAO(실질적 작업)을 반환하는 추상화 메서드
}
/*
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""DROP TABLE memo_sub""")
        database.execSQL("""CREATE TABLE discription_table (sub_id INTEGER, memo_sub TEXT, CONSTRAINT FK_TOPIC FOREIGN KEY(sub_id) REFERENCES memo_table(id))""")
    }
}
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""DROP TABLE discription_table""")
        database.execSQL("""CREATE TABLE information_table (sub_id INTEGER, sub_memo TEXT, CONSTRAINT FK_TOPIC FOREIGN KEY(sub_id) REFERENCES memo_table(id))""")
    }
}
 */