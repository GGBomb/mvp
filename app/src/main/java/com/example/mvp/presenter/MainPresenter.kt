package com.example.mvp.presenter

import com.example.mvp.model.MainModel
import com.example.mvp.ui.activity.view.MainView

class MainPresenter:BasePresenter<MainView, MainModel> {

    constructor(view:MainView, model:MainModel){
        setArgument(view, model)
    }

}