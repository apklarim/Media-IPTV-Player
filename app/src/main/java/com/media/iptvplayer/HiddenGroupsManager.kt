package com.media.iptvplayer

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HiddenGroupsManager {

    private const val FILE_NAME =
        "hidden_groups.json"

    private fun loadGroups():
            MutableSet<String> {

        val json =
            FileStorageManager.readText(
                FILE_NAME,
                "[]"
            )

        val type =
            object : TypeToken<MutableSet<String>>() {}.type

        return try {

            Gson().fromJson<MutableSet<String>>(
                json,
                type
            ) ?: mutableSetOf()

        } catch (e: Exception) {

            mutableSetOf()
        }
    }

    private fun saveGroups(
        groups: MutableSet<String>
    ) {

        FileStorageManager.writeText(
            FILE_NAME,
            Gson().toJson(groups)
        )
    }

    fun hideGroup(
        context: Context,
        group: String
    ) {

        val groups = loadGroups()

        groups.add(group)

        saveGroups(groups)
    }

    fun unhideGroup(
        context: Context,
        group: String
    ) {

        val groups = loadGroups()

        groups.remove(group)

        saveGroups(groups)
    }

    fun isHidden(
        context: Context,
        group: String
    ): Boolean {

        return loadGroups().contains(group)
    }

    fun getHiddenGroups(
        context: Context
    ): Set<String> {

        return loadGroups()
    }

    fun clearHiddenGroups(
        context: Context
    ) {

        saveGroups(mutableSetOf())
    }

    fun restoreGroup(
        context: Context,
        group: String
    ) {

        unhideGroup(
            context,
            group
        )
    }
}
