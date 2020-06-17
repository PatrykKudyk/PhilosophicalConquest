package com.example.philosophicalconquest.fragments

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.philosophicalconquest.R
import com.example.philosophicalconquest.db.DataBaseHelper
import com.example.philosophicalconquest.db.TableInfo
import kotlinx.android.synthetic.main.fragment_win.view.*


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
class WinFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var numberTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var nickname: TextView
    private lateinit var saveScoreButton: Button
    private lateinit var playAgainButton: Button
    private lateinit var backToMenuButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_win, container, false);
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
        fun newInstance(param1: Int, param2: Int) =
            WinFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }

    private fun initFragment() {
        numberTextView = rootView.findViewById(R.id.win_number_text_view)
        timeTextView = rootView.findViewById(R.id.win_time_text_view)
        saveScoreButton = rootView.findViewById(R.id.button_win_save_score)
        playAgainButton = rootView.findViewById(R.id.button_win_play_again)
        backToMenuButton = rootView.findViewById(R.id.button_win_back_to_menu)
        nickname = rootView.findViewById(R.id.win_text_input_nickname)
        when (param1) {
            1 -> {
                numberTextView.text = getString(R.string.win_short)
            }
            2 -> {
                numberTextView.text = getString(R.string.win_medium)
            }
            3 -> {
                numberTextView.text = getString(R.string.win_long)
            }
        }

        if (param2 as Int % 60 == 0) {
            timeTextView.text = (param2 as Int / 60).toString() + ":00"
        } else if (param2 as Int % 60 < 10) {
            timeTextView.text =
                (param2 as Int / 60).toString() + ":0" + (param2 as Int % 60).toString()
        } else {
            timeTextView.text =
                (param2 as Int / 60).toString() + ":" + (param2 as Int % 60).toString()
        }

        playAgainButton.setOnClickListener {
            when (param1) {
                1 -> {
                    val gameFragment = GameFragment.newInstance(1)
                    fragmentManager
                        ?.beginTransaction()
                        ?.setCustomAnimations(
                            R.anim.enter_left_to_right, R.anim.exit_right_to_left,
                            R.anim.enter_right_to_left, R.anim.exit_left_to_right
                        )
                        ?.replace(R.id.frame_layout, gameFragment)
                        ?.commit()
                }
                2 -> {
                    val gameFragment = GameFragment.newInstance(2)
                    fragmentManager
                        ?.beginTransaction()
                        ?.setCustomAnimations(
                            R.anim.enter_left_to_right, R.anim.exit_right_to_left,
                            R.anim.enter_right_to_left, R.anim.exit_left_to_right
                        )
                        ?.replace(R.id.frame_layout, gameFragment)
                        ?.commit()
                }
                3 -> {
                    val gameFragment = GameFragment.newInstance(3)
                    fragmentManager
                        ?.beginTransaction()
                        ?.setCustomAnimations(
                            R.anim.enter_left_to_right, R.anim.exit_right_to_left,
                            R.anim.enter_right_to_left, R.anim.exit_left_to_right
                        )
                        ?.replace(R.id.frame_layout, gameFragment)
                        ?.commit()
                }
            }
        }

        backToMenuButton.setOnClickListener {
            val menuFragment = MainMenuFragment.newInstance()
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left,
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right
                )
                ?.replace(R.id.frame_layout, menuFragment)
                ?.commit()
        }

        saveScoreButton.setOnClickListener {
           if(nickname.text.toString() != ""){
               val dbHelper = DataBaseHelper(rootView.context)
               val db = dbHelper.writableDatabase
               val value = ContentValues()
               value.put("score", param2 as Int)
               when (param1 as Int) {
                   1 -> {
                       value.put("type", "short")
                   }
                   2 -> {
                       value.put("type", "medium")
                   }
                   3 -> {
                       value.put("type", "long")
                   }
               }
               value.put("nick", nickname.text.toString())
               db.insertOrThrow(TableInfo.TABLE_NAME, null, value)
               Toast.makeText(
                   rootView.context,
                   getString(R.string.toast_database_save_success),
                   Toast.LENGTH_SHORT
               ).show()
               saveScoreButton.visibility = View.GONE
               nickname.text = ""
               nickname.clearFocus()
               nickname.visibility = View.GONE
               db.close()
           } else {
               Toast.makeText(
                   rootView.context,
                   getString(R.string.toast_enter_nickname),
                   Toast.LENGTH_SHORT
               ).show()
           }
        }
    }

}