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
                    "M3U dosyası eklendi",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(
                        this,
                        PlaylistListActivity::class.java
                    )
                )
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

        // M3U DOSYA

        findViewById<Button>(R.id.btnM3uFile)
            .setOnClickListener {

                filePicker.launch("*/*")
            }

        // XTREAM

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
