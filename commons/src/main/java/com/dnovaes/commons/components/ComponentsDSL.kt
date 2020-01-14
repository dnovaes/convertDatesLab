package com.dnovaes.commons.components

import android.view.View
import trikita.anvil.Anvil
import trikita.anvil.DSL

/*
* A collection of functions using high order functions feature from kotlin
* to DRY UI code and give a elegant syntax to our custom components.
 */

inline fun <reified T : View> highOrderComponent(crossinline func: T.() -> Unit) {
    DSL.v(T::class.java) {
        val block : T = Anvil.currentView()
        block.func()
    }
}
