package com.media.iptvplayer

import android.content.Context

object ChannelPreferences {

    private const val PREFS = "channel_prefs"

    fun saveLastGroup(
        context: Context,
        category: String,
        group: String
    ) {

        context.getSharedPreferences(
            PREFS,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(
                "group_$category",
                group
            )
            .apply()
    }

    fun getLastGroup(
        context: Context,
        category: String
    ): String {

        return context
            .getSharedPreferences(
                PREFS,
                Context.MODE_PRIVATE
            )
            .getString(
                "group_$category",
                "Tümü"
            ) ?: "Tümü"
    }
}
