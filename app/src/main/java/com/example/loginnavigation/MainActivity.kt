package com.example.loginnavigation

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Load default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BroadcastFragment())
                .commit()
            supportActionBar?.title = "Broadcast Receiver"
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_broadcast -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, BroadcastFragment())
                        .commit()
                    supportActionBar?.title = "Broadcast Receiver"
                }
                R.id.nav_image_scale -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ImageScaleFragment())
                        .commit()
                    supportActionBar?.title = "Image Scale"
                }
                R.id.nav_video -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, VideoFragment())
                        .commit()
                    supportActionBar?.title = "Video"
                }
                R.id.nav_audio -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, AudioFragment())
                        .commit()
                    supportActionBar?.title = "Audio"
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
}