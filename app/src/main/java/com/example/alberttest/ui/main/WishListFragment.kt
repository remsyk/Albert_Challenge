package com.example.alberttest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.alberttest.BookInfo
import com.example.alberttest.R
import com.example.alberttest.RecyclerItemClickListener
import com.example.alberttest.adapter.RecyclerAdapter
import com.example.alberttest.io.retrofit.ApiHandler
import com.example.alberttest.model.JSONData
import com.example.alberttest.realm.BookModel
import com.example.alberttest.realm.BookRealmObject
import io.realm.Realm
import kotlinx.android.synthetic.main.wish_list_fragment.view.*

class WishListFragment: Fragment() {

    private lateinit var fragmentContext: Context
    var bookList: List<JSONData?>? = null
    var apiHandler: ApiHandler = ApiHandler()
    var bookModel = BookModel()
    var realm = Realm.getDefaultInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        fragmentContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.wish_list_fragment, container, false)

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
                Toast.makeText(context, "Added to WishList", Toast.LENGTH_SHORT).show()
                addBook2Realm(bookList,position)

            }
        }))
    }

    fun displayBooks(realm: Realm) {
        var data = ""
        var results = bookModel.getbooks(realm)
        results.forEach { result -> data += "$result \n --------------------------------------- \n"
        }
        //display.text = data
    }

    fun addBook2Realm(bookList: List<JSONData?>?, pos: Int){
        var book = BookRealmObject(title =  bookList?.get(pos)?.title!!, authorName =  bookList?.get(pos)?.authorName?.get(0)!!, coverID = bookList?.get(pos)?.coverI!!,  seed =  bookList?.get(pos)?.seed?.get(1).toString())
        if (bookModel.getbooks(realm).count() <= 0) {
            bookModel.addBook(realm, book)
        } else {
            var v = bookModel.getLastbook(realm)
            var newBook = v.copy(v.title + 1)
            bookModel.addBook(realm, newBook)
        }
        //displayBooks(realm)
        //TODO update view
    }

    fun deleteBook(title: String){
        bookModel.delBook(realm,title)
        //TODO update view
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
        fun newInstance(sectionNumber: Int): WishListFragment {
            return WishListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}