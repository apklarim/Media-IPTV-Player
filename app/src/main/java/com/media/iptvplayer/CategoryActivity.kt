package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_channel_list)

        val category =
            intent.getStringExtra("category")
                ?: "LIVE"

        val listView =
            findViewById<ListView>(R.id.listChannels)

        val channels =
            ChannelRepository.channels.filter {

                it.category == category
            }

        val names =
            channels.map { it.name }

        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            names
        )

        listView.setOnItemClickListener { _, _, position, _ ->

            startActivity(
                Intent(
                    this,
                    PlayerActivity::class.java
                ).putExtra(
                    "url",
                    channels[position].url
                )
            )
        }
    }
}
