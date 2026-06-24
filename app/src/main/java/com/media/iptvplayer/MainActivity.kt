package com.media.iptvplayer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnLiveTv: Button
    private lateinit var btnMovies: Button
    private lateinit var btnSeries: Button
    private lateinit var btnPlaylists: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnLiveTv =
            findViewById(R.id.btnLiveTv)

        btnMovies =
            findViewById(R.id.btnMovies)

        btnSeries =
            findViewById(R.id.btnSeries)

        btnPlaylists =
            findViewById(R.id.btnPlaylists)

        btnLiveTv.setOnClickListener {

            animateButton(btnLiveTv)

            startActivity(
                Intent(
                    this,
                    ChannelListActivity::class.java
                ).putExtra(
                    "CATEGORY",
                    "LIVE"
                )
            )
        }

        btnMovies.setOnClickListener {

            animateButton(btnMovies)

            startActivity(
                Intent(
                    this,
                    ChannelListActivity::class.java
                ).putExtra(
                    "CATEGORY",
                    "MOVIE"
                )
            )
        }

        btnSeries.setOnClickListener {

            animateButton(btnSeries)

            startActivity(
                Intent(
                    this,
                    ChannelListActivity::class.java
                ).putExtra(
                    "CATEGORY",
                    "SERIES"
                )
            )
        }

        btnPlaylists.setOnClickListener {

            animateButton(btnPlaylists)

            startActivity(
                Intent(
                    this,
                    PlaylistListActivity::class.java
                )
            )
        }
    }

    private fun animateButton(
        button: Button
    ) {

        button.animate()
            .scaleX(1.05f)
            .scaleY(1.05f)
            .setDuration(100)
            .withEndAction {

                button.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .start()
            }
            .start()

        val vibrator =
            getSystemService(
                VIBRATOR_SERVICE
            ) as Vibrator

        if (Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O) {

            vibrator.vibrate(
                VibrationEffect
                    .createOneShot(
                        40,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
            )

        } else {

            @Suppress("DEPRECATION")
            vibrator.vibrate(40)
        }
    }
}
