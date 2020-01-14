package com.kaefer.convertdateslab.extensions

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import trikita.anvil.Anvil

inline fun onItemSelectedNative(crossinline func: (pos: Int) -> Unit) {
    val view: Spinner = Anvil.currentView()
    view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) { }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            func.invoke(pos)
        }
    }
}
