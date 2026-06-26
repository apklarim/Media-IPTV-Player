package com.media.iptvplayer

import android.app.Activity
import android.graphics.Color
import android.view.View

object ThemeManager {

    fun applyTheme(
        activity: Activity
    ) {

        val rootView =
            activity.findViewById<View>(
                android.R.id.content
            )

        when (
            ThemePreferences.getTheme(activity)
        ) {

            ThemePreferences.THEME_TURQUOISE -> {

                rootView.setBackgroundColor(
                    Color.parseColor("#071D1E")
                )
            }

            ThemePreferences.THEME_BLUE -> {

                rootView.setBackgroundColor(
                    Color.parseColor("#08192F")
                )
            }

            else -> {

                rootView.setBackgroundColor(
                    Color.parseColor("#050B12")
                )
            }
        }
    }

    fun getAccentColor(
        activity: Activity
    ): Int {

        return when (
            ThemePreferences.getTheme(activity)
        ) {

            ThemePreferences.THEME_TURQUOISE ->
                Color.parseColor("#00E5FF")

            ThemePreferences.THEME_BLUE ->
                Color.parseColor("#448AFF")

            else ->
                Color.parseColor("#00BCD4")
        }
    }

    fun getCardColor(
        activity: Activity
    ): Int {

        return when (
            ThemePreferences.getTheme(activity)
        ) {

            ThemePreferences.THEME_TURQUOISE ->
                Color.parseColor("#10232B")

            ThemePreferences.THEME_BLUE ->
                Color.parseColor("#12203A")

            else ->
                Color.parseColor("#222222")
        }
    }
}
