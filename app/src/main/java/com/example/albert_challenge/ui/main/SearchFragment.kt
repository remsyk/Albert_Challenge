package com.example.albert_challenge.ui.main

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
import android.widget.EditText
import android.widget.Toast
import com.example.albert_challenge.R
import com.example.albert_challenge.RecyclerItemClickListener
import com.example.albert_challenge.adapter.SearchListAdapter
import com.example.albert_challenge.adapter.WishListAdapter
import com.example.albert_challenge.io.retrofit.ApiHandler
import com.example.albert_challenge.model.JSONData
import com.example.albert_challenge.realm.BookModel
import io.realm.Realm
import kotlinx.android.synthetic.main.search_fragment.view.*

class SearchFragment: Fragment() {

    private lateinit var fragmentContext: Context
    var bookList: List<JSONData?>? = null
    var apiHandler: ApiHandler = ApiHandler()
    var bookModel = BookModel()
    var realm : Realm = Realm.getDefaultInstance()


    override fun onAttach(context: Context){
        super.onAttach(context)
        fragmentContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.search_fragment, container, false)

        setupRecyclerView(root,fragmentContext)
        //retrieves user input as it is being typed in
        root.searchEditText.afterTextChanged {
            //make API request to get booklist using LiveData
            apiHandler.getBookList(root.searchEditText.text.toString())
            .observe(this, Observer<List<JSONData?>> {
                //update recyclerView with booList data
                updateRecyclerView(root,it,fragmentContext)
            })
        }

        return root
    }
    //update the recyclerView
    fun updateRecyclerView(v:View, newBookList: List<JSONData?>?, context: Context) {
        bookList = newBookList
        //attaches adapter to view to load information into layout
        v.recyclerView.adapter = SearchListAdapter(bookList, context)
        v.recyclerView.invalidate()
    }

    fun setupRecyclerView(v:View, context: Context) {
        v.recyclerView.layoutManager = LinearLayoutManager(context)
        v.recyclerView.recycledViewPool.setMaxRecycledViews(0,0)
        clickListner(v,fragmentContext)

    }

    fun clickListner(v:View, context: Context){
        v.recyclerView.addOnItemTouchListener(RecyclerItemClickListener(context, v.recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            //single click to open webView intent
            override fun onItemClick(view: View, position: Int) {
                val bookInfoIntent = BookWebView.newIntent(context, bookList,null, position)
                context.startActivity(bookInfoIntent)
            }
            //long press to add book to realm database
            override fun onItemLongClick(view: View?, position: Int) {
                Toast.makeText(context, bookList?.get(position)?.title.toString() + " added", Toast.LENGTH_SHORT).show()
                bookModel.addBook(bookList,position)
                var wishListAdapter  = WishListAdapter(bookModel.getbooks(realm), context)
                //updates wishlist view
                wishListAdapter.update()
            }
        }))
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
        fun newInstance(sectionNumber: Int): SearchFragment {
            return SearchFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}