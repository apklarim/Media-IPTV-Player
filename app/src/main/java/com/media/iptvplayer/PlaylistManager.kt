package com.media.iptvplayer

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.media.iptvplayer.model.Playlist

object PlaylistManager {

    private const val PREFS = "playlists"
    private const val KEY = "playlist_list"

    fun getPlaylists(
        context: Context
    ): MutableList<Playlist> {

        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        val json =
            prefs.getString(
                KEY,
                "[]"
            )

        val type =
            object : TypeToken<MutableList<Playlist>>() {}.type

        return Gson().fromJson(
            json,
            type
        ) ?: mutableListOf()
    }

    fun savePlaylists(
        context: Context,
        playlists: MutableList<Playlist>
    ) {

        val prefs = context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )

        prefs.edit()
            .putString(
                KEY,
                Gson().toJson(playlists)
            )
            .apply()
    }

    fun addPlaylist(
        context: Context,
        playlist: Playlist
    ) {

        val list = getPlaylists(context)

        list.add(playlist)

        savePlaylists(
            context,
            list
        )
    }

    fun deletePlaylist(
        context: Context,
        id: Long
    ) {

        val list = getPlaylists(context)

        list.removeAll {
            it.id == id
        }

        savePlaylists(
            context,
            list
        )
    }
}
