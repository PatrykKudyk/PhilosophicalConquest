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
import java.util.*
import kotlin.collections.ArrayList


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
        var scoresList = ArrayList<Score>()
        val dbHelper = DataBaseHelper(rootView.context)
        val db = dbHelper.readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME}"
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var score: Score
                score = Score(result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_SCORE)).toInt(),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_TYPE)),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_NICK)))
                scoresList.add(score)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        when (param1 as Int) {
            1 -> {
                val list = ArrayList<Score>()
                for (score in scoresList){
                    if(score.type == "short"){
                        list.add(score)
                    }
                }
                recyclerView.adapter = HighscoresAdapter(list.sortedBy { it.time }, 1)
            }
            2 -> {
                val list = ArrayList<Score>()
                for (score in scoresList){
                    if(score.type == "medium"){
                        list.add(score)
                    }
                }
                recyclerView.adapter = HighscoresAdapter(list.sortedBy { it.time }, 2)
            }
            3 -> {
                val list = ArrayList<Score>()
                for (score in scoresList){
                    if(score.type == "long"){
                        list.add(score)
                    }
                }
                recyclerView.adapter = HighscoresAdapter(list.sortedBy { it.time }, 3)
            }
        }
    }
}