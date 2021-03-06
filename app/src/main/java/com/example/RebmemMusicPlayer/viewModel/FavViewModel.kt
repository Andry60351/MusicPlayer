package com.example.RebmemMusicPlayer.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.RebmemMusicPlayer.db.entities.SongModel
import com.example.RebmemMusicPlayer.repositories.FavRepository

class FavViewModel : BaseViewModel() {
    override var dataset: MutableLiveData<ArrayList<Any>> = MutableLiveData()
    var favRepository: FavRepository

    init {
        dataset.value = ArrayList()
        favRepository = FavRepository()
    }

    override fun fillRecyclerView() {
        updateDataset()
    }

    override fun updateDataset() {
        dataset.value = favRepository.getFavSongs() as ArrayList<Any>
    }

    fun getDataset(): ArrayList<SongModel>
    {
        return dataset.value as ArrayList<SongModel>
    }

}