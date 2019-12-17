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
import wsl.com.earlystimulationapp.Data.Entity.EArticle
import wsl.com.earlystimulationapp.MainActivity

import wsl.com.earlystimulationapp.R

/**
 * A simple [Fragment] subclass.
 */
class ArticlesFragment : Fragment() {

    private lateinit var uIView: View

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {

        uIView = inflater.inflate(R.layout.fragment_articles, container, false)

        InitUI().run()

        return uIView

    }

    inner class InitUI: Thread(){

        private val dataViewModel = ( activity!! as MainActivity).dataViewModel
        private lateinit var adapterArticles: AdapterArticles

        private val _swipeToRefresh = uIView.findViewById<SwipeRefreshLayout>( R.id._swipetorefresh )

        override fun run() {
            super.run()

            _swipeToRefresh.isRefreshing = true

            dataViewModel.getArticles { responseList ->

                initRecycler( responseList )

            }

        }

        private fun initRecycler( list: List<EArticle>) {

            adapterArticles = AdapterArticles( list, context!!)

            val recyclerView = uIView.findViewById<RecyclerView>(R.id._recycler_articles)
            recyclerView.apply {

                this.layoutManager = LinearLayoutManager(context!!)
                this.adapter = adapterArticles

            }

            _swipeToRefresh.setOnRefreshListener {

                _swipeToRefresh.isRefreshing = true
                dataViewModel.articlesList = null
                downloadItems()

            }

            _swipeToRefresh.isRefreshing = false

        }

        private fun downloadItems(){

            dataViewModel.getArticles { responseList ->

                adapterArticles.updateList( responseList )
                _swipeToRefresh.isRefreshing = false

            }

        }


    }


}

class AdapterArticles(private var list: List<EArticle>, private val context: Context) : RecyclerView.Adapter<AdapterArticles.MyViewHolder>() {

    private val picasso = Picasso.with( context )

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val image       = view.findViewById<ImageView>( R.id._image_activity )
        val title       = view.findViewById<TextView>( R.id._title_activity )
        val subtitle    = view.findViewById<TextView>( R.id._subtitle_activity )

        fun bind(item: EArticle){

            title.text      = item.name
            subtitle.text   = item.short_description

            picasso
                .load( item.picture )
                .fit()
                .into( image )

            image.setOnClickListener {



            }

            setIsRecyclable( false )

        }

    }

    fun updateList( itemList: List<EArticle> ){
        this.list = itemList
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from( context )
        return MyViewHolder( layoutInflater.inflate( R.layout.cardview_articles, parent, false ) )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(list[position])


}
