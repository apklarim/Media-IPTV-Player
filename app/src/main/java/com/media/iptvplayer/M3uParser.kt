package com.media.iptvplayer

import com.media.iptvplayer.model.Channel

object M3uParser {

    fun parse(content: String): List<Channel> {

        val channels = mutableListOf<Channel>()

        val lines = content
            .replace("\r\n", "\n")
            .replace("\r", "\n")
            .split("\n")

        var currentName = ""
        var currentGroup = ""

        for (lineRaw in lines) {

            val line = lineRaw.trim()

            if (line.startsWith("#EXTINF")) {

                currentName =
                    line.substringAfterLast(",").trim()

                currentGroup =
                    Regex("""group-title="([^"]*)"""")
                        .find(line)
                        ?.groupValues
                        ?.get(1)
                        ?: ""
            }

            else if (
                line.startsWith("http://") ||
                line.startsWith("https://")
            ) {

                val group =
                    currentGroup.lowercase()

                val category = when {

                    group.contains("movie") -> "MOVIES"
                    group.contains("film") -> "MOVIES"
                    group.contains("vod") -> "MOVIES"
                    group.contains("cinema") -> "MOVIES"
                    group.contains("netflix") -> "MOVIES"

                    group.contains("series") -> "SERIES"
                    group.contains("dizi") -> "SERIES"
                    group.contains("show") -> "SERIES"

                    else -> "LIVE"
                }

                channels.add(
                    Channel(
                        name = currentName,
                        url = line,
                        group = currentGroup,
                        category = category
                    )
                )
            }
        }

        return channels
    }
                          }
