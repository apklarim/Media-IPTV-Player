package com.media.iptvplayer

object ParentalControlManager {

    private val adultKeywords = listOf(
        "adult",
        "xxx",
        "18+",
        "porn",
        "sex",
        "erotik"
    )

    fun isProtectedGroup(
        group: String
    ): Boolean {

        return adultKeywords.any {

            group.lowercase()
                .contains(it)
        }
    }

    fun checkPin(
        pin: String
    ): Boolean {

        return pin == "1234"
    }
}
