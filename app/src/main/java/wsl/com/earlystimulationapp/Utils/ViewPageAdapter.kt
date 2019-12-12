package wsl.com.earlystimulationapp.Utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

data class ModelFragment(
    val fragment: Fragment,
    val title: String?
)

class ViewPageAdapter(fragmentManager: FragmentManager, private val modelsFragments: List<ModelFragment> ): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int): CharSequence? = modelsFragments[position].title
    override fun getItem(position: Int): Fragment = modelsFragments[position].fragment
    override fun getCount(): Int = modelsFragments.size

}