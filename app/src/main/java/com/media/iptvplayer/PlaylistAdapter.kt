package com.media.iptvplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PlaylistAdapter(
    context: Context,
    private val playlists: List<String>
) : ArrayAdapter<String>(
    context,
    0,
    playlists
) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view = convertView ?: LayoutInflater
            .from(context)
            .inflate(
                R.layout.item_playlist,
                parent,
                false
            )

        val txtName =
            view.findViewById<TextView>(
                R.id.txtPlaylistName
            )

        txtName.text = playlists[position]

        return view
    }
}
