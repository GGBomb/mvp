package com.example.mvp.presenter

import com.example.mvp.model.BaseModel
import com.example.mvp.ui.activity.view.IView

abstract class BasePresenter<V: IView, M : BaseModel>() {
    var view:V ?= null
    var model:M ?= null

    fun setArgument(view:V, model:M) {
        this.view = view
        this.model = model
    }

    fun detach() {
        this.view = null
        this.model = null
    }
}