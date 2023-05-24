package com.learning.utills

import android.content.Context
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrf @Inject constructor(@ApplicationContext context: Context) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getStoredTag(str: String): String {
        return prefs.getString(str, "")!!
    }

    fun setStoredTag( str: String,query: String) {
        prefs.edit().putString(str, query).apply()
    }
}