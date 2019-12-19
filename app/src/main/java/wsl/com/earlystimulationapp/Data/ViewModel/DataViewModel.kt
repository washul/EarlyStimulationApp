package wsl.com.earlystimulationapp.Data.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.jetbrains.anko.runOnUiThread
import wsl.com.earlystimulationapp.Data.Repository.DataRepository
import wsl.com.earlystimulationapp.Data.Entity.EActivity
import wsl.com.earlystimulationapp.Data.Entity.EArticle
import wsl.com.earlystimulationapp.Data.Entity.EArticles
import wsl.com.earlystimulationapp.R

class DataViewModel( application: Application ): AndroidViewModel( application ){

    private val dataRepository = DataRepository( application.applicationContext )

    var skill_id: String = "5"
        set(value) {
            getActivities()
            field = value
        }
    val activityListLiveData = MutableLiveData<List<EActivity>>()


    var articlesList: List<EArticles>? = null
    var activityList: List<EActivity>? = null
    var articleDetail: EArticle? = null

    private fun sendToast(){

        getApplication<Application>().runOnUiThread {

            Toast.makeText( applicationContext, getString(R.string.no_se_a_podido_descargar), Toast.LENGTH_LONG ).show()

        }

    }

    /**
     * Activities
     * */
    fun getActivities() {

        dataRepository.downloadActivities( this.skill_id ){ responseList ->

            if ( responseList == null ){

                this.activityList = dataRepository.getCacheActivities()
                sendToast()

            }else{

                this.activityList = responseList
                dataRepository.setCacheActivities( responseList )

            }

            this.activityListLiveData.postValue( responseList )

        }

    }


    /**
     * Articles
     * */
    fun getArticles( function: ( List<EArticles> ) -> Unit ) {

        if ( articlesList == null ){

            dataRepository.downloadArticles{ responseList ->

                if ( responseList == null ){

                    this.articlesList = dataRepository.getCacheArticles()
                    sendToast()

                }else{

                    this.articlesList = responseList
                    dataRepository.setCacheArticles( responseList )

                }

                function( this.articlesList!! )

            }

        }else{

            function( this.articlesList!! )

        }

    }

    fun downloadArticleDetail( articleID: String, function: ( EArticle? ) -> Unit ) {

        dataRepository.downloadArticleDetail( articleID ){ articleResponse ->

           this.articleDetail = articleResponse
            function( this.articleDetail )

        }

    }

}