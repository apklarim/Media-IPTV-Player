package com.media.iptvplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddPlaylistActivity : AppCompatActivity() {

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

                // Sonraki adımda eklenecek
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
