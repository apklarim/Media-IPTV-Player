package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // CANLI TV

        findViewById<Button>(R.id.btnLiveTv)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        ChannelListActivity::class.java
                    ).putExtra(
                        "CATEGORY",
                        "LIVE"
                    )
                )
            }

        // FILMLER

        findViewById<Button>(R.id.btnMovies)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        ChannelListActivity::class.java
                    ).putExtra(
                        "CATEGORY",
                        "MOVIES"
                    )
                )
            }

        // DIZILER

        findViewById<Button>(R.id.btnSeries)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        ChannelListActivity::class.java
                    ).putExtra(
                        "CATEGORY",
                        "SERIES"
                    )
                )
            }

        // KAYITLI LISTELER

        findViewById<Button>(R.id.btnPlaylists)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        PlaylistListActivity::class.java
                    )
                )
            }

        // AYARLAR

        findViewById<Button>(R.id.btnSettings)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        SettingsActivity::class.java
                    )
                )
            }
    }
}
