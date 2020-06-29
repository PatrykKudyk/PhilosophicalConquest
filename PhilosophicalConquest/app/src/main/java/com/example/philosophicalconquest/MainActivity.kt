package com.example.philosophicalconquest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.philosophicalconquest.fragments.*
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity(),
    MainMenuFragment.OnFragmentInteractionListener,
    ChoiceGameFragment.OnFragmentInteractionListener,
    ChoiceHighscoresFragment.OnFragmentInteractionListener,
    GameFragment.OnFragmentInteractionListener,
    HighscoresFragment.OnFragmentInteractionListener,
    RulesFragment.OnFragmentInteractionListener,
    WinFragment.OnFragmentInteractionListener,
    CreditsFragment.OnFragmentInteractionListener {

    lateinit var mainMenuFragment: MainMenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        mainMenuFragment = MainMenuFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_bottom_to_top, R.anim.exit_top_to_bottom,
                R.anim.enter_top_to_bottom, R.anim.exit_bottom_to_top
            )
            .add(R.id.frame_layout, mainMenuFragment)
            .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}