package com.example.alberttest.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.example.alberttest.BookInfo
import com.example.alberttest.R
import com.example.alberttest.RecyclerItemClickListener
import com.example.alberttest.adapter.RecyclerAdapter
import com.example.alberttest.io.retrofit.ApiHandler
import com.example.alberttest.model.JSONData
import kotlinx.android.synthetic.main.favorite_fragment.view.*

class FavoritesFragment: Fragment() {

    private lateinit var fragmentContext: Context
    var bookList: List<JSONData?>? = null
    var apiHandler: ApiHandler = ApiHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        fragmentContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.favorite_fragment, container, false)

        setupRecyclerView(root,fragmentContext)

      /*  root.searchEditText.afterTextChanged {
            //make API request to get booklist
            apiHandler.getBookList(root.searchEditText.text.toString())
            .observe(this, Observer<List<JSONData?>> {
                //update recyclerview with booList data
                updateRecyclerView(root,it,fragmentContext)
            })
        }*/

        return root
    }

    fun updateRecyclerView(v:View, newBookList: List<JSONData?>?, context: Context) {
        bookList = newBookList
        v.recyclerView.adapter = RecyclerAdapter(bookList, context)
        v.recyclerView.invalidate()
    }

    fun setupRecyclerView(v:View, context: Context) {
        v.recyclerView.layoutManager = LinearLayoutManager(context)
        v.recyclerView.recycledViewPool.setMaxRecycledViews(0,0)
        clickListner(v,fragmentContext)

    }

    fun clickListner(v:View, context: Context){
        v.recyclerView.addOnItemTouchListener(RecyclerItemClickListener(context, v.recyclerView, object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                Log.i("WE MADE IT", bookList?.get(position).toString())
                val bookInfoIntent = BookInfo.newIntent(context, bookList, position)
                context.startActivity(bookInfoIntent)
            }
            override fun onItemLongClick(view: View?, position: Int) {
                Log.i("LONGPRESS", "LONGPRESS")

            }
        }))
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
        fun newInstance(sectionNumber: Int): FavoritesFragment {
            return FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}