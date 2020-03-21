package nl.fhict.denmarkroadtax.mainnavigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import nl.fhict.denmarkroadtax.invoice.InvoiceFragment
import nl.fhict.denmarkroadtax.more.MoreFragment
import nl.fhict.denmarkroadtax.rides.RidesFragment

class MainNavigationViewPagerAdapter(fragmentManager: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fragmentManager, behavior) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                InvoiceFragment()
            }
            1 -> {
                RidesFragment()
            }
            2 -> {
                MoreFragment()
            }
            else -> RidesFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}