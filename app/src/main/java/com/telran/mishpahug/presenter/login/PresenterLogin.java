package com.telran.mishpahug.presenter.login;

import android.support.v7.app.AppCompatActivity;
import com.telran.mishpahug.R;
import com.telran.mishpahug.interactor.interfaces.IPresenterInteractorLoginr;
import com.telran.mishpahug.model.Message;
import com.telran.mishpahug.view.login.FragmentLogin;
import com.telran.mishpahug.view.login.IPresenterViewLogin;

public class PresenterLogin implements IPresenterInteractorLoginr,IPresenterViewLogin {

    private IInteractorPresenterLogin interactor;
    private AppCompatActivity activity;
    private IViewtLogin fragment;

    public PresenterLogin(IInteractorPresenterLogin interactor,AppCompatActivity activity){
        this.interactor = interactor;
        this.activity = activity;
    }

    @Override
    public void setView(IViewtLogin fragment) {
        this.fragment = fragment;
    }

    @Override
    public void show() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, (FragmentLogin)fragment)
                .commit();
    }

    @Override
    public void onMessage(Message message) {
        fragment.onMessage(message.getTitle(), message.getContent());
    }

    @Override
    public void onFbToken(String token) {
        interactor.onFbToken(token);
    }

}
