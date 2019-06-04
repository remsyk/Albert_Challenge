package com.example.albert_challenge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.albert_challenge.R
import com.example.albert_challenge.realm.BookRealmObject
import com.squareup.picasso.Picasso
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.list_item.view.*
//Passes in wishList from realm database
class WishListAdapter(private val wishList: RealmResults<BookRealmObject>?, val context: Context) : RecyclerView.Adapter<WishListAdapter.BookViewHolder>() {

    private var imageURL = ""
    private val imageUrlBase = "http://covers.openlibrary.org/b/id/"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder (LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        if (wishList == null) {
            return 0
        } else {
            return wishList.size
        }
    }

    override fun onBindViewHolder(viewHolder: BookViewHolder, pos: Int) {
        //constructs url string for book image
        imageURL = imageUrlBase + wishList?.get(pos)?.coverID + "-L.jpg"
        //binds book image to the recycler view
        Picasso.with(context).load(imageURL).into(viewHolder.bookImageView)
        //binds book title to the recycler view
        viewHolder.titleTextView.text = wishList?.get(pos)?.title
        //binds book author to the recycler view
        viewHolder.authorTextView.text = wishList?.get(pos)?.authorName
    }

    // view holder that gets called in onCreateViewHolder
    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImageView = view.book_image!!
        val titleTextView = view.book_title!!
        val authorTextView = view.book_author!!
    }

    //updates the view when a book is added or deleted from the database
    fun update(){
        wishList?.addChangeListener(RealmChangeListener {
            notifyDataSetChanged()
        })
    }

}