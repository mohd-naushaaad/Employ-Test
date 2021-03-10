package com.example.employmenttest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channel")
data class ChannelModel(

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "status")
    var status: Boolean

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}