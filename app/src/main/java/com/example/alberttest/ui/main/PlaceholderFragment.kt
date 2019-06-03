package com.example.alberttest.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.example.alberttest.R
import com.example.alberttest.RecyclerItemClickListener
import com.example.alberttest.adapter.RecyclerAdapter
import com.example.alberttest.io.retrofit.ApiHandler
import com.example.alberttest.model.JSONData
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class PlaceholderFragment: Fragment() {

    private lateinit var searchViewModel: SearchPageModel
    private lateinit var fragmentContext: Context
    private lateinit var linearLayoutManager: LinearLayoutManager
    var apiHandler: ApiHandler = ApiHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = ViewModelProviders.of(this).get(SearchPageModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        fragmentContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        searchViewModel.setupRecyclerView(root,fragmentContext)

        root.searchEditText.afterTextChanged {
            //make API request to get booklist
            apiHandler.getBookList(root.searchEditText.text.toString())
            .observe(this, Observer<List<JSONData?>> {
                //update recyclerview with booList data
                searchViewModel.updateRecyclerView(root,it,fragmentContext)
                searchViewModel.clickListner(root,it,fragmentContext)

            })
        }

        return root
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}