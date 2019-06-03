package com.example.albert_challenge.model

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("docs")
    var docs: List<JSONData?>?,
    @SerializedName("numFound")
    var numFound: Int?,
    @SerializedName("start")
    var start: Int?
)