package com.dnovaes.convertdateslab.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import trikita.anvil.RenderableView

abstract class RenderableActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        setContentView(object: RenderableView(this) {
            override fun view() {
                content()
            }
        })
    }

    abstract fun content()

}
