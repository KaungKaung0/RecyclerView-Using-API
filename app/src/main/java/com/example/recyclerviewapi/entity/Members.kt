package com.example.recyclerviewapi.entity

import com.google.gson.annotations.SerializedName

data class Members(
    @SerializedName("data")
    val `data`: List<Member>
)