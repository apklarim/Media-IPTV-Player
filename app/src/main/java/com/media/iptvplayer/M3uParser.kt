package com.media.iptvplayer

import com.media.iptvplayer.model.Channel

object M3uParser {

    fun parse(content: String): List<Channel> {

        val channels = mutableListOf<Channel>()

        val lines = content.lines()

        var currentName = ""

        for (i in lines.indices) {

            val line = lines[i].trim()

            if (line.startsWith("#EXTINF")) {

                currentName =
                    line.substringAfter(",")

            } else if (
                line.startsWith("http")
                || line.startsWith("https")
            ) {

                channels.add(

                    Channel(
                        name = currentName,
                        url = line
                    )
                )
            }
        }

        return channels
    }
}
