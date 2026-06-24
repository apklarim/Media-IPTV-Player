package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnLiveTv: Button
    private lateinit var btnMovies: Button
    private lateinit var btnSeries: Button
    private lateinit var btnPlaylists: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnLiveTv = findViewById(R.id.btnLiveTv)
        btnMovies = findViewById(R.id.btnMovies)
        btnSeries = findViewById(R.id.btnSeries)
        btnPlaylists = findViewById(R.id.btnPlaylists)

        btnLiveTv.setOnClickListener {

            btnLiveTv.animate()
                .scaleX(1.05f)
                .scaleY(1.05f)
                .setDuration(100)
                .withEndAction {
                    btnLiveTv.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start()
                }
                .start()

            startActivity(
                Intent(
                    this,
                    ChannelListActivity::class.java
                )
            )
        }

        btnMovies.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ChannelListActivity::class.java
                )
            )
        }

        btnSeries.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    ChannelListActivity::class.java
                )
            )
        }

        btnPlaylists.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    PlaylistListActivity::class.java
                )
            )
        }
    }
}
