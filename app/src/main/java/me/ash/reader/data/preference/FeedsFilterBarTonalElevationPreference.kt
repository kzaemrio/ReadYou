package me.ash.reader.data.preference

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.ash.reader.data.constant.ElevationTokens
import me.ash.reader.ui.ext.DataStoreKeys
import me.ash.reader.ui.ext.dataStore
import me.ash.reader.ui.ext.put

sealed class FeedsFilterBarTonalElevationPreference(val value: Int) : Preference() {
    object Level0 : FeedsFilterBarTonalElevationPreference(ElevationTokens.Level0)
    object Level1 : FeedsFilterBarTonalElevationPreference(ElevationTokens.Level1)
    object Level2 : FeedsFilterBarTonalElevationPreference(ElevationTokens.Level2)
    object Level3 : FeedsFilterBarTonalElevationPreference(ElevationTokens.Level3)
    object Level4 : FeedsFilterBarTonalElevationPreference(ElevationTokens.Level4)
    object Level5 : FeedsFilterBarTonalElevationPreference(ElevationTokens.Level5)

    override fun put(context: Context, scope: CoroutineScope) {
        scope.launch {
            context.dataStore.put(
                DataStoreKeys.FeedsFilterBarTonalElevation,
                value
            )
        }
    }

    fun getDesc(context: Context): String =
        when (this) {
            Level0 -> "Level 0 (${ElevationTokens.Level0}dp)"
            Level1 -> "Level 1 (${ElevationTokens.Level1}dp)"
            Level2 -> "Level 2 (${ElevationTokens.Level2}dp)"
            Level3 -> "Level 3 (${ElevationTokens.Level3}dp)"
            Level4 -> "Level 4 (${ElevationTokens.Level4}dp)"
            Level5 -> "Level 5 (${ElevationTokens.Level5}dp)"
        }

    companion object {
        val default = Level0
        val values = listOf(Level0, Level1, Level2, Level3, Level4, Level5)

        fun fromPreferences(preferences: Preferences) =
            when (preferences[DataStoreKeys.FeedsFilterBarTonalElevation.key]) {
                ElevationTokens.Level0 -> Level0
                ElevationTokens.Level1 -> Level1
                ElevationTokens.Level2 -> Level2
                ElevationTokens.Level3 -> Level3
                ElevationTokens.Level4 -> Level4
                ElevationTokens.Level5 -> Level5
                else -> default
            }
    }
}

