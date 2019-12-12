package wsl.com.earlystimulationapp.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import wsl.com.earlystimulationapp.R

class ActivitiesFragment : Fragment() {

    private lateinit var uIView: View

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {

        uIView = inflater.inflate(R.layout.fragment_activities, container, false)

        InitUI().run()

        return uIView

    }

    inner class InitUI: Thread(){

        override fun run() {
            super.run()



        }

    }


}
