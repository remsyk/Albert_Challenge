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
import io.realm.RealmResults
import kotlinx.android.synthetic.main.list_item.view.*

class WishListAdapter(private val wishList: RealmResults<BookRealmObject>, val context: Context) : RecyclerView.Adapter<WishListAdapter.BookViewHolder>() {

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
        imageURL = imageUrlBase + wishList.get(pos)?.coverID + "-L.jpg"
        Picasso.with(context).load(imageURL).into(viewHolder.bookImageView)
        viewHolder.titleTextView.text = wishList.get(pos)?.title
        viewHolder.authorTextView.text = wishList.get(pos)?.authorName
    }

    // view holder
    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImageView = view.book_image!!
        val titleTextView = view.book_title!!
        val authorTextView = view.book_author!!
    }
}