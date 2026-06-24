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

        btnAdd.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AddPlaylistActivity::class.java
                )
            )
        }

        val playlists =
            PlaylistManager.getPlaylists(this)

        val items = mutableListOf<String>()

        playlists.forEach {

            items.add("${it.name} (${it.type})")
        }

        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )

        listView.setOnItemClickListener { _, _, position, _ ->

            startActivity(
                Intent(
                    this,
                    ChannelListActivity::class.java
                )
            )
        }
    }
}
