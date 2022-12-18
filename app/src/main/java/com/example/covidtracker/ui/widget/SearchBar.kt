package com.example.covidtracker.ui.widget

import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.EditText

fun setSearchBar(editText: EditText, callback: () -> Unit) {
    editText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (editText.text.isNotEmpty()) callback()
            return@OnEditorActionListener true
        }
        false
    })
}
