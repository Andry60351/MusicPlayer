package com.example.RebmemMusicPlayer.myInterface

import com.example.RebmemMusicPlayer.db.entities.PlaylistModel

interface PassDataForSelectPlaylists {
    fun passDataToInvokingFragment(playlists : ArrayList<PlaylistModel>)
}