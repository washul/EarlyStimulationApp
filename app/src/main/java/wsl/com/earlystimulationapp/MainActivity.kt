package wsl.com.earlystimulationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import wsl.com.earlystimulationapp.Data.ViewModel.DataViewModel
import wsl.com.earlystimulationapp.Utils.ViewPageAdapter
import wsl.com.earlystimulationapp.Utils.getFragmentsToViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar( toolbar )

        InitUI().run()

    }

    inner class InitUI: Thread(){

        override fun run() {
            super.run()

            dataViewModel = ViewModelProviders.of( this@MainActivity ).get( DataViewModel::class.java )
            dataViewModel.downloadActivities {

                Log.e(wsl.com.earlystimulationapp.Utils.TAG, it.toString() )

            }

            supportActionBar?.title = getString(R.string.crawling)

            initViewPager()

        }

        private fun initViewPager(){

            val viewPager = findViewById<ViewPager>( R.id.container )
            viewPager.adapter = ViewPageAdapter( supportFragmentManager, getFragmentsToViewPager() )

            val tabLayour = findViewById<TabLayout>( R.id.tabs )
            tabLayour.setupWithViewPager( viewPager )

            viewPager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(tabLayour))


        }

    }

}