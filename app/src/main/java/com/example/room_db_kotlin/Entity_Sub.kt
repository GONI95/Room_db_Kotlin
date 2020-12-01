package com.example.room_db_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "info_table",
    foreignKeys = [
        ForeignKey(
            entity = Entity_Main::class,
            parentColumns = ["id"],
            childColumns = ["sub_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Entity_Sub(
    @PrimaryKey
    @ColumnInfo(name = "sub_id")
    var sub_id: Int = 0,
    @ColumnInfo(name = "sub_memo")
    var sub_memo : String = ""
)


