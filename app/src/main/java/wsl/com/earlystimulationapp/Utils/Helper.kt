package wsl.com.earlystimulationapp.Utils

import android.app.Activity
import androidx.fragment.app.Fragment
import wsl.com.earlystimulationapp.Fragments.ActivitiesFragment
import wsl.com.earlystimulationapp.Fragments.ArticlesFragment
import wsl.com.earlystimulationapp.MainActivity
import wsl.com.earlystimulationapp.R

fun Activity.getFragmentsToViewPager(): List<ModelFragment> {
    
    return listOf( 
        ModelFragment( 
            fragment = ActivitiesFragment(), 
            title = getString(R.string.activity) ),
        ModelFragment( 
            fragment = ArticlesFragment(), 
            title = getString(R.string.articles)
        )
    )

}

fun simulateDownloadListOfMonths(): ArrayList<String>{

    val list = ArrayList<String>()

    list.add("ALL MONTHS")

    for ( i in 1..54 ){

        list.add("$i MONTHS")

    }

    return list

}