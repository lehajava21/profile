package com.telran.mishpahug.presenter.profile;

import android.net.Uri;

import com.telran.mishpahug.model.Profile;

public interface IViewProfile {
    void onMessage(String title, String content);
    Profile saveProfile(Profile profile);
    void loadProfile(Profile profile);
    void setBirthday(String date);
    void showProgress(Boolean on);
    Uri getUri();
}
