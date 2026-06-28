package com.media.iptvplayer

import android.content.Context

object AdultPinManager {

    private const val FILE_NAME =
        "adult_pin.json"

    fun getPin(
        context: Context
    ): String {

        return FileStorageManager.readText(
            FILE_NAME,
            "1234"
        )
    }

    fun setPin(
        context: Context,
        pin: String
    ) {

        FileStorageManager.writeText(
            FILE_NAME,
            pin
        )
    }

    fun isAdultChannel(
        group: String,
        name: String
    ): Boolean {

        val text =
            "$group $name".lowercase()

        return text.contains("xxx") ||
                text.contains("adult") ||
                text.contains("18+") ||
                text.contains("18 ") ||
                text.contains("erotik") ||
                text.contains("sex") ||
                text.contains("porno") ||
                text.contains("yetişkin")
    }
}
