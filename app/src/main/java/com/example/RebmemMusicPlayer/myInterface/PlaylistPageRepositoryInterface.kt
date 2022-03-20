package com.example.RebmemMusicPlayer.myInterface

import com.example.RebmemMusicPlayer.db.entities.SongModel

interface PlaylistPageRepositoryInterface
{
    fun getSongsIdFromDatabase(): String
    fun songsIdToSongModelConverter(songId: String): SongModel?
    fun getSongs(): ArrayList<SongModel>
}