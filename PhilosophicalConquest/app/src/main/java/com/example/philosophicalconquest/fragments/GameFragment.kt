package com.example.philosophicalconquest.fragments

import android.content.Context
import android.icu.text.NumberFormat
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.philosophicalconquest.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_game.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AccountFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var backToMenuButton: Button
    private var time = -1
    private var ownedArray = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var money = 0
    private lateinit var moneyTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var cardView1: View
    private lateinit var cardView2: View
    private lateinit var cardView3: View
    private lateinit var cardView4: View
    private lateinit var cardView5: View
    private lateinit var cardView6: View
    private lateinit var cardView7: View
    private lateinit var cardView8: View
    private lateinit var cardView9: View
    private lateinit var owned2: TextView
    private lateinit var owned3: TextView
    private lateinit var owned4: TextView
    private lateinit var owned5: TextView
    private lateinit var owned6: TextView
    private lateinit var owned7: TextView
    private lateinit var owned8: TextView
    private lateinit var owned9: TextView
    private lateinit var incomePlus: TextView
    private lateinit var incomeMinus: TextView
    private lateinit var incomeTextView: TextView
    private lateinit var mInterstitialAd: InterstitialAd


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_game, container, false);
        initFragment()
        return rootView
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }

    private fun initFragment() {
        backToMenuButton = rootView.findViewById(R.id.game_button_back)
        timeTextView = rootView.findViewById(R.id.game_time_text_view)
        moneyTextView = rootView.findViewById(R.id.game_money_text_view)
        incomePlus = rootView.findViewById(R.id.game_income_plus)
        incomeMinus = rootView.findViewById(R.id.game_income_minus)
        incomeTextView = rootView.findViewById(R.id.game_text_view_income)

        MobileAds.initialize(rootView.context)
        mInterstitialAd = InterstitialAd(rootView.context)
//        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.adUnitId = "ca-app-pub-1626761492159766/6500258429"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        initCardViews()
        backToMenuButton.setOnClickListener {
            val mainMenuFragment = MainMenuFragment.newInstance()
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left,
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right
                )
                ?.replace(R.id.frame_layout, mainMenuFragment)
                ?.commit()
        }
        initListeners()

        loopGame()
    }

    private fun initCardViews() {
        cardView1 = rootView.findViewById(R.id.philosophy_cell_card_view_1)
        cardView2 = rootView.findViewById(R.id.philosophy_cell_card_view_2)
        cardView3 = rootView.findViewById(R.id.philosophy_cell_card_view_3)
        cardView4 = rootView.findViewById(R.id.philosophy_cell_card_view_4)
        cardView5 = rootView.findViewById(R.id.philosophy_cell_card_view_5)
        cardView6 = rootView.findViewById(R.id.philosophy_cell_card_view_6)
        cardView7 = rootView.findViewById(R.id.philosophy_cell_card_view_7)
        cardView8 = rootView.findViewById(R.id.philosophy_cell_card_view_8)
        cardView9 = rootView.findViewById(R.id.philosophy_cell_card_view_9)

        owned2 = rootView.findViewById(R.id.philosophy_cell_owned_amount_2)
        owned3 = rootView.findViewById(R.id.philosophy_cell_owned_amount_3)
        owned4 = rootView.findViewById(R.id.philosophy_cell_owned_amount_4)
        owned5 = rootView.findViewById(R.id.philosophy_cell_owned_amount_5)
        owned6 = rootView.findViewById(R.id.philosophy_cell_owned_amount_6)
        owned7 = rootView.findViewById(R.id.philosophy_cell_owned_amount_7)
        owned8 = rootView.findViewById(R.id.philosophy_cell_owned_amount_8)
        owned9 = rootView.findViewById(R.id.philosophy_cell_owned_amount_9)
    }

    private fun initListeners() {
        cardView1.setOnClickListener {
            updateMoney(true, 1)
        }
        cardView2.setOnClickListener {
            if (money >= 15) {
                updateMoney(false, 15)
                ownedArray[1]++
                owned2.text = ownedArray[1].toString()
                updateIncome()
            }
        }
        cardView3.setOnClickListener {
            if (money >= 60) {
                updateMoney(false, 60)
                ownedArray[2]++
                owned3.text = ownedArray[2].toString()
                updateIncome()
            }
        }
        cardView4.setOnClickListener {
            if (money >= 150) {
                updateMoney(false, 150)
                ownedArray[3]++
                owned4.text = ownedArray[3].toString()
                updateIncome()
            }
        }
        cardView5.setOnClickListener {
            if (money >= 650) {
                updateMoney(false, 650)
                ownedArray[4]++
                owned5.text = ownedArray[4].toString()
                updateIncome()
            }
        }
        cardView6.setOnClickListener {
            if (money >= 3500) {
                updateMoney(false, 3500)
                ownedArray[5]++
                owned6.text = ownedArray[5].toString()
                updateIncome()
            }
        }
        cardView7.setOnClickListener {
            if (money >= 20000) {
                updateMoney(false, 20000)
                ownedArray[6]++
                owned7.text = ownedArray[6].toString()
                updateIncome()
            }
        }
        cardView8.setOnClickListener {
            if (money >= 170000) {
                updateMoney(false, 170000)
                ownedArray[7]++
                owned8.text = ownedArray[7].toString()
                updateIncome()
            }
        }
        cardView9.setOnClickListener {
            if (money >= 700000) {
                updateMoney(false, 700000)
                ownedArray[8]++
                owned9.text = ownedArray[8].toString()
                updateIncome()
            }
        }
    }

    private fun loopGame() {
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                updateClock()
                addMoney()
                updateIncome()
                isEnd()
                mainHandler.postDelayed(this, 1000)
            }
        })
    }

    private fun updateMoney(add: Boolean, moneyToAdd: Int) {
        if (add) {
            money += moneyToAdd
//            formatMoneyTextView()
            moneyTextView.text = money.toString()
            incomePlus.text = "+" + moneyToAdd.toString()
            incomePlus.visibility = View.VISIBLE
            Handler().postDelayed(
                {
                    incomePlus.visibility = View.INVISIBLE
                }, 250
            )
        } else {
            money -= moneyToAdd
//            formatMoneyTextView()
            moneyTextView.text = money.toString()
            incomeMinus.text = "-" + moneyToAdd.toString()
            incomeMinus.visibility = View.VISIBLE
            Handler().postDelayed(
                {
                    incomeMinus.visibility = View.INVISIBLE
                }, 250
            )
        }
    }

    private fun formatMoneyTextView() {


//        if(money < 1000) {
//            moneyTextView.text = money.toString()
//        } else if (money < 1000000) {
//            moneyTextView.text = (money / 1000).toString() + " " + (money % 1000).toString()
//        } else if (money < 1000000000) {
//            moneyTextView.text = (money / 1000000).toString() +
//                    " " +
//                    ((money % 1000000) / 1000).toString() +
//                    " " +
//                    ((money % 1000000) % 1000).toString()
//        }
    }

    private fun updateClock() {
        time++
        val timeFirst = (time.toDouble() / 60.0).toInt()
        val timeSecond = time % 60
        if (timeSecond == 0) {
            timeTextView.text = timeFirst.toString() + ":00"
        } else if (timeSecond < 10) {
            timeTextView.text = timeFirst.toString() + ":0" + timeSecond.toString()
        } else {
            timeTextView.text = timeFirst.toString() + ":" + timeSecond.toString()
        }
    }

    private fun addMoney() {
        val income = (ownedArray[1] +
                ownedArray[2] * 5 +
                ownedArray[3] * 25 +
                ownedArray[4] * 100 +
                ownedArray[5] * 500 +
                ownedArray[6] * 2000 +
                ownedArray[7] * 10000 +
                ownedArray[8] * 60000)
        if (income != 0) {
            updateMoney(true, income)
        }
    }

    private fun updateIncome() {
        val income = (ownedArray[1] +
                ownedArray[2] * 5 +
                ownedArray[3] * 25 +
                ownedArray[4] * 100 +
                ownedArray[5] * 500 +
                ownedArray[6] * 2000 +
                ownedArray[7] * 10000 +
                ownedArray[8] * 60000)
        incomeTextView.text = income.toString()
    }

    private fun isEnd(): Boolean {
        if (param1 as Int == 1) {
            if (money >= 1000000) {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                }
                val winFragment = WinFragment.newInstance(param1 as Int, time)
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left,
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right
                    )
                    ?.replace(R.id.frame_layout, winFragment)
                    ?.commit()
                return true
            }
        } else if (param1 as Int == 2) {
            if (money >= 50000000) {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                }
                val winFragment = WinFragment.newInstance(param1 as Int, time)
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left,
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right
                    )
                    ?.replace(R.id.frame_layout, winFragment)
                    ?.commit()
                return true
            }
        } else if (param1 as Int == 3) {
            if (money >= 900000000) {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                }
                val winFragment = WinFragment.newInstance(param1 as Int, time)
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left,
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right
                    )
                    ?.replace(R.id.frame_layout, winFragment)
                    ?.commit()
                return true
            }
        }
        return false
    }
}