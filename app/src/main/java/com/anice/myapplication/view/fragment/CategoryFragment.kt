package com.anice.myapplication.view.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.anice.myapplication.model.Categories
import com.anice.myapplication.OnItemClickListener
import com.anice.myapplication.R
import com.anice.myapplication.common.Utils
import org.json.JSONArray
import org.json.JSONException
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var categories: List<Categories>
    private var listener: OnItemClickListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View
        view = inflater.inflate(R.layout.fragment_category, container, false)
        setup(view)
        return view
    }

    private fun setup(view: View) {
        categories = getAllCategories(context?.let {
            Utils.loadJSONFromAsset("categories.json")
        })!!
        var llMain: LinearLayout = view?.findViewById(R.id.llMain) as LinearLayout
        val arrayTextView: Array<TextView?> = arrayOfNulls<TextView>(4)
        for (i in 0..3) {
            arrayTextView[i] = llMain.getChildAt(i) as TextView
            arrayTextView[i]?.setText(categories[i].title)
            arrayTextView[i]?.setBackgroundColor(Color.parseColor(categories[i].color))
        }
        arrayTextView[0]?.setOnClickListener { listener?.onCategoryClick(categories[0].color) }
        arrayTextView[1]?.setOnClickListener { listener?.onCategoryClick(categories[1].color) }
        arrayTextView[2]?.setOnClickListener { listener?.onCategoryClick(categories[2].color) }
        arrayTextView[3]?.setOnClickListener { listener?.onCategoryClick(categories[3].color) }

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CategoryFragment().apply {
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    fun getAllCategories(stringJsonArray: String?): List<Categories>? {
        var listdata: List<Categories> = ArrayList<Categories>()
        try {
            val jArray = JSONArray(stringJsonArray)
            listdata = ArrayList<Categories>()
            if (jArray != null) {
                for (i in 0 until jArray.length()) {
                    listdata.add(
                        Categories(
                            jArray.getJSONObject(i).getString("id"),
                            jArray.getJSONObject(i).getString("title"),
                            jArray.getJSONObject(i).getString("color")
                        )
                    )
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return listdata
    }

}
