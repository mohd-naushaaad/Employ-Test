package com.example.employmenttest.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channel")
    suspend fun getAll(): MutableList<ChannelModel>

    @Query("SELECT * FROM channel WHERE name = :name ")
    suspend fun get(name: String): ChannelModel

    @Insert
    suspend fun insert(channelModel: ChannelModel)

    @Insert
    suspend fun insert(channelModel: List<ChannelModel>)

    @Update
    suspend fun update(channelModel: ChannelModel)

    @Delete
    suspend fun delete(channelModel: ChannelModel)
}