package ru.alexereh.tickets.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import ru.alexereh.tickets.presentation.R
import ru.alexereh.tickets.presentation.databinding.ActivityMainBinding
import ru.alexereh.tickets.presentation.navigation.HotelsNavFragment
import ru.alexereh.tickets.presentation.navigation.ProfileNavFragment
import ru.alexereh.tickets.presentation.navigation.ShorterNavFragment
import ru.alexereh.tickets.presentation.navigation.SubscriptionsNavFragment
import ru.alexereh.tickets.presentation.navigation.TicketsNavFragment
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var subscriptionsFragment: SubscriptionsNavFragment

    @Inject
    lateinit var shorterFragment: ShorterNavFragment

    @Inject
    lateinit var profileFragment: ProfileNavFragment

    @Inject
    lateinit var hotelsFragment: HotelsNavFragment

    @Inject
    lateinit var ticketsFragment: TicketsNavFragment

    class ViewPagerAdapter(
        activity: MainActivity,
        private val ticketsFragment: TicketsNavFragment,
        private val shorterFragment: ShorterNavFragment,
        private val profileFragment: ProfileNavFragment,
        private val subscriptionsFragment: SubscriptionsNavFragment,
        private val hotelsFragment: HotelsNavFragment,
    ) : FragmentStateAdapter(activity) {

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ticketsFragment
                1 -> hotelsFragment
                2 -> shorterFragment
                3 -> subscriptionsFragment
                4 -> profileFragment
                else -> throw RuntimeException()
            }
        }

        override fun getItemCount(): Int {
            return 5
        }
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding) {
            viewPagerAdapter = ViewPagerAdapter(
                this@MainActivity,
                ticketsFragment,
                shorterFragment,
                profileFragment,
                subscriptionsFragment,
                hotelsFragment
            )
            mainBottomNavView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.tickets -> mainViewPager.setCurrentItem(0, true)
                    R.id.hotels -> mainViewPager.setCurrentItem(1, true)
                    R.id.shorter -> mainViewPager.setCurrentItem(2, true)
                    R.id.subscriptions -> mainViewPager.setCurrentItem(3, true)
                    R.id.profile -> mainViewPager.setCurrentItem(4, true)
                }
                true
            }
            mainViewPager.adapter = viewPagerAdapter
            mainViewPager.isUserInputEnabled = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        setContentView(binding.root)
    }
}