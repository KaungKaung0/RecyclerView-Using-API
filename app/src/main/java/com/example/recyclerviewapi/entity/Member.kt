package com.example.recyclerviewapi.entity

import com.google.gson.annotations.SerializedName

data class Member(

    @SerializedName("name")
    val name: String,

    @SerializedName("profile_pic")
    val profilePic: String,

    @SerializedName("previous_occupation")
    val previousOccupation: String

)