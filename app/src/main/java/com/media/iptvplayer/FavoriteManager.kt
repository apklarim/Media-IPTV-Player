package com.media.iptvplayer

import android.content.Context

object FavoriteManager {

    private const val PREFS = "favorites"

    fun isFavorite(
        context: Context,
        channelName: String
    ): Boolean {

        return context
            .getSharedPreferences(
                PREFS,
                Context.MODE_PRIVATE
            )
            .getBoolean(
                channelName,
                false
            )
    }

    fun toggleFavorite(
        context: Context,
        channelName: String
    ) {

        val prefs =
            context.getSharedPreferences(
                PREFS,
                Context.MODE_PRIVATE
            )

        val current =
            prefs.getBoolean(
                channelName,
                false
            )

        prefs.edit()
            .putBoolean(
                channelName,
                !current
            )
            .apply()
    }
}
