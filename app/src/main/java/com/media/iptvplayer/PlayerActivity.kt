package com.media.iptvplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class PlayerActivity : AppCompatActivity() {

    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_player)

        val url = intent.getStringExtra("url") ?: return

        val playerView =
            findViewById<PlayerView>(R.id.playerView)

        player = ExoPlayer.Builder(this).build()

        playerView.player = player

        val mediaItem = MediaItem.fromUri(url)

        player.setMediaItem(mediaItem)

        player.prepare()

        player.play()
    }

    override fun onStop() {
        super.onStop()

        player.release()
    }
}
