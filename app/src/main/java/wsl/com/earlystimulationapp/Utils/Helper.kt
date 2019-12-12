package wsl.com.earlystimulationapp.Utils

import android.app.Activity
import wsl.com.earlystimulationapp.Fragments.ActivitiesFragment
import wsl.com.earlystimulationapp.Fragments.ArticlesFragment
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