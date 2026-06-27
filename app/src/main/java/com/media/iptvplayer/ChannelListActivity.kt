package com.media.iptvplayer

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.media.iptvplayer.model.Channel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChannelListActivity : AppCompatActivity() {

    private lateinit var listChannels: GridView
    private lateinit var searchBox: EditText
    private lateinit var groupContainer: LinearLayout
    private lateinit var loadingLayout: LinearLayout

    private var allChannels = mutableListOf<Channel>()
    private var filteredChannels = mutableListOf<Channel>()

    private var currentCategory = "LIVE"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_channel_list)

        listChannels = findViewById(R.id.listChannels)
        searchBox = findViewById(R.id.etSearch)
        groupContainer = findViewById(R.id.groupContainer)
        loadingLayout = findViewById(R.id.loadingLayout)

        currentCategory =
            intent.getStringExtra("CATEGORY")
                ?: "LIVE"

        listChannels.numColumns = 1

        loadingLayout.visibility = View.VISIBLE

        lifecycleScope.launch {

            withContext(Dispatchers.Default) {

                allChannels =
                    ChannelRepository.channels
                        .filter {
                            it.category == currentCategory
                        }
                        .toMutableList()

                if (allChannels.isEmpty()) {

                    allChannels =
                        ChannelRepository.channels
                            .toMutableList()
                }

                filteredChannels =
                    allChannels.toMutableList()
            }

            loadGroups()

            loadChannels()

            loadingLayout.visibility = View.GONE
        }

        searchBox.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    applyFilter()
                }

                override fun afterTextChanged(
                    s: Editable?
                ) {
                }
            }
        )

        listChannels.setOnItemClickListener {
                _, _, position, _ ->

            ChannelRepository.setChannels(
                filteredChannels
            )

            startActivity(
                Intent(
                    this,
                    PlayerActivity::class.java
                ).putExtra(
                    "url",
                    filteredChannels[position].url
                )
            )
        }

        listChannels.setOnItemLongClickListener {
                _, _, position, _ ->

            FavoriteManager.toggleFavorite(
                this,
                filteredChannels[position].name
            )

            filteredChannels[position].isFavorite =
                !filteredChannels[position].isFavorite

            loadChannels()

            true
        }
    }

    private fun applyFilter() {

        val search =
            searchBox.text.toString()
                .lowercase()

        filteredChannels =
            allChannels.filter {

                it.name.lowercase()
                    .contains(search)

            }.toMutableList()

        loadChannels()
    }

    private fun loadChannels() {

        listChannels.adapter =
            ChannelAdapter(
                this,
                filteredChannels,
                currentCategory
            )
    }

    private fun loadGroups() {

        groupContainer.removeAllViews()

        addGroupButton("Tümü") {

            filteredChannels =
                allChannels.toMutableList()

            loadChannels()
        }

        addGroupButton("⭐ Favoriler") {

            filteredChannels =
                allChannels.filter {
                    it.isFavorite
                }.toMutableList()

            loadChannels()
        }

        val groups =
            allChannels
                .map { it.group }
                .distinct()
                .filter { it.isNotEmpty() }
                .sorted()

        groups.forEach { group ->

            addGroupButton(group) {

                filteredChannels =
                    allChannels.filter {
                        it.group == group
                    }.toMutableList()

                loadChannels()
            }
        }
    }

    private fun addGroupButton(
        title: String,
        action: () -> Unit
    ) {

        val button = Button(this)

        button.text = title

        button.setBackgroundResource(
            R.drawable.focus_selector
        )

        button.setTextColor(Color.WHITE)

        val params =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        params.marginEnd = 12

        button.layoutParams = params

        button.setOnClickListener {

            action()
        }

        groupContainer.addView(button)
    }
}
