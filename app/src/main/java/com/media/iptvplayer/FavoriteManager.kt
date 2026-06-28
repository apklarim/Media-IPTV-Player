package com.media.iptvplayer

import android.content.Context

object FavoriteManager {

    private const val PREFS = "favorites"
    private const val KEY = "favorite_channels"

    fun isFavorite(
        context: Context,
        channelName: String
    ): Boolean {

        return context
            .getSharedPreferences(
                PREFS,
                Context.MODE_PRIVATE
            )
            .getStringSet(
                KEY,
                emptySet()
            )
            ?.contains(channelName)
            ?: false
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

        val favorites =
            prefs.getStringSet(
                KEY,
                mutableSetOf()
            )!!.toMutableSet()

        if (favorites.contains(channelName)) {
            favorites.remove(channelName)
        } else {
            favorites.add(channelName)
        }

        prefs.edit()
            .putStringSet(
                KEY,
                favorites
            )
            .apply()
    }
}
