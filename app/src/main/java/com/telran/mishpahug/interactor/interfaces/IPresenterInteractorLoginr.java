package com.telran.mishpahug.interactor.interfaces;

import com.telran.mishpahug.model.Message;
import com.telran.mishpahug.presenter.login.IViewtLogin;

public interface IPresenterInteractorLoginr {
    void show();
    void onMessage(Message message);
    void setView(IViewtLogin view);
}
