package com.example.room_db_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "Room_db_table")         // memo 테이블 생성
data class Entity_Main (         // data class로 생성
    //@PrimaryKey(autoGenerate = true)         // 기본키(자동 증가)
    //var id : Int = 0,
    var name : String = ""){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
    // 기본키 역할을 하는 컬럼
}
