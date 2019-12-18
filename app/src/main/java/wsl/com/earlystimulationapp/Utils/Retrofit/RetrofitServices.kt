package wsl.com.earlystimulationapp.Utils.Retrofit

import io.reactivex.Observable
import retrofit2.http.*
import wsl.com.earlystimulationapp.Data.Entity.EActivity
import wsl.com.earlystimulationapp.Data.Entity.EArticle
import wsl.com.earlystimulationapp.Data.Entity.EArticles


interface ServicesData{

    @GET("catalogue/activities")
    fun getActivities(): Observable<List<EActivity>>

    @GET("catalogue/articles")
    fun getArticles(): Observable<List<EArticles>>

    @GET
    fun getArticleDetail( @Url url: String ): Observable<EArticle>

}

