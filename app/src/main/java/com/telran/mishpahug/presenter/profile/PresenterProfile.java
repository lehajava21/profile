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
    private Profile profile;

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
        fragment.showProgress(false);
        Profile profile = fragment.saveProfile(new Profile());
        profile.setPhoto(url);
        interactor.saveProfile(profile);
    }

    @Override
    public void onSave(Boolean photoChanged) {
        if(photoChanged){
            fragment.showProgress(true);
            Uri uri = fragment.getUri();
            interactor.savePhoto(uri);
        }else {
            Profile profile = fragment.saveProfile(new Profile());
            interactor.saveProfile(profile);
        }
    }

    @Override
    public void onPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Fragment)fragment).startActivityForResult(intent, RequestCode.PHOTO.getId());
    }

    @Override
    public void onBirthday() {
        DatePickerDialog   mDatePicker =
                new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datepicker, int year, int month, int day)
            {
                fragment.setBirthday(day + " - " + month + " - " + year);
            }
        },2000, 1, 1);
        mDatePicker.show();
    }

    @Override
    public void show(Profile profile) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, (FragmentProfile)fragment)
                .commit();
        if(profile != null){
            this.profile =profile;
            fragment.loadProfile(profile);
        }
    }

    @Override
    public void onMessage(Message message) {
        fragment.onMessage(message.getTitle(), message.getContent());
    }

}
