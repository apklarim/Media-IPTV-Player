package com.media.iptvplayer.model

data class Playlist(

    val id: Long = System.currentTimeMillis(),

    var name: String,

    var type: String,

    var url: String = "",

    var username: String = "",

    var password: String = "",

    var server: String = "",

    var expireDate: String = ""
)
