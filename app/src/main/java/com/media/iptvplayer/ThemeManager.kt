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
}
