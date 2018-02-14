package com.telran.mishpahug.presenter.profile;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;

import com.telran.mishpahug.R;
import com.telran.mishpahug.interactor.interfaces.IPresenterInteractorProfile;
import com.telran.mishpahug.model.Message;
import com.telran.mishpahug.model.Profile;
import com.telran.mishpahug.model.RequestCode;
import com.telran.mishpahug.view.profile.FragmentProfile;
import com.telran.mishpahug.view.profile.IPresenterViewProfile;

@SuppressLint("ValidFragment")
public class PresenterProfile implements IPresenterInteractorProfile,IPresenterViewProfile {

    private IInteractorPresenterProfile interactor;
    private AppCompatActivity activity;
    private IViewProfile fragment;

    public PresenterProfile(IInteractorPresenterProfile interactor,AppCompatActivity activity){
        this.interactor = interactor;
        this.activity = activity;
    }

    @Override
    public void setView(IViewProfile fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onCloudinaryUrl(String url) {
        fragment.onCloudinaryUrl(url);
        fragment.showProgress(false);
    }

    @Override
    public void onPhoto(Uri uri) {
        fragment.showProgress(true);
        interactor.savePhoto(uri);
    }

    @Override
    public void onSave() {
        Profile profile = fragment.saveProfile();
        interactor.saveProfile(profile);
    }

    @Override
    public void onBirthday() {
        DatePickerDialog   mDatePicker =
                new DatePickerDialog(activity, (datepicker, year, month, day) ->
                        fragment.setBirthday(year + "-" + month + "-" + day),
                        2000, 1, 1);
        mDatePicker.show();
    }

    @Override
    public void show(Profile profile) {
        fragment.setProfile(profile);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, (FragmentProfile)fragment)
                .commit();
    }

    @Override
    public void onMessage(Message message) {
        fragment.onMessage(message.getTitle(), message.getContent());
    }

}
