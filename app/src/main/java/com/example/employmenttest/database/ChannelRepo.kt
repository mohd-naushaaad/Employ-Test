package com.example.employmenttest.database

import android.content.Context
import androidx.lifecycle.LiveData

class ChannelRepo(context: Context) {

    private val tag = "QuizRepoTag"
    private var channelDao: ChannelDao

    init {
        val database = AppDatabase(context)
        channelDao = database.channelDao()
    }

    suspend fun getAll(): List<ChannelModel> {
        return channelDao.getAll()
    }

    suspend fun get(name: String): ChannelModel {
        return channelDao.get(name)
    }

    suspend fun insert(channelModel: ChannelModel) {
        channelDao.insert(channelModel)
    }

    suspend fun update(channelModel: ChannelModel) {
        channelDao.update(channelModel)
    }

    suspend fun delete(channelModel: ChannelModel) {
        channelDao.delete(channelModel)
    }
}