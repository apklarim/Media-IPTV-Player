package com.media.iptvplayer

import com.media.iptvplayer.model.Channel

object M3uParser {

    fun parse(content: String): List<Channel> {

        val channels = mutableListOf<Channel>()

        val lines = content.split("\n")

        var currentName = ""
        var currentGroup = ""
        var currentLogo = ""

        for (rawLine in lines) {

            val line = rawLine.trim()

            if (line.startsWith("#EXTINF")) {

                currentName =
                    line.substringAfterLast(",")

                currentGroup =
                    Regex("""group-title="([^"]*)"""")
                        .find(line)
                        ?.groupValues
                        ?.get(1)
                        ?: ""

                currentLogo =
                    Regex("""tvg-logo="([^"]*)"""")
                        .find(line)
                        ?.groupValues
                        ?.get(1)
                        ?: ""
            }

            else if (
                line.startsWith("http://")
                || line.startsWith("https://")
            ) {

                channels.add(
                    Channel(
                        name =
                        if (currentName.isBlank())
                            "İsimsiz Kanal"
                        else
                            currentName,

                        url = line,
                        logo = currentLogo,
                        group = currentGroup
                    )
                )
            }
        }

        return channels
    }
                          }
