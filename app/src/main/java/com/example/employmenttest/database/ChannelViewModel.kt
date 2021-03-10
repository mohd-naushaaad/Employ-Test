package com.example.employmenttest.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChannelViewModel(context: Context) : ViewModel() {

    private val tag = "QuizViewModelTag"
    private var channelRepo: ChannelRepo = ChannelRepo(context)

    fun getAll(): List<ChannelModel> = runBlocking {
        channelRepo.getAll()
    }

    fun get(name: String): ChannelModel = runBlocking {
        channelRepo.get(name)
    }

    fun insert(channelModel: ChannelModel): Job = viewModelScope.launch {
        channelRepo.insert(channelModel)
    }

    fun update(channelModel: ChannelModel): Job = viewModelScope.launch {
        channelRepo.update(channelModel)
    }

    fun delete(channelModel: ChannelModel): Job = viewModelScope.launch {
        channelRepo.delete(channelModel)
    }

}