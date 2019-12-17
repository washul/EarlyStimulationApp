package wsl.com.earlystimulationapp.Utils.Retrofit

import io.reactivex.Observable
import retrofit2.http.*
import wsl.com.earlystimulationapp.Data.Entity.EActivity
import wsl.com.earlystimulationapp.Data.Entity.EArticle


interface ServicesData{

    @GET("activities")
    fun getActivities(): Observable<List<EActivity>>

    @GET("articles")
    fun getArticles(): Observable<List<EArticle>>

}

