package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp.model.MainModel
import com.example.mvp.presenter.MainPresenter
import com.example.mvp.ui.activity.BaseActivity
import com.example.mvp.ui.activity.view.MainView

class MainActivity : BaseActivity<MainPresenter>() {
    override fun initLayout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initResource() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initPresenter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}
