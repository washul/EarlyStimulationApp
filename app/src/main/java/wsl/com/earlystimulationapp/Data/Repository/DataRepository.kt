package wsl.com.earlystimulationapp.Data.Repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import wsl.com.earlystimulationapp.Data.Entity.EActivity
import wsl.com.earlystimulationapp.Data.Entity.EArticle
import wsl.com.earlystimulationapp.Utils.Prefs
import wsl.com.earlystimulationapp.Utils.Retrofit.RetrofitClient
import wsl.com.earlystimulationapp.Utils.Retrofit.ServicesData
import java.lang.reflect.Type

class DataRepository( context: Context ) {

    private val service = RetrofitClient.instance.create(ServicesData::class.java)
    private val compositeDisposable = CompositeDisposable()
    private val preferences = Prefs(context)
    private val GSON = Gson()

    /**
     * ACTIVITY
     * */
    fun downloadActivities(function: (List<EActivity>?) -> Unit) {

        try {

            compositeDisposable.add(service.getActivities().subscribeOn(
                Schedulers.io()
            )
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->

                    Log.e("WSL", list.toString())

                    function(list)

                },
                    {
                        it.printStackTrace()
                        function(null)
                    }
                )

            )

        } catch (e: Exception) {
            e.printStackTrace()
            function(null)
        }

    }

    fun getCacheActivities(): List<EActivity> {

        val jsonString = preferences.activitiesListCache!!

        if ( jsonString == "" )
            return emptyList()

        val listType: Type = object : TypeToken<ArrayList<EActivity>>() {}.type
        return GSON.fromJson(jsonString, listType)

    }

    fun setCacheActivities( list: List<EActivity> ) {

        val jsonString = GSON.toJson( list )
        preferences.activitiesListCache = jsonString

    }


    /**
     * ARTICLES
     * */
    fun downloadArticles(function: (List<EArticle>?) -> Unit) {

        try {

            compositeDisposable.add(service.getArticles().subscribeOn(
                Schedulers.io()
            )
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->

                    Log.e("WSL", list.toString())

                    function(list)

                },
                    {
                        it.printStackTrace()
                        function(null)
                    }
                )

            )

        } catch (e: Exception) {
            e.printStackTrace()
            function(null)
        }

    }

    fun getCacheArticles(): List<EArticle> {

        val jsonString = preferences.articleListCache!!

        if ( jsonString == "" )
            return emptyList()

        val listType: Type = object : TypeToken<ArrayList<EArticle>>() {}.type
        return GSON.fromJson(jsonString, listType)

    }

    fun setCacheArticles( list: List<EArticle> ) {

        val jsonString = GSON.toJson( list )
        preferences.articleListCache = jsonString

    }

}

