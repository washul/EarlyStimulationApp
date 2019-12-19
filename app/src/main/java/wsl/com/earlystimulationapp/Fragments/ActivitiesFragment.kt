package wsl.com.earlystimulationapp.Fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import wsl.com.earlystimulationapp.Data.Entity.EActivity
import wsl.com.earlystimulationapp.MainActivity

import wsl.com.earlystimulationapp.R

class ActivitiesFragment : Fragment() {

    private lateinit var uIView: View

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {

        uIView = inflater.inflate(R.layout.fragment_activities, container, false)

        InitUI().run()

        return uIView

    }

    inner class InitUI: Thread(){

        private val dataViewModel = ( activity!! as MainActivity ).dataViewModel
        private var adapterActivities: AdapterActivities? = null

        private val _swipeToRefresh = uIView.findViewById<SwipeRefreshLayout>( R.id._swipetorefresh )

        override fun run() {
            super.run()

            _swipeToRefresh.isRefreshing = true
            initRecycler()

            dataViewModel.activityListLiveData.observe( this@ActivitiesFragment, Observer { responseList ->

                if ( adapterActivities == null )
                    return@Observer

                adapterActivities?.updateList( responseList )
                _swipeToRefresh.isRefreshing = false

            })

        }

        private fun initRecycler(){

            adapterActivities = AdapterActivities( emptyList(), context!! )

            val recyclerView = uIView.findViewById<RecyclerView>( R.id._recycler_activities )
            recyclerView.apply {

                this.layoutManager = LinearLayoutManager( context!! )
                this.adapter = adapterActivities

            }

            _swipeToRefresh.setOnRefreshListener {

                _swipeToRefresh.isRefreshing = true
                dataViewModel.activityList = null
                downloadActivities()

            }

        }

        private fun downloadActivities() {

            _swipeToRefresh.isRefreshing = true
            dataViewModel.getActivities()

        }

    }


}

class AdapterActivities(private var list: List<EActivity>, private val context: Context) : RecyclerView.Adapter<AdapterActivities.MyViewHolder>() {

    private val picasso = Picasso.with( context )

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val image       = view.findViewById<ImageView>( R.id._image_activity )
        val title       = view.findViewById<TextView>( R.id._title_activity )
        val subtitle    = view.findViewById<TextView>( R.id._subtitle_activity )

        fun bind(item: EActivity){

            title.text      = item.name
            subtitle.text   = item.purpose

            picasso
                .load( item.thumbnail )
                .fit()
                .into( image )

            image.setOnClickListener {



            }

            setIsRecyclable( false )

        }

    }

    fun updateList( itemList: List<EActivity> ){
        this.list = itemList
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from( context )
        return MyViewHolder( layoutInflater.inflate( R.layout.cardview_activities, parent, false ) )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(list[position])


}
