package wsl.com.earlystimulationapp.Fragments


import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso
import wsl.com.earlystimulationapp.Data.ViewModel.DataViewModel
import wsl.com.earlystimulationapp.MainActivity

import wsl.com.earlystimulationapp.R


class ArticleDetailFragment: DialogFragment() {

    private lateinit var uIView: View

    companion object{
        const val TAG = "DetailsFragment"
    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog

        if ( dialog != null ) {

            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle )
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {

        uIView = inflater.inflate(R.layout.fragment_article_detail, container, false)

        InitUI().run()

        return uIView

    }

    inner class InitUI: Thread() {

        private val dataViewModel = ( activity!! as MainActivity ).dataViewModel
        private val picasso = Picasso.with( context!! )

        override fun run() {
            super.run()

            initToolbar()
            initImage()
            initTitle()
            initBody()

        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private fun initToolbar(){

            val toolbar = uIView.findViewById<Toolbar>( R.id.toolbar_collapsing )
            toolbar.setNavigationIcon( R.drawable.ic_arrow_back )
            toolbar.setNavigationOnClickListener {
                dismiss()
            }

        }

        private fun initImage(){

            picasso
                .load( dataViewModel.articleDetail?.picture )
                .into( uIView.findViewById<ImageView>( R.id._image_article_detail ) )

        }

        private fun initTitle(){

            uIView.findViewById<CollapsingToolbarLayout>(R.id.collapsing).title =
                dataViewModel.articleDetail?.title

        }

        private fun initBody(){

            uIView.findViewById<TextView>( R.id._body_article_detail ).text =
                if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N )
                    Html.fromHtml( dataViewModel.articleDetail?.body, Html.FROM_HTML_MODE_COMPACT )
                else
                    Html.fromHtml( dataViewModel.articleDetail?.body )

        }

    }


}
