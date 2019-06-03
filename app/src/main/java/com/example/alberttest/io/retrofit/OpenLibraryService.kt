package com.example.alberttest.io.retrofit

import com.example.alberttest.model.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable


interface OpenLibraryService {
    //create an end point for accessing the docs needed

    companion object {
        const val BASE_URL: String = "https://openlibrary.org/dev/docs/api/"
    }

    @GET("/search.json?")
    fun search(@Query("q") request:String): Observable<APIResponse>
}

