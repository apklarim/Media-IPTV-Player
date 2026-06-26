package com.media.iptvplayer

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout

class VlcPlayerActivity : AppCompatActivity() {

    private lateinit var libVLC: LibVLC
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var videoLayout: VLCVideoLayout

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_vlc_player
        )

        videoLayout =
            findViewById(
                R.id.vlcVideoLayout
            )

        val url =
            intent.getStringExtra(
                "url"
            ) ?: return

        libVLC =
            LibVLC(
                this,
                arrayListOf(
                    "--network-caching=1000"
                )
            )

        mediaPlayer =
            MediaPlayer(libVLC)

        mediaPlayer.attachViews(
            videoLayout,
            null,
            false,
            false
        )

        val media =
            Media(
                libVLC,
                Uri.parse(url)
            )

        mediaPlayer.media = media
        media.release()

        mediaPlayer.play()
    }

    override fun onDestroy() {

        mediaPlayer.stop()
        mediaPlayer.detachViews()
        mediaPlayer.release()

        libVLC.release()

        super.onDestroy()
    }
}
