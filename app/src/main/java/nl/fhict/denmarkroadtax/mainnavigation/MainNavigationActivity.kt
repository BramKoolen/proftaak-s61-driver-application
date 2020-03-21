package nl.fhict.denmarkroadtax.mainnavigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main_navigation.*
import nl.fhict.denmarkroadtax.R
import javax.inject.Inject

class MainNavigationActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)
        setupViewPager()
        setupBottomNavigationListener()
    }

    private fun setupViewPager() {
        mainNavigationViewPager.offscreenPageLimit = 3
        mainNavigationViewPager.adapter = MainNavigationViewPagerAdapter(supportFragmentManager, 3)
        mainNavigationViewPager.currentItem = 1
        mainNavigationViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> mainNavigationBottomNav.selectedItemId = R.id.mainNavigationBottomNavInvoice
                    1 -> mainNavigationBottomNav.selectedItemId = R.id.mainNavigationBottomNavRides
                    2 -> mainNavigationBottomNav.selectedItemId = R.id.mainNavigationBottomNavMore
                }
            }
        })
    }

    private fun setupBottomNavigationListener() {
        mainNavigationBottomNav.selectedItemId = R.id.mainNavigationBottomNavRides
        mainNavigationBottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainNavigationBottomNavInvoice -> {
                    mainNavigationViewPager.currentItem = 0
                    true
                }
                R.id.mainNavigationBottomNavRides -> {
                    mainNavigationViewPager.currentItem = 1
                    true
                }
                R.id.mainNavigationBottomNavMore -> {
                    mainNavigationViewPager.currentItem = 2
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, MainNavigationActivity::class.java)
        }
    }
}
