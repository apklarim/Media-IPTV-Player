package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.media.iptvplayer.model.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class M3uUrlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_m3u_url)

        val etName = findViewById<EditText>(R.id.etPlaylistName)
        val etUrl = findViewById<EditText>(R.id.etPlaylistUrl)
        val btnSave = findViewById<Button>(R.id.btnSaveM3u)

        btnSave.setOnClickListener {

            val name = etName.text.toString().trim()
            val url = etUrl.text.toString().trim()

            if (name.isEmpty() || url.isEmpty()) {
                Toast.makeText(
                    this,
                    "Lütfen tüm alanları doldurun",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {

                try {

                    Toast.makeText(
                        this@M3uUrlActivity,
                        "Liste yükleniyor...",
                        Toast.LENGTH_SHORT
                    ).show()

                    val content = withContext(Dispatchers.IO) {
                        NetworkUtils.downloadText(url)
                    }

                    AlertDialog.Builder(this@M3uUrlActivity)
                        .setTitle("Sunucudan Gelen Veri")
                        .setMessage(content.take(500))
                        .setPositiveButton("Tamam", null)
                        .show()

                    val channels = M3uParser.parse(content)

                    ChannelRepository.channels = channels

                    Toast.makeText(
                        this@M3uUrlActivity,
                        "Kanal sayısı: ${channels.size}",
                        Toast.LENGTH_LONG
                    ).show()

                    PlaylistManager.addPlaylist(
                        this@M3uUrlActivity,
                        Playlist(
                            name = name,
                            type = "M3U",
                            url = url
                        )
                    )

                    startActivity(
                        Intent(
                            this@M3uUrlActivity,
                            ChannelListActivity::class.java
                        ).putExtra(
                            "playlistName",
                            name
                        )
                    )

                    finish()

                } catch (e: Exception) {

                    AlertDialog.Builder(this@M3uUrlActivity)
                        .setTitle("Hata")
                        .setMessage(e.message)
                        .setPositiveButton("Tamam", null)
                        .show()
                }
            }
        }
    }
}
