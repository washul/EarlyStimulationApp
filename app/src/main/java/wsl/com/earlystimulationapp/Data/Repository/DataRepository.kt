package wsl.com.earlystimulationapp.Data.Repository

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import wsl.com.earlystimulationapp.Data.Room.Entity.EActivity
import wsl.com.earlystimulationapp.Utils.Retrofit.RetrofitClient
import wsl.com.earlystimulationapp.Utils.Retrofit.ServicesData
import java.lang.Exception

class DataRepository {

    private  val service = RetrofitClient.instance.create( ServicesData::class.java )
    private val compositeDisposable = CompositeDisposable()

    fun downloadActivities( function: (List<EActivity>?) -> Unit){

        try {

            compositeDisposable.add( service.getActivities().subscribeOn(
                Schedulers.io() )
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

        }catch ( e: Exception){
            e.printStackTrace()
            function(null)
        }

    }

}