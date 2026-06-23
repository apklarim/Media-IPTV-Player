package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.media.iptvplayer.model.Playlist

class XtreamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_xtream)

        val etName =
            findViewById<EditText>(R.id.etXtreamName)

        val etServer =
            findViewById<EditText>(R.id.etServer)

        val etUsername =
            findViewById<EditText>(R.id.etUsername)

        val etPassword =
            findViewById<EditText>(R.id.etPassword)

        findViewById<Button>(R.id.btnSaveXtream)
            .setOnClickListener {

                val name = etName.text.toString().trim()
                val server = etServer.text.toString().trim()
                val username = etUsername.text.toString().trim()
                val password = etPassword.text.toString().trim()

                if (name.isEmpty()
                    || server.isEmpty()
                    || username.isEmpty()
                    || password.isEmpty()
                ) {

                    Toast.makeText(
                        this,
                        "Lütfen tüm alanları doldurun",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                PlaylistManager.addPlaylist(
                    this,
                    Playlist(
                        name = name,
                        type = "XTREAM",
                        server = server,
                        username = username,
                        password = password
                    )
                )

                Toast.makeText(
                    this,
                    "Xtream liste kaydedildi",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(
                        this,
                        PlaylistListActivity::class.java
                    )
                )

                finish()
            }
    }
}
