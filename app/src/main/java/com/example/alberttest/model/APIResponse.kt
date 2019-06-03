package com.example.alberttest.model

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("docs")
    var docs: List<JSONData?>?,
    @SerializedName("numFound")
    var numFound: Int?,
    @SerializedName("start")
    var start: Int?
)