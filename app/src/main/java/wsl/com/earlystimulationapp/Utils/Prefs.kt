package wsl.com.earlystimulationapp.Utils

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    private val PREFS_NAME = "wsl.com.earlystimulationapp"
    private val ARTICLE_NAME = "articlesListCache"
    private val ACTIVITY_NAME = "activitiesListCache"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var articleListCache: String?
        get() = prefs.getString(ARTICLE_NAME, "")
        set(value) = prefs.edit().putString(ARTICLE_NAME, value).apply()

    var activitiesListCache: String?
        get() = prefs.getString(ACTIVITY_NAME, "")
        set(value) = prefs.edit().putString(ACTIVITY_NAME, value).apply()

}