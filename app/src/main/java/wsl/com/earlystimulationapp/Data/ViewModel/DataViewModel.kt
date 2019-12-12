package wsl.com.earlystimulationapp.Data.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import wsl.com.earlystimulationapp.Data.Repository.DataRepository
import wsl.com.earlystimulationapp.Data.Room.Entity.EActivity

class DataViewModel( application: Application ): AndroidViewModel( application ){

    private val dataRepository = DataRepository()

    fun downloadActivities( function: ( List<EActivity>?) -> Unit ) = dataRepository.downloadActivities( function )


}