package com.example.mvp.ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvp.model.BaseModel
import com.example.mvp.presenter.BasePresenter
import com.example.mvp.ui.activity.view.IView

abstract class BaseActivity<P : BasePresenter<IView, BaseModel>>: AppCompatActivity() {
    val presenter:P ?= null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        registerBaseBroadcastReceiver()
        initLayout()
        initResource()
        initPresenter()
    }

    protected abstract fun initLayout()

    protected abstract fun initResource()

    protected abstract fun initPresenter()

    override fun onDestroy() {
        super.onDestroy()
        unregisterBroadcastReceiver()
    }

    var mBaseBroadcastReceiver:BaseActivityBroadcast ?= null

    protected fun registerBaseBroadcastReceiver():Unit {
        mBaseBroadcastReceiver = BaseActivityBroadcast()
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.CUSTOM")
        //intentFilter.priority = Int.MAX_VALUE
        //intentFilter.addCategory(Intent.ACTION_MAIN)
        registerReceiver(mBaseBroadcastReceiver, intentFilter)
    }

    protected fun unregisterBroadcastReceiver():Unit {
        mBaseBroadcastReceiver ?: return
        unregisterReceiver(mBaseBroadcastReceiver)
    }

    class BaseActivityBroadcast : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}