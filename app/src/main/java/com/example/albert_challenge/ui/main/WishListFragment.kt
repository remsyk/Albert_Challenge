package com.example.albert_challenge.ui.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.albert_challenge.R
import com.example.albert_challenge.RecyclerItemClickListener
import com.example.albert_challenge.adapter.WishListAdapter
import com.example.albert_challenge.realm.BookModel
import com.example.albert_challenge.realm.BookRealmObject
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.wish_list_fragment.view.*


class WishListFragment: Fragment() {


    private lateinit var fragmentContext: Context
    var bookModel = BookModel()
    var realm: Realm = Realm.getDefaultInstance()


    override fun onAttach(context: Context){
        super.onAttach(context)
        fragmentContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.wish_list_fragment, container, false)
        setupRecyclerView(root,fragmentContext,bookModel.getbooks(realm))
        updateRecyclerView(root, fragmentContext, realm, bookModel.getbooks(realm))
        return root
    }

    //updates
    fun updateRecyclerView(v:View?, context: Context, realm: Realm, wishList: RealmResults<BookRealmObject>?) {
        Log.i("Books in Realm" , wishList.toString())
        var wishListAdapter  = WishListAdapter(wishList, context)
        v?.wishrecyclerView?.adapter = wishListAdapter
        //updates adapter
        wishListAdapter.update()
    }

    fun setupRecyclerView(v:View, context: Context, wishList: RealmResults<BookRealmObject>) {
        v.wishrecyclerView.layoutManager = LinearLayoutManager(context)
        v.wishrecyclerView.recycledViewPool.setMaxRecycledViews(0,0)
        clickListner(v,context,wishList)

    }

    fun clickListner(v:View, context: Context, wishList: RealmResults<BookRealmObject>){
        v.wishrecyclerView.addOnItemTouchListener(RecyclerItemClickListener(context, v.wishrecyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            //single press to open webview of the book
            override fun onItemClick(view: View, position: Int) {
                val bookInfoIntent = BookWebView.newIntent(context, null, wishList, position)
                context.startActivity(bookInfoIntent)
            }
            //longpress to delete item from wishlist
            override fun onItemLongClick(view: View?, position: Int) {
                Toast.makeText(context, wishList.get(position)?.title.toString()+" removed", Toast.LENGTH_SHORT).show()
                val newBooklist = bookModel.delBook(realm, wishList.get(position))
                //updates recyclerview wishlist
                updateRecyclerView(view, fragmentContext, realm, newBooklist)
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
        fun newInstance(sectionNumber: Int): WishListFragment {
            return WishListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }

    }
}
