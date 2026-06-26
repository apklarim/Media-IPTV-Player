package com.media.iptvplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.media.iptvplayer.model.Channel

class ChannelAdapter(
    context: Context,
    private val channels: List<Channel>,
    private val category: String
) : ArrayAdapter<Channel>(
    context,
    0,
    channels
) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view =
            convertView ?: LayoutInflater
                .from(context)
                .inflate(
                    R.layout.item_channel_live,
                    parent,
                    false
                )

        val channel =
            channels[position]

        val logo =
            view.findViewById<ImageView>(
                R.id.imgLogo
            )

        val name =
            view.findViewById<TextView>(
                R.id.txtChannelName
            )

        // Kanal adı

        name.text =
            if (channel.isFavorite)
                "⭐ ${channel.name}"
            else
                channel.name

        // Logo yükleme

        if (
            channel.logo.isNotEmpty()
        ) {

            logo.load(channel.logo) {

                crossfade(true)

                error(
                    android.R.drawable
                        .ic_menu_gallery
                )

                placeholder(
                    android.R.drawable
                        .ic_menu_gallery
                )
            }

        } else {

            // TEST İÇİN SABİT LOGO

            logo.load(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/512px-Google_2015_logo.svg.png"
            ) {

                crossfade(true)

                error(
                    android.R.drawable
                        .ic_menu_gallery
                )

                placeholder(
                    android.R.drawable
                        .ic_menu_gallery
                )
            }
        }

        return view
    }
}
