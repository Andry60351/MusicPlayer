package com.example.RebmemMusicPlayer.myInterface

import com.example.RebmemMusicPlayer.db.entities.PlaylistModel

interface PlaylistRepoInterface {

//    val localDatabase: MyDatabase


    fun createPlaylist(name: String)
//    fun addSongsToPlaylist(playlist_name: String, songsId: String): Boolean


    //    TODO(fun renamePlaylist())
    fun getPlaylists(): ArrayList<PlaylistModel>
    fun getIdOfSongsStoredInPlaylist(plylist_id: Long): String
    fun getPlaylistId(name: String): Long

    //    fun updateDatabase(): Boolean
    fun removePlaylist(id: Long): Boolean

    //    fun getPlaylistFromStorage(): ArrayList<PlaylistModel>
//    fun getPlaylistFromDatabase(): ArrayList<PlaylistModel>

    //    utils
    fun getIdByName(name: String): Long
    fun getPlaylistById(id: Long): PlaylistModel?


//    fun updateTable()

}