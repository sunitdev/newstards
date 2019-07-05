package com.news.aggregator.newstard.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

open class BaseActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        _handleOnCreate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _handleOnCreate()
    }

    private fun _handleOnCreate(){
        _configureDagger()
    }

    /**
     * Init Dagger injection
     */
    private fun _configureDagger(){
        AndroidInjection.inject(this)
    }
}
