package com.telran.mishpahug.presenter.profile;

import android.net.Uri;
import android.widget.ImageView;

import com.telran.mishpahug.model.Profile;

public interface IInteractorPresenterProfile {
    void savePhoto(Uri uri);
    void saveProfile(Profile profile);
}
