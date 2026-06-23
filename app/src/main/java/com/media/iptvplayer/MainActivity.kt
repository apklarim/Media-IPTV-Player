package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Canlı TV
        findViewById<LinearLayout>(R.id.cardLive)
            .setOnClickListener {

                Toast.makeText(
                    this,
                    "Canlı TV yakında eklenecek",
                    Toast.LENGTH_SHORT
                ).show()
            }

        // Filmler
        findViewById<LinearLayout>(R.id.cardMovies)
            .setOnClickListener {

                Toast.makeText(
                    this,
                    "Filmler yakında eklenecek",
                    Toast.LENGTH_SHORT
                ).show()
            }

        // Diziler
        findViewById<LinearLayout>(R.id.cardSeries)
            .setOnClickListener {

                Toast.makeText(
                    this,
                    "Diziler yakında eklenecek",
                    Toast.LENGTH_SHORT
                ).show()
            }

        // Ayarlar
        findViewById<LinearLayout>(R.id.cardSettings)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        SettingsActivity::class.java
                    )
                )
            }
    }
}
