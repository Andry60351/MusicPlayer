package com.example.RebmemMusicPlayer.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.RebmemMusicPlayer.R
import com.example.RebmemMusicPlayer.helper.Coordinator
import com.example.RebmemMusicPlayer.db.entities.SongModel
import com.example.RebmemMusicPlayer.utlis.ImageUtils
import com.example.RebmemMusicPlayer.views.Fragments.PlaylistPageFragment
import com.example.RebmemMusicPlayer.views.activities.MainActivity
import kotlinx.android.synthetic.main.playlist_song_rv_item.view.*

class PlaylistPageAdapater(var dataset: ArrayList<SongModel>, val context: Activity): RVBaseAdapter() {

    var position = 0
    lateinit var dataSend: OnDataSend
    lateinit var playlist_name : String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView: View =
            LayoutInflater.from(context).inflate(R.layout.playlist_song_rv_item, parent, false)
        return RecyclerViewViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val song: SongModel = dataset[position]

        this.position = position
        val viewHolder = holder as PlaylistPageAdapater.RecyclerViewViewHolder
        viewHolder.title.text = song.title
        viewHolder.artist.text = song.artist
        song.image?.let {
            ImageUtils.loadImageToImageView(
                context = context,
                imageView = viewHolder.imageView,
                image = it
            )
        }


        viewHolder.recyclerItem.setOnClickListener {
            updatePosition(newIndex = viewHolder.adapterPosition)
            Coordinator.SourceOfSelectedSong = playlist_name
            Coordinator.currentDataSource = dataset

            MainActivity.activity.updateVisibility()

            Coordinator.playSelectedSong(dataset[position])

        }

        viewHolder.menuBtn.setOnClickListener {
            val popUpMenu = PopupMenu(context, it)
            popUpMenu.inflate(R.menu.songs_in_playlist_popup_menu)

            popUpMenu.setOnMenuItemClickListener {
                val id = dataset[position].id
                return@setOnMenuItemClickListener handleMenuButtonClickListener(
                    it.itemId,
                    id.toString()
                )
            }
            popUpMenu.show()
        }

    }

    private fun handleMenuButtonClickListener(itemId: Int, songId: String): Boolean {
        when (itemId) {
            R.id.delete_song_from_playlist_menu_item -> {
                PlaylistPageFragment.viewModel?.playlistPageRepository?.removeSongFromPlaylist(songId)
//                dataSend.onSend(context, songId)
            }
            else -> return false
        }
        return true
    }

    fun updatePosition(newIndex: Int) {
        position = newIndex
    }


    override fun getCurrentPosition(): Int {
        return position
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    interface OnDataSend {
        fun onSend(context: Activity, id: String)
    }

    fun OnDataSend(dataSend: OnDataSend) {
        this.dataSend = dataSend
    }

    open inner class RecyclerViewViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.playlist_song_title
        val artist: TextView = itemView.playlist_song_artist
        val menuBtn: ImageView = itemView.playlist_music_menu_btn
        val imageView: ImageView = itemView.playlist_music_iv
        val recyclerItem: ConstraintLayout = itemView.playlist_song_container
    }
}