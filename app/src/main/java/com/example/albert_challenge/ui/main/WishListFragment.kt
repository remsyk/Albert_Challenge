package com.example.albert_challenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.albert_challenge.R
import com.example.albert_challenge.RecyclerItemClickListener
import com.example.albert_challenge.adapter.WishListAdapter
import com.example.albert_challenge.io.retrofit.ApiHandler
import com.example.albert_challenge.model.JSONData
import com.example.albert_challenge.realm.BookModel
import com.example.albert_challenge.realm.BookRealmObject
import io.realm.Realm
import io.realm.RealmResults
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
        displayBooks(realm, root)
        return root
    }


    /*fun updateRecyclerView(v:View, context: Context, realm: Realm, bookModel: BookModel) {
        Log.i("Realm", "Onchangelistener OK!")
        var myList: RealmResults<BookRealmObject> = realm.where(BookRealmObject::class.java).findAll()
        myList.addChangeListener(RealmChangeListener<RealmResults<BookRealmObject>> {
            Log.i("Realm", "Database Updated")
            v.recyclerView.adapter = WishListAdapter(bookModel.getbooks(realm), context)
            v.recyclerView.invalidate()
        })
    }
*/
    fun updateRecyclerView2(v:View, context: Context, realm: Realm, book: BookRealmObject) {
        Log.i("Books in Realm" , bookModel.getbooks(realm).toString())
        v.recyclerView.adapter = WishListAdapter(book, context)
        v.recyclerView.invalidate()

    }

    fun setupRecyclerView(v:View, context: Context) {
        v.recyclerView.layoutManager = LinearLayoutManager(context)
        v.recyclerView.recycledViewPool.setMaxRecycledViews(0,0)
        clickListner(v,context)

    }

    fun clickListner(v:View, context: Context){
        v.recyclerView.addOnItemTouchListener(RecyclerItemClickListener(context, v.recyclerView, object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                //Log.i("WE MADE IT", bookList?.get(position).toString())
                //TODO fix object that gets passed into the pop up view from the wish list
                //val bookInfoIntent = BookInfo.newIntent(context, bookList, position)
               // context.startActivity(bookInfoIntent)
            }
            override fun onItemLongClick(view: View?, position: Int) {
                Toast.makeText(context, "Removed from wish list", Toast.LENGTH_SHORT).show()
                //deleteBook(bookList, position)

            }
        }))
    }

    fun displayBooks(realm: Realm, v: View) {
        var results = bookModel.getbooks(realm)
        results.forEach{results -> updateRecyclerView2(v, fragmentContext, realm, results)}
        //repeat(results.size) { updateRecyclerView2(v, fragmentContext, realm, results)}
    }


    /*fun deleteBook(bookList: List<JSONData?>?, pos: Int){
        bookModel.delBook(bookList,pos)
        //TODO update view
    }*/

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