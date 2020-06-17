package com.example.philosophicalconquest.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.philosophicalconquest.R
import com.example.philosophicalconquest.db.DataBaseHelper
import com.example.philosophicalconquest.db.TableInfo
import com.example.philosophicalconquest.models.Score
import com.example.philosophicalconquest.recycler.HighscoresAdapter
import com.example.philosophicalconquest.recycler.MarginItemDecoration


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
class HighscoresFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView

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
        rootView = inflater.inflate(R.layout.fragment_highscores, container, false);
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
            HighscoresFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }

    private fun initFragment() {
        recyclerView = rootView.findViewById(R.id.high_scores_recycler_view)

        val mLayoutManager: LinearLayoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = mLayoutManager

        recyclerView.addItemDecoration(
            MarginItemDecoration(
                12
            )
        )
        val dbHelper = DataBaseHelper(rootView.context)
        val db = dbHelper.readableDatabase
        when(param1 as Int) {
            1 -> {
                val scores = db.execSQL("Select * FROM ${TableInfo.TABLE_NAME} WHERE ${TableInfo.TABLE_COLUMN_TYPE} = short") as Array<Score>
                recyclerView.adapter = HighscoresAdapter(scores,1)
            }
            2 -> {
                val scores = db.execSQL("Select * FROM ${TableInfo.TABLE_NAME} WHERE ${TableInfo.TABLE_COLUMN_TYPE} = medium") as Array<Score>
                recyclerView.adapter = HighscoresAdapter(scores,2)
            }
            3 -> {
                val scores = db.execSQL("Select * FROM ${TableInfo.TABLE_NAME} WHERE ${TableInfo.TABLE_COLUMN_TYPE} = long") as Array<Score>
                recyclerView.adapter = HighscoresAdapter(scores,3)
            }
        }
    }
}