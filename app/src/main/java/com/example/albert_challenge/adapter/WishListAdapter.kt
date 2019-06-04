package com.example.albert_challenge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.albert_challenge.R
import com.example.albert_challenge.model.JSONData
import com.example.albert_challenge.realm.BookRealmObject
import com.squareup.picasso.Picasso
import io.realm.RealmResults
import kotlinx.android.synthetic.main.list_item.view.*

class WishListAdapter(val book: BookRealmObject, val context: Context) : RecyclerView.Adapter<WishListAdapter.BookViewHolder>() {

    private var imageURL = ""
    private val imageUrlBase = "http://covers.openlibrary.org/b/id/"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder (LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        if (book == null) {
            return 0
        } else {
            return 10
        }
    }


    override fun onBindViewHolder(viewHolder: BookViewHolder, pos: Int) {
        imageURL = imageUrlBase + book.coverID + "-L.jpg"
        Picasso.with(context).load(imageURL).into(viewHolder.bookImageView)
        viewHolder.titleTextView.text = book.title
        viewHolder.authorTextView.text = book.authorName
    }

    // view holder
    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImageView = view.book_image!!
        val titleTextView = view.book_title!!
        val authorTextView = view.book_author!!
    }
}