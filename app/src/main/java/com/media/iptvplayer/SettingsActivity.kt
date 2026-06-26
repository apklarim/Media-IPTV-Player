package com.media.iptvplayer

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var btnAbout: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_settings
        )

        btnAbout =
            findViewById(
                R.id.btnAbout
            )

        btnAbout.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle(
                    "Media IPTV Player"
                )
                .setMessage(
                    """
Sürüm : 1.1

Media IPTV Player

Telefon, Tablet,
TV ve TV Box için
gelişmiş IPTV oynatıcı.

© 2026
                    """.trimIndent()
                )
                .setPositiveButton(
                    "Tamam",
                    null
                )
                .show()
        }
    }
}
