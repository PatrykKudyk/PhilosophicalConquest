package com.example.philosophicalconquest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.philosophicalconquest.fragments.MainMenuFragment


class MainActivity : AppCompatActivity() {

    lateinit var mainMenuFragment: MainMenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.enter_bottom_to_top, R.anim.exit_top_to_bottom,
                        R.anim.enter_top_to_bottom, R.anim.exit_bottom_to_top
                )
                .add(R.id.frame_layout, mainMenuFragment)
                .commit()
    }
}