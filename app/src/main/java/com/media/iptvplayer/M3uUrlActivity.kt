package com.media.iptvplayer

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.media.iptvplayer.model.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class M3uUrlActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private lateinit var loadingLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m3u_url)

        prefs = getSharedPreferences("drafts", MODE_PRIVATE)
        loadingLayout = findViewById(R.id.loadingLayout)

        val etName = findViewById<EditText>(R.id.etPlaylistName)
        val etUrl = findViewById<EditText>(R.id.etPlaylistUrl)

        etName.setText(prefs.getString("m3u_name", ""))
        etUrl.setText(prefs.getString("m3u_url", ""))

        findViewById<Button>(R.id.btnSaveM3u)
            .setOnClickListener {

                val name = etName.text.toString().trim()
                val url = etUrl.text.toString().trim()

                if (name.isEmpty() || url.isEmpty()) {
                    Toast.makeText(this,
                        "Tüm alanları doldurun",
                        Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                showTypeDialog(name, url)
            }
    }

    private fun showTypeDialog(name: String, url: String) {

        val options = arrayOf(
            "M3U URL",
            "XTREAM CODES",
            "MAG"
        )

        var selected = when {
            url.contains("get.php", true) &&
                    url.contains("username=", true) -> 1

            url.contains(".m3u", true) ||
                    url.contains(".m3u8", true) -> 0

            else -> 0
        }

        AlertDialog.Builder(this)
            .setTitle("Liste Türünü Seç")
            .setSingleChoiceItems(
                options,
                selected
            ) { _, which ->
                selected = which
            }
            .setPositiveButton("Kaydet") { _, _ ->

                val type = when (selected) {
                    1 -> "XTREAM"
                    2 -> "MAG"
                    else -> "M3U"
                }

                savePlaylist(name, url, type)
            }
            .setNegativeButton("İptal", null)
            .show()
    }

    private fun savePlaylist(
        name: String,
        url: String,
        type: String
    ) {

        loadingLayout.visibility = View.VISIBLE

        lifecycleScope.launch {

            try {

                val content =
                    withContext(Dispatchers.IO) {
                        NetworkUtils.downloadText(url)
                    }

                val channels =
                    withContext(Dispatchers.Default) {
                        M3uParser.parse(content)
                    }

                ChannelRepository.setChannels(channels)

                PlaylistManager.addPlaylist(
                    this@M3uUrlActivity,
                    Playlist(
                        name = name,
                        type = type,
                        url = url
                    )
                )

                prefs.edit().clear().apply()

                loadingLayout.visibility = View.GONE

                Toast.makeText(
                    this@M3uUrlActivity,
                    "${channels.size} kanal yüklendi",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(
                    Intent(
                        this@M3uUrlActivity,
                        MainActivity::class.java
                    )
                )

                finish()

            } catch (e: Exception) {

                loadingLayout.visibility = View.GONE

                Toast.makeText(
                    this@M3uUrlActivity,
                    "Hata: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        prefs.edit()
            .putString("m3u_name",
                findViewById<EditText>(
                    R.id.etPlaylistName
                ).text.toString())
            .putString("m3u_url",
                findViewById<EditText>(
                    R.id.etPlaylistUrl
                ).text.toString())
            .apply()
    }
}
