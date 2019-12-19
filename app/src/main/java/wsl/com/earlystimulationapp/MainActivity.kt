package wsl.com.earlystimulationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import wsl.com.earlystimulationapp.Data.ViewModel.DataViewModel
import wsl.com.earlystimulationapp.Utils.ViewPageAdapter
import wsl.com.earlystimulationapp.Utils.getFragmentsToViewPager
import wsl.com.earlystimulationapp.Utils.simulateDownloadListOfMonths

class MainActivity : AppCompatActivity() {

    internal lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar( toolbar )

        InitUI().run()

    }

    //TODO: Hay una fuga de memoria en el CPU de 2 megas rastrear esa fuga

    inner class InitUI: Thread(){

        override fun run() {
            super.run()

            dataViewModel = ViewModelProviders.of( this@MainActivity ).get( DataViewModel::class.java )

            initSpinner()

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

        private fun initSpinner(){

            val itemsList = simulateDownloadListOfMonths()

            val adapter = ArrayAdapter<String>( applicationContext, R.layout.spinner_item, itemsList )
            val spinner = findViewById<Spinner>( R.id._months_selector )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected( parent: AdapterView<*>?, view: View?,  position: Int,  id: Long ) {

                    var number = ( view as TextView? )?.text ?: dataViewModel.skill_id
                    number = number.subSequence( 0, 1 ).toString()

                    if ( !number.isDigitsOnly() )
                        number = "5" //No encontre como traer todos los meses

                    dataViewModel.skill_id = number

                }

            }

        }

    }

}
