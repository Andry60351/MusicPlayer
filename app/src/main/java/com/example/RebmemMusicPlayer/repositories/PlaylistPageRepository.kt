package com.example.RebmemMusicPlayer.repositories

import com.example.RebmemMusicPlayer.db.entities.SongModel
import com.example.RebmemMusicPlayer.myInterface.PlaylistPageRepositoryInterface
import com.example.RebmemMusicPlayer.utlis.DatabaseConverterUtils
import com.example.RebmemMusicPlayer.views.Fragments.LibraryFragment
import com.example.RebmemMusicPlayer.views.Fragments.PlaylistFragment
import kotlinx.coroutines.runBlocking

class PlaylistPageRepository(private val playlistId: Long) : PlaylistPageRepositoryInterface {

    override fun getSongsIdFromDatabase(): String {
        var songsOfPlaylist: String = ""
        runBlocking {
            songsOfPlaylist = RoomRepository.localDatabase.playlistDao().getSongsOfPlaylist(playlistId)
        }
        return songsOfPlaylist
    }

    override fun songsIdToSongModelConverter(songId: String): SongModel? {
        val allSongsInStorage = LibraryFragment.viewModel.getDataSet()

        for (song in allSongsInStorage) {
            if (song.id == songId.toLong()) {
                return song
            }
        }
        return null
    }

    override fun getSongs(): ArrayList<SongModel> {

        var songs: ArrayList<SongModel> = arrayListOf()

        val songsIdInString = getSongsIdFromDatabase()
        if (songsIdInString != null) {
            val songsIdInArraylist = convertStringToArraylist(songsIdInString)

            for (songId in songsIdInArraylist) {
                val realSong = songsIdToSongModelConverter(songId)
                if (realSong != null)
                    songs.add(realSong)
            }
        }

        return songs
    }

    fun convertStringToArraylist(songs: String): ArrayList<String> {
        return DatabaseConverterUtils.stringToArraylist(songs)
    }

    fun removeSongFromPlaylist(songId: String) {

        PlaylistFragment.viewModel?.playlistRepository?.removeSongFromPlaylist(playlistId, songId)

    }

}
