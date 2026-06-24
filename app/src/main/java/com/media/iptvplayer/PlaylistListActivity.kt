package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PlaylistListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_playlist_list)

        val btnAdd =
            findViewById<Button>(R.id.btnAddPlaylist)

        val listView =
            findViewById<ListView>(R.id.listPlaylists)

        // Yeni liste ekle
        btnAdd.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AddPlaylistActivity::class.java
                )
            )
        }

        // Kayıtlı listeleri yükle
        val playlists =
            PlaylistManager.getPlaylists(this)

        val names =
            playlists.map {
                "${it.name} (${it.type})"
            }

        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            names
        )

        listView.setOnItemClickListener { _, _, position, _ ->

            val playlist = playlists[position]

            when (playlist.type) {

                "M3U_FILE" -> {

                    startActivity(
                        Intent(
                            this,
                            ChannelListActivity::class.java
                        ).putExtra(
                            "playlistName",
                            playlist.name
                        )
                    )
                }

                "M3U" -> {

                    startActivity(
                        Intent(
                            this,
                            M3uUrlActivity::class.java
                        )
                    )
                }

                "XTREAM" -> {

                    startActivity(
                        Intent(
                            this,
                            XtreamActivity::class.java
                        )
                    )
                }

                else -> {

                    startActivity(
                        Intent(
                            this,
                            ChannelListActivity::class.java
                        )
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        recreate()
    }
}
