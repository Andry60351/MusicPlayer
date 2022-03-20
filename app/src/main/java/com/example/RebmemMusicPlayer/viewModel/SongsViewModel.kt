package com.example.RebmemMusicPlayer.viewModel

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.RebmemMusicPlayer.db.entities.SongModel
import com.example.RebmemMusicPlayer.repositories.SongsRepository

class SongsViewModel : BaseViewModel() {


    override var dataset: MutableLiveData<ArrayList<Any>> = MutableLiveData()
    lateinit var songsRepository: SongsRepository


    init {
        dataset.value = ArrayList()
    }

    fun setFragmentContext(context: Context) {
        songsRepository = SongsRepository(context)
        fillRecyclerView()
    }


    override fun fillRecyclerView() {

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                updateDataset()
                mainHandler.postDelayed(this, 10000)
            }
        })
    }


    override fun updateDataset() {

        dataset.value = songsRepository.getListOfSongs()!! as ArrayList<Any>
    }


    fun getDataSet(): ArrayList<SongModel> {
        return dataset.value as ArrayList<SongModel>
    }

}