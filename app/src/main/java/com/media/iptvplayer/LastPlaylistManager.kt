package com.media.iptvplayer

import android.content.Context

object LastPlaylistManager {

    private const val PREFS = "last_playlist_prefs"
    private const val KEY_LAST_PLAYLIST_ID = "last_playlist_id"

    fun saveLastPlaylist(
        context: Context,
        playlistId: String
    ) {

        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(
                KEY_LAST_PLAYLIST_ID,
                playlistId
            )
            .apply()
    }

    fun getLastPlaylistId(
        context: Context
    ): String? {

        return context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
            .getString(
                KEY_LAST_PLAYLIST_ID,
                null
            )
    }
}
