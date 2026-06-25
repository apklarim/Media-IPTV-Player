package com.media.iptvplayer

import android.content.Context

object PlayerPreferences {

    private const val PREFS = "player_prefs"
    private const val KEY_LAST_CHANNEL = "last_channel"

    fun saveLastChannel(
        context: Context,
        url: String
    ) {

        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(
                KEY_LAST_CHANNEL,
                url
            )
            .apply()
    }

    fun getLastChannel(
        context: Context
    ): String? {

        return context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
            .getString(
                KEY_LAST_CHANNEL,
                null
            )
    }
}
