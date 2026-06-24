package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.media.iptvplayer.model.Channel

class ChannelListActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var channels = mutableListOf<Channel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_channel_list)

        listView =
            findViewById(R.id.listChannels)

        val category =
            intent.getStringExtra("CATEGORY")
                ?: "LIVE"

        title = when (category) {

            "MOVIES" -> "Filmler"

            "SERIES" -> "Diziler"

            else -> "Canlı TV"
        }

        channels =
            ChannelRepository.channels
                .filter {

                    it.category == category

                }.toMutableList()

        if (channels.isEmpty()) {

            Toast.makeText(
                this,
                "İçerik bulunamadı",
                Toast.LENGTH_LONG
            ).show()
        }

        val names =
            channels.map { it.name }

        listView.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                names
            )

        listView.setOnItemClickListener { _, _, position, _ ->

            startActivity(
                Intent(
                    this,
                    PlayerActivity::class.java
                ).apply {

                    putExtra(
                        "url",
                        channels[position].url
                    )

                    putExtra(
                        "name",
                        channels[position].name
                    )
                }
            )
        }
    }
}
