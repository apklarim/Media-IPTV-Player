package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.media.iptvplayer.model.Channel

class ChannelListActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var searchBox: EditText

    private var channels =
        mutableListOf<Channel>()

    private var filteredChannels =
        mutableListOf<Channel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_channel_list
        )

        listView =
            findViewById(R.id.listChannels)

        searchBox =
            findViewById(R.id.etSearch)

        val category =
            intent.getStringExtra(
                "CATEGORY"
            ) ?: "LIVE"

        channels =
            ChannelRepository.channels
                .filter {
                    it.category == category
                }
                .toMutableList()

        channels.forEach {

            it.isFavorite =
                FavoriteManager.isFavorite(
                    this,
                    it.name
                )
        }

        channels.sortByDescending {
            it.isFavorite
        }

        filteredChannels =
            channels.toMutableList()

        loadList()

        searchBox.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {

                    val query =
                        s.toString().lowercase()

                    filteredChannels =
                        channels.filter {

                            it.name.lowercase()
                                .contains(query)

                        }.toMutableList()

                    loadList()
                }

                override fun afterTextChanged(
                    s: Editable?
                ) {}
            }
        )

        listView.setOnItemClickListener {

                _, _, position, _ ->

            startActivity(
                Intent(
                    this,
                    PlayerActivity::class.java
                )
                    .putExtra(
                        "url",
                        filteredChannels[position].url
                    )
            )
        }

        listView.setOnItemLongClickListener {

                _, _, position, _ ->

            FavoriteManager.toggleFavorite(
                this,
                filteredChannels[position].name
            )

            filteredChannels[position]
                .isFavorite =
                !filteredChannels[position]
                    .isFavorite

            channels.forEach {

                if (it.name ==
                    filteredChannels[position].name
                ) {

                    it.isFavorite =
                        filteredChannels[position]
                            .isFavorite
                }
            }

            channels.sortByDescending {
                it.isFavorite
            }

            filteredChannels =
                channels.toMutableList()

            loadList()

            true
        }
    }

    private fun loadList() {

        val names =
            filteredChannels.map {

                if (it.isFavorite)

                    "⭐ ${it.name}"

                else

                    it.name
            }

        listView.adapter =
            ArrayAdapter(
                this,
                android.R.layout
                    .simple_list_item_1,
                names
            )
    }
}
