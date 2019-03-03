package com.example.recyclerviewapi.utils

import me.myatminsoe.mdetect.MDetect

object FontHelper {
    fun isUnicode():Boolean{
        if(MDetect.isUnicode()){
            return true
        }
        return false
    }
}