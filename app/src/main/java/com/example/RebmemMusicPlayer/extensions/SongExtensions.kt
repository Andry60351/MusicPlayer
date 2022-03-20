package com.example.RebmemMusicPlayer.extensions

import com.example.RebmemMusicPlayer.db.entities.SongModel
import com.example.RebmemMusicPlayer.repositories.RoomRepository

fun SongModel.isFavorite(): Boolean
{
    for (favSongs in RoomRepository.cachedFavArray) {
        if (favSongs.id!!.equals(this.id)) {
            return true
        }
    }
    return false
}