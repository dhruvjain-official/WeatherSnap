package com.yourname.weathersnap.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(
    name = "draft_store"
)

class DraftManager(
    private val context: Context
) {

    companion object {

        val NOTES =
            stringPreferencesKey("notes")

        val IMAGE_PATH =
            stringPreferencesKey("image_path")

        val CITY =
            stringPreferencesKey("city")
    }

    suspend fun saveDraft(
        notes: String,
        imagePath: String,
        city: String
    ) {

        context.dataStore.edit { prefs ->

            prefs[NOTES] = notes
            prefs[IMAGE_PATH] = imagePath
            prefs[CITY] = city
        }
    }

    suspend fun getNotes(): String {

        return context.dataStore.data
            .first()[NOTES] ?: ""
    }

    suspend fun getImagePath(): String {

        return context.dataStore.data
            .first()[IMAGE_PATH] ?: ""
    }

    suspend fun getCity(): String {

        return context.dataStore.data
            .first()[CITY] ?: ""
    }

    suspend fun clearDraft() {

        context.dataStore.edit { prefs ->

            prefs.clear()
        }
    }
}