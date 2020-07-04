package com.anice.myapplication.databinding

import android.view.View

import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visible: Boolean?) {
    with(view) {
        visible?.let {
            visibility = if (it) View.VISIBLE
            else View.GONE
        }
    }
}