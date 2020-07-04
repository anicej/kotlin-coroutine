package com.anice.myapplication

import android.graphics.Color
import java.text.FieldPosition

interface OnItemClickListener {
    fun  onItemClick(categoryID:String,position: Int)
    fun  onCategoryClick(color: String)
}