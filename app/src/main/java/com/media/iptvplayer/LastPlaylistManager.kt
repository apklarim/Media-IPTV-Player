package com.media.iptvplayer

import android.content.Context

object LastPlaylistManager {

    private const val PREFS =
        "last_playlist_prefs"

    private const val KEY_LAST_PLAYLIST_ID =
        "last_playlist_id"

    fun saveLastPlaylist(
        context: Context,
        playlistId: Long
    ) {

        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
            .edit()
            .putLong(
                KEY_LAST_PLAYLIST_ID,
                playlistId
            )
            .apply()
    }

    fun getLastPlaylistId(
        context: Context
    ): Long {

        return context
            .getSharedPreferences(
                PREFS,
                Context.MODE_PRIVATE
            )
            .getLong(
                KEY_LAST_PLAYLIST_ID,
                -1L
            )
    }

    fun clearLastPlaylist(
        context: Context
    ) {

        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
            .edit()
            .remove(
                KEY_LAST_PLAYLIST_ID
            )
            .apply()
    }
}
