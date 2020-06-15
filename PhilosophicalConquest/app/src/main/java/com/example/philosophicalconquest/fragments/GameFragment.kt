package com.example.philosophicalconquest.fragments

import android.content.Context
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
import com.example.philosophicalconquest.models.MarginItemDecoration
import com.example.philosophicalconquest.models.PhilosophyCell
import com.example.philosophicalconquest.recycler.PhilosofyAdapter


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
    private lateinit var recyclerView: RecyclerView
    private lateinit var backToMenuButton: Button
    private var time = -1
    private lateinit var timeTextView: TextView

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
        recyclerView = rootView.findViewById(R.id.game_recycler_view)

        val mLayoutManager: LinearLayoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = mLayoutManager

        recyclerView.addItemDecoration(
            MarginItemDecoration(
                12
            )
        )
        recyclerView.adapter = PhilosofyAdapter()


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

        loopGame()
    }

    private fun loopGame() {
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                updateClock()
                mainHandler.postDelayed(this, 1000)
            }
        })
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

    private fun isEnd(): Boolean {

        return false
    }
}