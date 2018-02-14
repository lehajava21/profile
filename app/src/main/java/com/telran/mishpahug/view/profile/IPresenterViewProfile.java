package com.telran.mishpahug.view.profile;

import android.net.Uri;

public interface IPresenterViewProfile {
    void onPhoto(Uri uri);
    void onSave();
    void onBirthday();
}
