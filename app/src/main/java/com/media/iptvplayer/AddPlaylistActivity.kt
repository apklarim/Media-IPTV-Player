package com.media.iptvplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.media.iptvplayer.model.Playlist

class AddPlaylistActivity : AppCompatActivity() {

    private val filePicker =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->

            if (uri != null) {

                try {

                    // Dosyayı oku
                    val inputStream =
                        contentResolver.openInputStream(uri)

                    val content =
                        inputStream?.bufferedReader()
                            ?.use { it.readText() } ?: ""

                    // Kanalları parse et
                    val channels =
                        M3uParser.parse(content)

                    // Repository'ye yükle
                    ChannelRepository.channels = channels

                    // Listeyi kaydet
                    PlaylistManager.addPlaylist(
                        this,
                        Playlist(
                            name = "M3U Dosyası",
                            type = "M3U_FILE",
                            url = uri.toString()
                        )
                    )

                    Toast.makeText(
                        this,
                        "Toplam ${channels.size} kanal bulundu",
                        Toast.LENGTH_LONG
                    ).show()

                    // Kanal listesine git
                    startActivity(
                        Intent(
                            this,
                            ChannelListActivity::class.java
                        )
                    )

                } catch (e: Exception) {

                    Toast.makeText(
                        this,
                        "Hata: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_playlist)

        // M3U URL
        findViewById<Button>(R.id.btnM3uUrl)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        M3uUrlActivity::class.java
                    )
                )
            }

        // M3U Dosya
        findViewById<Button>(R.id.btnM3uFile)
            .setOnClickListener {

                filePicker.launch("*/*")
            }

        // Xtream
        findViewById<Button>(R.id.btnXtream)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        XtreamActivity::class.java
                    )
                )
            }
    }
}
