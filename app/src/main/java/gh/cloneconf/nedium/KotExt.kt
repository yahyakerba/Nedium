package gh.cloneconf.nedium

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


fun Context.prefs() = getSharedPreferences("settings", MODE_PRIVATE)

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
