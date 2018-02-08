package com.telran.mishpahug.interactor.interfaces;

import com.telran.mishpahug.model.Message;
import com.telran.mishpahug.model.Profile;
import com.telran.mishpahug.presenter.profile.IViewProfile;

public interface IPresenterInteractorProfile {
    void show(Profile profile);
    void onMessage(Message message);
    void setView(IViewProfile view);
    void onCloudinaryUrl(String url);
}
