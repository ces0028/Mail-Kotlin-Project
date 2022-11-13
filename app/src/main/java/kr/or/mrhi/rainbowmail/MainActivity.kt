package kr.or.mrhi.rainbowmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import kr.or.mrhi.rainbowmail.databinding.ActivityMainBinding
import kr.or.mrhi.rainbowmail.databinding.TabButtonBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    lateinit var navigationView: NavigationView
    lateinit var fragmentReceive: FragmentReceive
    lateinit var fragmentSend: FragmentSend
    lateinit var fragmentSpam: FragmentSpam

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.drawerOpen, R.string.drawerClose)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val pagerAdapter = PagerAdapter(this)
        val titleList = mutableListOf<String>("receive", "send", "spam")
        fragmentReceive = FragmentReceive()
        fragmentSend = FragmentSend()
        fragmentSpam = FragmentSpam()

        pagerAdapter.addFragment(fragmentReceive, titleList[0])
        pagerAdapter.addFragment(fragmentSend, titleList[1])
        pagerAdapter.addFragment(fragmentSpam, titleList[2])

        binding.viewpager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewpager){ tab, position ->
            tab.setCustomView(createTabView(titleList[position]))
        }.attach()

        navigationView = binding.navigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun createTabView(title: String): View {
        val tabBinding = TabButtonBinding.inflate(layoutInflater)
        when(title) {
            "receive" -> tabBinding.ivTabIcon.setImageResource(R.drawable.receive)
            "send" -> tabBinding.ivTabIcon.setImageResource(R.drawable.send)
            "spam" -> tabBinding.ivTabIcon.setImageResource(R.drawable.spam)
        }
        return tabBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuReceive -> {
                Toast.makeText(this, "받은메일함을 누르셨습니다.", Toast.LENGTH_SHORT).show()
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                binding.viewpager.currentItem = 0
            }
            R.id.menuSend -> {
                Toast.makeText(this, "보낸메일함을 누르셨습니다.", Toast.LENGTH_SHORT).show()
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                binding.viewpager.currentItem = 1
            }
            R.id.menuSpam -> {
                Toast.makeText(this, "스팸메일함을 누르셨습니다.", Toast.LENGTH_SHORT).show()
                binding.drawerLayout.closeDrawer(Gravity.LEFT)
                binding.viewpager.currentItem = 2
            }
        }
        return false
    }
}